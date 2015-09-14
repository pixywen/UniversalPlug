package com.tatian.up.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tatian.up.engine.controller.Constants;
import com.tatian.up.engine.controller.UniversalPlugController;
import com.tatian.up.engine.manager.AbstractStatisticsManager;
import com.tatian.up.engine.manager.impl.PaymentManagerImpl;
import com.tatian.up.engine.manager.impl.StatisticsManagerImpl;
import com.tatian.up.engine.manager.impl.UserManagerImpl;
import com.tatian.up.event.ExitEvent;
import com.tatian.up.event.exevent.ActivityPauseEvent;
import com.tatian.up.event.exevent.ActivityResultEvent;
import com.tatian.up.event.exevent.ActivityResumeEvent;
import com.tatian.up.event.exevent.ActivityStartEvent;
import com.tatian.up.event.exevent.ActivityStopEvent;
import com.tatian.up.event.handler.EventHandler;
import com.tatian.up.event.handler.ExitHandler;
import com.tatian.up.index.UniversalPlugIndex;
import com.tatian.up.manager.ExceptionManager;
import com.tatian.up.manager.PaymentManager;
import com.tatian.up.manager.ToolBarManager;
import com.tatian.up.manager.UserManager;
import com.tatian.up.ui.AboutDialog;
import com.tatian.up.ui.ExitDialog;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.NotProguard;

public class UniversalPlugPlatform implements NotProguard {
	protected static UniversalPlugPlatform instance;
	protected ToolBarManager toolbarManager;
	protected PlatformInitCompleteCallback callback;
	protected PaymentManager paymentManager;
	protected UserManager userManager;

	protected AbstractStatisticsManager statsManager;

	protected UniversalPlugPlatform(UniversalPlugRunConfig config,
			PlatformInitCompleteCallback callback) {
		this.callback = callback;
		createManager();
	}

	/**
	 * 
	 * @return UniversalPlugPlatform的实例
	 */
	public static UniversalPlugPlatform getInstance() {
		if (instance == null) {
			throw new RuntimeException("UniversalPlugPlatform has not init.");
		}
		return instance;
	}

	/**
	 * 初始化方法,需传递一个{@link UniversalPlugRunConfig}对象
	 * 
	 * @param config
	 *            平台运行参数对象
	 */
	public static void init(UniversalPlugRunConfig config,
			final PlatformInitCompleteCallback callback) {
		UniversalPlugController.init(config);
		UniversalPlugIndex.getInstance().init(config.getContext());
		String extarSDK = config.getExtraAttribute("extraSDK");

		if (extarSDK == null || extarSDK.trim().equals("")
				|| extarSDK.equalsIgnoreCase("UniversalPlug")) {
			instance = new UniversalPlugPlatform(config, callback);
			callback.onPlatformInitComplete(PlatformInitCompleteCallback.SUCCESS);
		} else {
			try {
				Class<?> sdkClass = Class.forName(Constants.PROXY_PACKAGE_NAME
						+ extarSDK + "PlatformProxy");
				instance = (UniversalPlugPlatform) (sdkClass.getConstructor(
						UniversalPlugRunConfig.class,
						PlatformInitCompleteCallback.class).newInstance(config,
						callback));
			} catch (Exception e) {
				Debug.e("___UniversalPlug___", "Init SDK Failed : " + extarSDK);
				Debug.e(e);
				callback.onPlatformInitComplete(PlatformInitCompleteCallback.INIT_FAILED);
			}
		}
	}

	/**
	 * 初始化方法,这个方法将从assets的xml读取RunConfig信息
	 */
	public static void init(Context context,
			PlatformInitCompleteCallback callback) {
		init(UniversalPlugRunConfig.initFromXML(context), callback);

	}

	/**
	 * release方法需在应用onDestroy时调用
	 */
	public static void release() {

		UniversalPlugController.release();
		if (instance != null) {
			instance = null;
		}
	}

	// 退出接口
	/**
	 * 退出接口
	 * 
	 * @param Activity
	 * @param ExitHandler
	 *            退出回调函数
	 * @return UniversalPlugPlatform的实例
	 */

	public void exit(Activity activity, ExitHandler handler) {

		Debug.d("exit");
		if (handler != null) {
			UniversalPlugController.getInstance().getEventManager()
					.registerEventHandler(handler);
		}

		doExit(activity);

	}

	/**
	 * 获得渠道号
	 * 
	 * @return String 渠道编号
	 */
	public String getChannel() {

		Debug.d("getChannel: "
				+ UniversalPlugController.getInstance().getChannelId());
		return UniversalPlugController.getInstance().getChannelId();
	}

