package com.tatian.up.ui;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.UniversalPlugString;

public class SplashActivity extends Activity {

	protected ImageView screen;
	protected RelativeLayout root;
	protected long duration = 2000;
	protected List<String> splashIteam;
	protected String mainActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		UniversalPlugRunConfig config = UniversalPlugRunConfig
				.initFromXML(this);
		setRequestedOrientation(config.getScreenOrientation());

		root = new RelativeLayout(this);
		setContentView(root);

		screen = new ImageView(this);
		RelativeLayout.LayoutParams screen_params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		screen.setLayoutParams(screen_params);
		screen.setScaleType(ScaleType.FIT_XY);
		root.addView(screen);

		init();

	}

	protected void init() {

		try {
			initString(this);
			duration = Long.valueOf(UniversalPlugString.splash_duration);
			splashIteam = getSplashItem();
			mainActivity = UniversalPlugString.main_activity;
			step(screen, 0, splashIteam.size());
		} catch (Exception e) {

			if (mainActivity != null && mainActivity.length() > 0)
				jumpToMain(mainActivity);

		}

	}

	protected void jumpToMain(String main) {

		Intent intent = new Intent();
		intent.setClassName(SplashActivity.this, main);
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();

	}

	protected List<String> getSplashItem() {

		List<String> result = new ArrayList<String>();

		for (Field field : UniversalPlugString.class.getFields()) {
			if (field.getName().startsWith("splash_path_"))
				try {
					String path = (String) field.get(field.getName());
					if (path != null && path.length() > 0)
						result.add(path);
					Debug.d("splash_path: ");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return result;

	}

	protected void step(final ImageView iv, final int step, final int length) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(duration);
		setImage(iv, splashIteam.get(step));
		iv.startAnimation(alphaAnimation);

		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				if (step < length - 1) {
					step(iv, step + 1, length);
				}

				else {
					if (mainActivity != null && mainActivity.length() > 0)
						jumpToMain(mainActivity);
				}

			}
		});

	}

	protected void setImage(ImageView iv, String path) {

		try {
			screen.setImageBitmap(getImageFromAssetsFile(path));
		} catch (Exception e) {

		}

	}

	protected Bitmap getImageFromAssetsFile(String fileName) {
		Bitmap image = null;
		AssetManager am = getResources().getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

	protected void initString(Context context) {
		Resources res = context.getResources();
		Class<?> rClass = null;
		try {
			rClass = Class.forName(context.getPackageName() + ".R");
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(
					"UniversalPlugPlatform Init Failed : Cannot find R class of Context : "
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
				int id = stringClass.getField("up_" + field.getName()).getInt(
						null);
				field.set(null, res.getString(id));
			} catch (Exception e) {
				try {
					field.set(null, "Unknown");
				} catch (Exception e1) {
				}
				continue;
			}
		}
	}
}
