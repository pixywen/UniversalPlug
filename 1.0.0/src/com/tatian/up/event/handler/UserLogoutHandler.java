package com.tatian.up.event.handler;

import com.tatian.up.entity.User;
import com.tatian.up.event.Handle;
import com.tatian.up.event.UserLogoutEvent;

public abstract class UserLogoutHandler implements OnceEventHandler {
	@Handle(UserLogoutEvent.class)
	private void onUserLogout0(UserLogoutEvent event) {
		switch (event.getResult()) {
		case UserLogoutEvent.SUCCESS:
			onLogoutSuccess(event.getUser());
			break;
		case UserLogoutEvent.NOT_LOGIN:
			onUserNotLogin();
			break;
		}
	}

	protected abstract void onUserNotLogin();

	protected abstract void onLogoutSuccess(User user);
}
