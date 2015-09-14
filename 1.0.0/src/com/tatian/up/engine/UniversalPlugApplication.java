package com.tatian.up.engine;

import android.app.Application;
import android.content.Context;

import com.tatian.up.utils.Debug;
import com.tatian.up.utils.NotProguard;

public class UniversalPlugApplication extends Application implements NotProguard{

	
	@Override
	public Context getApplicationContext() {
		// TODO Auto-generated method stub
		return super.getApplicationContext();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Debug.d("BaseUniversalPlugApplication onCreate");
	}
	
	

}
