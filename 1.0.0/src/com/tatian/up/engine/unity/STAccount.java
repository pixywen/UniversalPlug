package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STAccount extends UnityBridge {

	/**
	 * 设置用户ID
	 * 
	 * @param accountId
	 *            唯一识别码
	 */
	public static void AccountSetID(String accountId) {
		Debug.d("Platform ST STAccount.AccountSetID");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetID(accountId);
	}

	/**
	 * 用户类型
	 * 
	 * @param type
	 */
	public static void AccountSetType(String type) {
		Debug.d("Platform ST STAccount.AccountSetType");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetType(type);
	}

	/**
	 * 用户登录
	 * 
	 * @param accountId
	 */
	public static void AccountLogin(String accountId) {
		Debug.d("Platform ST STAccount.AccountLogin");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountLogin(accountId);
	}

	/**
	 * 用户登录(同时登录服务器)
	 * 
	 * @param accountId
	 * @param gameServer
	 */
	public static void AccountLogin(String accountId, String gameServer) {
		Debug.d("Platform ST STAccount.AccountLogin");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountLogin(accountId, gameServer);
	}

	/**
	 * 用户注销
	 * 
	 * @param accountId
	 */
	public static void AccountLogout(String accountId) {
		Debug.d("Platform ST STAccount.AccountLogout");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountLogout(accountId);
	}

	/**
	 * 用户年龄
	 * @param age
	 */
	public static void AccountSetAge(int age) {
		Debug.d("Platform ST STAccount.AccountSetAge");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetAge(age);
	}

	
	/**
	 * 用户性别
	 * @param gender
	 */
	public static void AccountSetGender(int gender) {
		Debug.d("Platform ST STAccount.AccountSetGender");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetGender(gender);
	}

	/**
	 * 服务器ID
	 * @param gameServer
	 */
	public static void AccountSetGameServer(String gameServer) {
		Debug.d("Platform ST STAccount.AccountSetGender");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetGameServer(gameServer);
	}

	/**
	 * 用户等级
	 * @param level
	 */
	public static void AccountSetLevel(int level) {
		Debug.d("Platform ST STAccount.AccountSetLevel");
		UniversalPlugPlatform.getInstance().getStatsManager().STAccount
				.AccountSetLevel(level);
	}

}
