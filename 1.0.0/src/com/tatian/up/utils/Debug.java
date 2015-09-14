package com.tatian.up.utils;

import android.util.Log;

import com.tatian.up.engine.controller.Constants;

public class Debug implements NotProguard{
	public static boolean LOG_FLAG = false;
	public static boolean debug = LOG_FLAG || Constants.DEBUG;
	public static boolean info = LOG_FLAG || Constants.DEBUG;

	private static Logger[] loggers = new Logger[] { new LogcatLogger() };

	public static void i(String message) {
		i(null, message);
	}

	public static void i(String tag, String message) {
		if (info) {
			for (Logger logger : loggers) {
				logger.i(makeTag(tag), message);
			}
		}
	}

	public static void d(String message) {
		e(null, message);
	}

	public static void d(String tag, String message) {
		if (debug) {
			for (Logger logger : loggers) {
				logger.d(makeTag(tag), message);
			}
		}
	}

	public static void w(String tag, Exception e) {
		if (e != null) {
			w(tag, "Cause:" + //
					(e.getCause() == null ? "Unknown Cause" : e.getCause().getLocalizedMessage()) + //
					"\r\nStackInfo:" + //
					Log.getStackTraceString(e)//
			);
		} else {
			w(tag, "Null Exception");
		}
	}

	public static void e(Exception e) {
		e(null, e);
	}

	public static void e(String message) {
		e(null, message);
	}

	public static void e(String tag, String message) {
		for (Logger logger : loggers) {
			logger.e(makeTag(tag), message);
		}
	}

	public static void e(String tag, Exception e) {
		if (e != null) {
			e(tag, "Cause:" + //
					(e.getCause() == null ? "Unknown Cause" : e.getCause().getLocalizedMessage()) + //
					"\r\nStackInfo:" + //
					Log.getStackTraceString(e)//
			);
		} else {
			e(tag, "Null Exception");
		}
	}

	public static void w(Exception e) {
		w(null, e);
	}

	public static void w(String message) {
		w(null, message);
	}

	public static void w(String tag, String message) {
		for (Logger logger : loggers) {
			logger.w(makeTag(tag), message);
		}
	}

	private static String makeTag(String tag) {
		if (tag == null || tag.trim().equals("")) {
			return "___UniversalPlugSDK___";
		} else {
			return "___UniversalPlugSDK-" + tag+"___";
		}
	}

	private static interface Logger {

		void d(String tag, String message);

		void i(String tag, String message);

		void w(String tag, String message);

		void e(String tag, String message);

	}

	private static class LogcatLogger implements Logger {

		@Override
		public void d(String tag, String message) {
			Log.d(tag, message);
		}

		@Override
		public void i(String tag, String message) {
			Log.i(tag, message);
		}

		@Override
		public void w(String tag, String message) {
			Log.w(tag, message);
		}

		@Override
		public void e(String tag, String message) {
			Log.e(tag, message);
		}

	}

}
