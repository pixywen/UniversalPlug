package com.tatian.up.event.handler;

import com.tatian.up.entity.User;
import com.tatian.up.event.Handle;
import com.tatian.up.event.UserLoginEvent;
import com.tatian.up.event.UserLogoutEvent;

public abstract class SwitchUserHandler implements OnceEventHandler {

	@Handle(UserLoginEvent.class)
	private void onUserLogin(UserLoginEvent event) {
		switch (event.getResult()) {
		case UserLoginEvent.SUCCESS:
			onNewUserLogin(event.getUser());
			break;
		case UserLoginEvent.USER_CANCEL:
			onUserCancel();
			break;
		case UserLoginEvent.FAILED:
			onLoginFailed();
			break;
		}
	}

	@Handle(UserLogoutEvent.class)
	private void onUserLogout(UserLogoutEvent event) {
		onOldUserLogout(event.getUser());
	}

	protected abstract void onUserCancel();

	protected abstract void onNewUserLogin(User user);

	protected abstract void onOldUserLogout(User user);

	protected abstract void onLoginFailed();
}
