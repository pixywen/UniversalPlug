package com.tatian.up.engine.controller;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.EventManager;
import com.tatian.up.engine.manager.Manager;
import com.tatian.up.engine.manager.impl.ActivityManager;
import com.tatian.up.engine.manager.impl.EventManagerImpl;
import com.tatian.up.engine.manager.impl.ExceptionManagerImpl;
import com.tatian.up.event.PlatformReleaseEvent;
import com.tatian.up.manager.ExceptionManager;
import com.tatian.up.service.UniversalPlugService;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.UniversalPlugString;

public final class UniversalPlugController {
	public static final String SDK_VERSION = "1.0.0";
	public static boolean GAME_DEBUG;
	private static UniversalPlugController instance;

	// Activity Context
	private SoftReference<Context> contextReference;

	private String appId;
	private String appKey;
	private String channelId;
	private String appLanguage;
	private String packageName;
	private int screenOrientation;
	private Map<String, String> extraAttrMap;
	private UserSession userSession;
	private SystemInfo systemInfo;

	// Public Manager
	private ExceptionManager exceptionManager;

	// Custom Manager
	private ActivityManager activityManager;
	private EventManager eventManager;

	public static UniversalPlugController getInstance() {
		if (instance == null) {
			throw new RuntimeException("Cannot get SevengaController instance.");
		}
		return instance;
	}

	private UniversalPlugController(UniversalPlugRunConfig config) {
		contextReference = new SoftReference<Context>(config.getContext());
		GAME_DEBUG = config.isDebug();
		appId = config.getAppId();
		appKey = config.getAppKey();
		channelId = config.getChannelId();
		screenOrientation = config.getScreenOrientation();
		extraAttrMap = config.getExtraAttributeMap();
		appLanguage = config.getLocale() == null ? config.getContext()
				.getResources().getConfiguration().locale.getLanguage()
				: config.getLocale().getLanguage();
		systemInfo = new SystemInfo(config);
		mainThreadHandler = new Handler(Looper.getMainLooper());
		userSession = new UserSession();
		exceptionManager = new ExceptionManagerImpl();
		activityManager = new ActivityManager();
		eventManager = new EventManagerImpl();
		density = config.getContext().getResources().getDisplayMetrics().density;
		packageName = config.getContext().getPackageName();

	}

	public static void init(UniversalPlugRunConfig config) {
		instance = new UniversalPlugController(config);
		instance.initString(config.getContext());
		instance.initManager(config);

//		if (Constants.DEBUG) {
//			instance.getContext().startService(
//					new Intent(instance.getContext(), UniversalPlugService.class));
//		}
	}