	/**
	 * 获得Proxy版本号
	 * 
	 * @return
	 */
	public String getProxyVersion() {

		String version = doGetProxyVersion();

		Debug.d("version: " + version);

		return version;
	}

	/**
	 * 获得包名
	 * 
	 * @return String 包名
	 */
	public String getPackageName() {

		Debug.d("getPackageName: "
				+ UniversalPlugController.getInstance().getPackageName());
		return UniversalPlugController.getInstance().getPackageName();
	}

	/**
	 * 打开关于页
	 * 
	 * @param activity
	 */
	public void about(Activity activity) {

		Debug.d("about");
		AboutDialog.getInstance().show(activity);

	}

	protected String doGetProxyVersion() {
		return "0.0.1";
	}

	protected void doExit(Activity activity) {

		new ExitDialog().exit(activity, new ExitDialog.ExitCallback() {

			@Override
			public void onPositive() {
				// TODO Auto-generated method stub
				notifyExitSuccess();
			}

			@Override
			public void onNegative() {
				// TODO Auto-generated method stub
				notifyCancelExit();
			}
		});

	}

	protected void notifyCancelExit() {
		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ExitEvent(ExitEvent.CANCEL));
	}

	protected void notifyExitSuccess() {
		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ExitEvent(ExitEvent.EXIT));
	}

	// 判断是否需要开启音乐接口(移动基地含有此接口)
	/**
	 * 
	 * @param activity
	 * @return boolean 是否开启音乐
	 */
	public boolean isMusicEnabled(Activity activity) {
		Debug.d("isMusicEnabled");
		return false;
	}

	// 更多游戏接口
	/**
	 * 更多游戏接口
	 * 
	 * @param activity
	 */
	public void viewMoreGames(Activity activity) {
		Debug.d("viewMoreGames");

	}

	/**
	 * onActivityResume需在Activity的onResume方法中调用
	 */
	public void onActivityResume(Activity activity) {

		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ActivityResumeEvent());

	}

	/**
	 * onActivityPause需在Activity的onPause方法中调用
	 */
	public void onActivityPause(Activity activity) {

		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ActivityPauseEvent());

	}

	/**
	 * onActivityStart需在Activity的onStart方法中调用
	 */
	public void onActivityStart(Activity activity) {

		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ActivityStartEvent());
	}

	/**
	 * onActivityStop需在Activity的onStop方法中调用
	 */
	public void onActivityStop(Activity activity) {
		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new ActivityStopEvent());
	}

	/**
	 * onActivityStop需在Activity的onActivityResult方法中调用
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		UniversalPlugController
				.getInstance()
				.getEventManager()
				.dispatchEvent(
						new ActivityResultEvent(requestCode, resultCode, data));
	}

	/**
	 * 注册事件的Handler
	 * 
	 * @param eventHandler
	 *            需要注册的Handler
	 */
	public void registerEventHandler(EventHandler eventHandler) {
		UniversalPlugController.getInstance().getEventManager()
				.registerEventHandler(eventHandler);
	}

	/**
	 * 反注册Handler
	 * 
	 * @param eventHandler
	 *            需要反注册的Handler
	 */
	public void unRegisterEventHandler(EventHandler eventHandler) {
		UniversalPlugController.getInstance().getEventManager()
				.unRegisterEventHandler(eventHandler);
	}

	/**
	 * @return ExceptionManager的实例
	 */
	public ExceptionManager getExceptionManager() {
		return UniversalPlugController.getInstance().getExceptionManager();
	}

	/**
	 * @return StatisticsManager的实例
	 */
	public AbstractStatisticsManager getStatsManager() {
		return statsManager;
	}

	/**
	 * @return UserManager的实例
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @return PaymentManager的实例
	 */
	public PaymentManager getPaymentManager() {
		return paymentManager;
	}

	/**
	 * @return ToolbarManager的实例
	 */
	public ToolBarManager getToolbarManager() {
		return toolbarManager;
	}

	protected void createManager() {

		paymentManager = new PaymentManagerImpl();
		userManager = new UserManagerImpl();
		statsManager = new StatisticsManagerImpl(null);

	}

	public static interface PlatformInitCompleteCallback extends NotProguard {
		int SUCCESS = 0;
		int NETWORK_FAILED = -1;
		int INIT_FAILED = -2;
		int WRONG_CONFIG = -3;

		void onPlatformInitComplete(int code);
	}

}
