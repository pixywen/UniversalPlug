package com.tatian.up.engine.manager.impl;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tatian.up.engine.controller.Constants;
import com.tatian.up.engine.controller.UniversalPlugController;
import com.tatian.up.manager.ExceptionManager;
import com.tatian.up.utils.Debug;

public class ExceptionManagerImpl implements ExceptionManager {

	private UncaughtExceptionHandler exceptionHandler;

	public ExceptionManagerImpl() {
		exceptionHandler = new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread thread, Throwable ex) {
				notifyException(thread, ex);
			}
		};
	}

	@Override
	public void enableAutoCatchException() {
		Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
	}

	@Override
	public void notifyException(Throwable exception) {
		notifyException(Thread.currentThread(), exception);
	}

	@Override
	public void notifyException(Thread thread, final Throwable exception) {
		Debug.w("--------------------Exception--------------------");
		Debug.w("Thread:" + thread.getName());
		Debug.w(Log.getStackTraceString(exception));
		if (Constants.DEBUG) {
			logException(thread, exception);
		} else {
			pingbackException(exception);
		}
	}

	private void logException(final Thread thread, final Throwable exception) {
		StringBuilder builder = new StringBuilder();
		builder.append("Thread:\r\n");
		builder.append(thread.getName());
		builder.append("\r\n\r\nStackTrace:\r\n");
		builder.append(Log.getStackTraceString(exception));
		SharedPreferences.Editor editor = UniversalPlugController.getInstance().getContext().getSharedPreferences("SevengaException", Context.MODE_PRIVATE).edit();
		editor.putBoolean("exception", true);
		editor.putString("msg", builder.toString());
		editor.commit();
		System.exit(0);
	}

	private void pingbackException(Throwable exception) {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("name", exception.getClass().getName());
			paramMap.put("content", Log.getStackTraceString(exception));
			paramMap.put("app_version", UniversalPlugController.getInstance().getSystemInfo().app_version);
			paramMap.put("os_version", UniversalPlugController.getInstance().getSystemInfo().os_version);
			paramMap.put("model", UniversalPlugController.getInstance().getSystemInfo().model);
			paramMap.put("os_name", UniversalPlugController.getInstance().getSystemInfo().os_name);
		} catch (Exception e) {
		} finally {
			System.exit(0);
		}
	}

}