	private void initManager(UniversalPlugRunConfig config) {
		for (Field field : UniversalPlugController.class.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				field.getType().asSubclass(Manager.class);
			} catch (ClassCastException e) {
				continue;
			}
			try {
				((Manager) (field.get(instance))).init(config);
			} catch (Exception e) {
				Debug.w("Init Manager Failed : " + field.getName());
				Debug.w(e);
			}
		}
	}

	private void releaseManager() {
		for (Field field : UniversalPlugController.class.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				field.getType().asSubclass(Manager.class);
			} catch (ClassCastException e) {
				continue;
			}
			try {
				((Manager) (field.get(instance))).release();
			} catch (Exception e) {
				Debug.w("Release Manager Failed : " + field.getName());
				Debug.w(e);
			}
		}
	}

	public static void release() {
		if (instance != null) {
			instance.eventManager.dispatchEvent(new PlatformReleaseEvent());
			instance.releaseManager();
			instance = null;
		}
	}

	public void requestExitContext() {
		if (contextReference == null) {
			Debug.w("requestExitContext Failed , contextReference is null.");
			return;
		}
		if (contextReference.get() == null) {
			Debug.w("requestExitContext Failed , context is null.");
			return;
		}
		if (!(contextReference.get() instanceof Activity)) {
			Debug.w("requestExitContext Failed , context is not Activity.");
			return;
		}
		((Activity) contextReference.get()).finish();
	}

	private void initString(Context context) {
		Resources res = context.getResources();
		Class<?> rClass = null;
		try {
			rClass = Class.forName(context.getPackageName() + ".R");
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(
					"Platform Init Failed : Cannot find R class of Context : "
							+ context.getPackageName());
		}
		Class<?>[] classes = rClass.getClasses();
		Class<?> stringClass = null;
		for (int i = 0; i < classes.length; ++i) {
			if (classes[i].getName().split("\\$")[1].equals("string")) {
				stringClass = classes[i];
				break;
			}
		}
		for (Field field : UniversalPlugString.class.getFields()) {
			try {
				int id = stringClass.getField("up_" + field.getName())
						.getInt(null);
				field.set(null, res.getString(id));
			} catch (Exception e) {
				Debug.w("Controllter-initString",
						"Init String Failed : " + field.getName());
				try {
					field.set(null, "Unknown");
				} catch (Exception e1) {
				}
				continue;
			}
		}
	}

	private static final int HIDE = 0;
	private static final int READY_SHOW = 1;
	private static final int SHOW = 2;
	private static final int READY_HIDDEN = 3;

	// Loading对话框
	private Dialog progressDialog;
	private TextView progressTextView;
	private int progressDialogState = HIDE;
	private boolean requestDismiss;

	public void showProgressDialog(String message) {

	}

	public void showProgressDialog(final Context context, String message) {
		if (progressDialogState == SHOW || progressDialogState == READY_SHOW) {
			return;
		}
		final String msg = message;
		
		requestDismiss = false;
		progressDialogState = READY_SHOW;
		post2MainThread(new Runnable() {

			@Override
			public void run() {
				if (progressDialog == null) {
					createProgressDialog(context);
				}
				progressTextView.setText(msg);
				showDialogSafe();
			}
		});
	}

	public void closeProgressDialog() {
		switch (progressDialogState) {
		case SHOW:
			dismissDialogSafe();
			break;
		case READY_SHOW:
			requestDismiss = true;
			break;
		case HIDE:
			break;
		case READY_HIDDEN:
			break;
		}
	}

	private void createProgressDialog(Context context) {
		progressDialog = new Dialog(context,
				com.tatian.up.R.style.com_tatian_up_transparent_dialog);
		progressDialog
				.setContentView(com.tatian.up.R.layout.com_tatian_up_progress_dialog);
		progressTextView = (TextView) progressDialog
				.findViewById(com.tatian.up.R.id.progress_dialog_text);
		progressDialog.setCancelable(false);
		progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				if (requestDismiss) {
					requestDismiss = false;
					dismissDialogSafe();
				} else {
					progressDialogState = SHOW;
				}
			}
		});
		progressDialog
				.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						progressDialogState = HIDE;
						progressDialog = null;
					}
				});
	}

	private void dismissDialogSafe() {
		post2MainThread(new Runnable() {
			@Override
			public void run() {
				try {
					progressDialogState = READY_HIDDEN;
					if (progressDialog != null) {
						progressDialog.dismiss();
					} else {
						progressDialogState = HIDE;
					}
				} catch (Exception e) {
					Debug.w("Dismiss ProgressDialog throw Exception : ");
					Debug.w(e);
				}
			}
		});
	}

	private void showDialogSafe() {
		post2MainThread(new Runnable() {
			@Override
			public void run() {
				try {
					progressDialogState = READY_SHOW;
					if (progressDialog != null) {
						progressDialog.show();
					} else {
						progressDialogState = HIDE;
					}
				} catch (Exception e) {
					Debug.w("Show ProgressDialog throw Exception : ");
					Debug.w(e);
					progressDialogState = HIDE;
				}
			}
		});
	}

	private Handler mainThreadHandler;
	private Toast commonToast;
	private float density;

	public int dp2px(int dp) {
		return (int) (dp * density);
	}

	public void post2MainThread(Runnable r) {
		mainThreadHandler.post(r);
	}

	public void post2MainThreadDelayed(Runnable r, long delayMillis) {
		mainThreadHandler.postDelayed(r, delayMillis);
	}

	public void showToast(final String message) {
		post2MainThread(new Runnable() {
			@Override
			public void run() {
				if (commonToast == null) {
					commonToast = Toast.makeText(getContext(), message,
							Toast.LENGTH_SHORT);
					commonToast.setGravity(Gravity.CENTER, 0, 0);
				}
				commonToast.setDuration(Toast.LENGTH_SHORT);
				commonToast.setText(message);
				commonToast.show();
			}
		});
	}

	public Context getActiveContext() {
		Context context = getActivityManager().getActiveActivity();
		if (context == null) {
			context = getContext();
		}
		return context;
	}

	public Context getContext() {
		if (contextReference == null) {
			throw new RuntimeException("ContextReference is null.");
		}
		if (contextReference.get() == null) {
			throw new RuntimeException("Cannot get context from reference.");
		}
		return contextReference.get();
	}

	public ActivityManager getActivityManager() {
		return activityManager;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppLanguage() {
		return appLanguage;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getExtraAttribute(String name) {
		return extraAttrMap.get(name);
	}

	public Map<String, String> getExtraAttributeMap() {
		return extraAttrMap;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public ExceptionManager getExceptionManager() {
		return exceptionManager;
	}

	public int getScreenOrientation() {
		return screenOrientation;
	}

}
