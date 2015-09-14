package com.tatian.up.event.handler;

import com.tatian.up.entity.User;
import com.tatian.up.event.Handle;
import com.tatian.up.event.UserLoginEvent;

public abstract class UserLoginHandler implements OnceEventHandler {
	@Handle(UserLoginEvent.class)
	private void onUserLogin(UserLoginEvent event) {
		switch (event.getResult()) {
		case UserLoginEvent.SUCCESS:
			onLoginSuccess(event.getUser());
			break;
		case UserLoginEvent.USER_CANCEL:
			onUserCancel();
			break;
		case UserLoginEvent.FAILED:
			onLoginFailed();
			break;
		}
	}

	protected abstract void onLoginSuccess(User user);

	protected abstract void onUserCancel();

	protected abstract void onLoginFailed();
}
