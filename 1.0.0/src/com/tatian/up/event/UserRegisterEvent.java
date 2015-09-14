package com.tatian.up.event;

import com.tatian.up.entity.User;

/**
 * 用户注册成功时调用该事件
 */
public final class UserRegisterEvent implements Event {
	private User user;

	public UserRegisterEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
