package com.tatian.up.utils;

public class EncryptUtil implements NotProguard {
	static {
		System.loadLibrary("NativeEncrypt");
	}


	public static native byte[] nativeDecode(String str);
	
	

}
