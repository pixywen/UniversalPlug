package com.tatian.up.engine.controller;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.http.conn.util.InetAddressUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.MD5Util;
import com.tatian.up.utils.NotProguard;

public class SystemInfo implements NotProguard {

	public SystemInfo(UniversalPlugRunConfig config) {
		init(config.getContext());
	}

	public Map<String, String> getSimpleSystemInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", UniversalPlugController.getInstance().getAppId());
		map.put("udid", udid);
		map.put("connection_type", getConnectionType());
		return map;
	}

	public Map<String, String> getTotalSystemInfo() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Field field : this.getClass().getFields()) {
				Object obj = field.get(this);
				if (obj instanceof String) {
					String value = (String) obj;
					map.put(field.getName(), value == null ? "" : value);
				}
			}
			map.put("app_id", UniversalPlugController.getInstance().getAppId());
			
		} catch (Exception e) {
			Debug.w(e);
			Debug.w("SystemInfo", "Make SystemInfoMap Failed.");
		}
		return map;
	}

	public String app_version;
	public String os_name;
	public String os_version;
	public String os_lang;
	public String ip;
	public String mac;
	public String connection_type;
	public String screen_resolution;
	public String mobile_operator;
	public String carrier;
	public String brand;
	public String manufacturer;
	public String model;
	public String imei;
	public String imsi;
	public String android_id;
	public String device_serial;
	public String country;
	public String udid;

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void init(Context context) {
		TelephonyManager tm = (TelephonyManager) (context.getSystemService(Context.TELEPHONY_SERVICE));
		WifiManager wifiManager = (WifiManager) (context.getSystemService(Context.WIFI_SERVICE));
		NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		windowManager.getDefaultDisplay().getMetrics(dm);

		os_name = "Android";
		os_version = Build.VERSION.RELEASE;
		os_lang = Locale.getDefault().getLanguage();
		ip = getLocalIpAddress();
		mac = wifiManager.getConnectionInfo().getMacAddress();
		connection_type = networkInfo == null ? "NO_NETWORK" : networkInfo.getTypeName();
		screen_resolution = windowManager.getDefaultDisplay().getHeight() + "x" + windowManager.getDefaultDisplay().getWidth() + "x" + dm.densityDpi;
		mobile_operator = tm.getSimOperatorName();
		carrier = tm.getNetworkOperatorName();
		brand = Build.BRAND;
		manufacturer = Build.MANUFACTURER;
		model = Build.MODEL;
		imei = tm.getDeviceId();
		imsi = tm.getSubscriberId();
		android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		device_serial = Build.VERSION.SDK_INT >= 9 ? Build.SERIAL : "NO_SERIAL";
		country = tm.getSimCountryIso();
		udid = generateUDID();

		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			app_version = packageInfo.versionName + "_" + packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			Debug.w(e);
		}
	}

	private String getConnectionType() {
		NetworkInfo networkInfo = ((ConnectivityManager) (UniversalPlugController.getInstance().getContext()).getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkInfo == null ? "NO_NETWORK" : networkInfo.getTypeName();
	}

	private String getLocalIpAddress() {
		try {
			String ipv4;
			ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface network : list) {
				ArrayList<InetAddress> addressList = Collections.list(network.getInetAddresses());
				for (InetAddress address : addressList) {
					if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = address.getHostAddress())) {
						return ipv4;
					}
				}
			}
		} catch (SocketException ex) {
		}
		return "UNKNOWN_IP";
	}

	@SuppressLint("NewApi")
	protected String generateUDID() {
		StringBuilder builder = new StringBuilder();
		builder.append(imei);
		builder.append(android_id);
		builder.append(Build.VERSION.SDK_INT >= 9 ? Build.SERIAL : "NO_SERIAL");
		return MD5Util.md5(builder.toString());
	}

}
