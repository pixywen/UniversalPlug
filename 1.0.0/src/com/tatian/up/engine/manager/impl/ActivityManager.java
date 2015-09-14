package com.tatian.up.engine.manager.impl;

import java.lang.ref.SoftReference;

import android.app.Activity;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.Manager;

//管理SDK的Activity
public class ActivityManager implements Manager {
	
	
	private SoftReference<Activity> activeActivityRef;
	
	
	@Override
	public void init(UniversalPlugRunConfig config) {

	}

	@Override
	public void release() {

	}



	public void notifyActivityActive(Activity activity) {
		activeActivityRef = new SoftReference<Activity>(activity);
	}


	public Activity getActiveActivity() {
		return activeActivityRef == null ? null : activeActivityRef.get();
	}


}
