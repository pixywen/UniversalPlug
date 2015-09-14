package com.tatian.up.event;

import com.tatian.up.entity.User;

/**
 * 用户登录成功时调用该事件
 */
public final class UserLoginEvent implements Event {
	public static final int SUCCESS = 0;
	public static final int USER_CANCEL = 1;
	public static final int FAILED = 2;
	private int result;
	private User user;

	public UserLoginEvent(int result, User user) {
		this.result = result;
		this.user = user;
	}

	public int getResult() {
		return result;
	}

	public User getUser() {
		return user;
	}

}
