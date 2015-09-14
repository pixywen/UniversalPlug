package com.tatian.up.event;

import com.tatian.up.entity.User;

/**
 * 切换用户时调用该事件
 */
public final class SwitchUserEvent implements Event {
	private User oldUser;
	private User newUser;

	public SwitchUserEvent(User oldUser, User newUser) {
		this.oldUser = oldUser;
		this.newUser = newUser;
	}

	public User getOldUser() {
		return oldUser;
	}

	public User getNewUser() {
		return newUser;
	}

}
