package com.tatian.up.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SIMInfoUtils implements Carrier{

	private static SIMInfoUtils instance;
	private TelephonyManager telephonyManager;

	public static SIMInfoUtils getInstance() {
		if (instance == null)
			 instance=new SIMInfoUtils();
		return instance;
	}

	public void init(Context context) {

		if (telephonyManager == null)
			telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

	}

	public String getNativePhoneNumber() {
		if (telephonyManager == null)
			return "none";
		String NativePhoneNumber = null;

		NativePhoneNumber = telephonyManager.getLine1Number();
		return NativePhoneNumber;
	}

	public int getCarriers() {
		
		Debug.d("IMSI");
		if (telephonyManager == null)
			return Carrier.UNKNOW;
		String IMSI = telephonyManager.getSubscriberId();

		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		if (IMSI == null)
			return Carrier.UNKNOW;
		
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
			return Carrier.CHINA_MOBILE;
		} else if (IMSI.startsWith("46001")|| IMSI.startsWith("46006")) {
			return Carrier.CHINA_UNICOM;
		} else if (IMSI.startsWith("46003") || IMSI.startsWith("46005") || IMSI.startsWith("46011")) {
			return Carrier.CHINA_TELECOM;
		}
		return Carrier.UNKNOW;
	}

}
