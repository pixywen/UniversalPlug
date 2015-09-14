package com.tatian.up.event.handler;

import com.tatian.up.entity.User;
import com.tatian.up.event.Handle;
import com.tatian.up.event.UserGetInfoEvent;

public abstract class UserInfoHandler implements OnceEventHandler {

	@Handle(UserGetInfoEvent.class)
	private void onUserLogin(UserGetInfoEvent event) {
		switch (event.getResult()) {
		case UserGetInfoEvent.SUCCESS:
			onGetInfoSuccess(event.getUser());
			break;
		case UserGetInfoEvent.FAILED:
			onGetInfoFailed();
			break;
		}
	}
	
	protected abstract void onGetInfoSuccess(User user);

	protected abstract void onGetInfoFailed();
}
