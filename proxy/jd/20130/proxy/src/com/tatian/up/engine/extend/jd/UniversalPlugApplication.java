package com.tatian.up.engine.extend.jd;

import android.app.Application;

import com.tatian.up.utils.Debug;

public class UniversalPlugApplication extends Application implements com.tatian.up.utils.NotProguard {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Debug.d("JDUniversalPlugApplication OnCreate");
		super.onCreate();
		try {
			System.loadLibrary("megjb");
		} catch (Exception e) {

		}

	}

}
