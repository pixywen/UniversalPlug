package com.tatian.up.service;

import com.tatian.up.ui.ExceptionActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class UniversalPlugService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		SharedPreferences sp = getSharedPreferences("UniversalPlugException", Context.MODE_PRIVATE);
		if (sp.getBoolean("exception", false)) {
			Intent intent = new Intent(this, ExceptionActivity.class);
			intent.putExtra("Exception", sp.getString("msg", "Read no msg."));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean("exception", false);
			editor.commit();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
