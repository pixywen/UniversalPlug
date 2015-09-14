package com.tatian.up.event;

import com.tatian.up.entity.User;

public class UserGetInfoEvent implements Event{

	public static final int SUCCESS = 0;
	public static final int FAILED = 1;
	
	private int result;
	private User user;

	public UserGetInfoEvent(int result, User user) {
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
