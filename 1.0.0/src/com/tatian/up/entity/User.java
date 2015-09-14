package com.tatian.up.entity;

import com.tatian.up.utils.NotProguard;

public interface User extends NotProguard {
	/**
	 * @return 获得用户Id
	 */
	String getUserId();

	/**
	 * @return 获得用户昵称
	 */
	String getDisplayName();

	/**
	 * @return 获得登录Token
	 */
	String getToken();

	/**
	 * @return 获得第三方平台名称
	 */
	String getThirdPlatformName();

	/**
	 * @return 获得第三方用户ID
	 */
	String getThirdPlatformUserId();

}
