package com.tatian.up.engine.extend.fusion;

import android.app.Application;

import com.tatian.up.utils.Debug;
import com.unicom.dcLoader.Utils;
import com.unicom.dcLoader.Utils.UnipayPayResultListener;

public class UniversalPlugApplication extends Application implements
		com.tatian.up.utils.NotProguard {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Debug.d("FusionApplication OnCreate");
		
		try {
			System.loadLibrary("megjb");
		} catch (Exception e) {

		}

		Utils.getInstances().initSDK(this, new UnipayPayResultListener() {

			@Override
			public void PayResult(String arg0, int arg1, int arg2, String arg3) {
				// TODO Auto-generated method stub

			}

		});

		super.onCreate();
	}

}
