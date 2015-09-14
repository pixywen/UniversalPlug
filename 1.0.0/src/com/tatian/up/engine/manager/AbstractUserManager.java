package com.tatian.up.engine.manager;

import java.lang.ref.SoftReference;

import android.app.Activity;

import com.tatian.up.engine.controller.UniversalPlugController;
import com.tatian.up.entity.User;
import com.tatian.up.event.UserLoginEvent;
import com.tatian.up.event.handler.SwitchUserHandler;
import com.tatian.up.event.handler.UserLoginHandler;
import com.tatian.up.event.handler.UserLogoutHandler;
import com.tatian.up.manager.UserManager;

public abstract class AbstractUserManager implements UserManager {
	protected boolean autoLogin = true;
	protected SoftReference<Activity> activityRef;

	protected Activity getActivityContext() {
		if (activityRef == null) {
			return (Activity) UniversalPlugController.getInstance().getContext();
		}
		if (activityRef.get() == null) {
			return (Activity) UniversalPlugController.getInstance().getContext();
		}
		return activityRef.get();
	}

	@Override
	public void bindGameUserInfo(GameUserInfo gameUserInfo) {

	}

	@Override
	public User getActiveUser() {
		return null;
	}

	@Override
	public void enableAutoLogin(boolean enabled) {
		autoLogin = enabled;
	}

	protected void requestAutoLogin() {
		if (autoLogin) {
			doLogin();
		} else {
			doSwitchUser();
		}
	}

	@Override
	public void requestLogin(Activity activity, UserLoginHandler handler) {
		activityRef = new SoftReference<Activity>(activity);
		UniversalPlugController.getInstance().getActivityManager()
				.notifyActivityActive(activity);
		if (handler != null) {
			UniversalPlugController.getInstance().getEventManager()
					.registerEventHandler(handler);
		}
		requestAutoLogin();
	}

	@Override
	public void requestLogout(UserLogoutHandler handler) {
		if (handler != null) {
			UniversalPlugController.getInstance().getEventManager()
					.registerEventHandler(handler);
		}
		doLogout();
	}

	@Override
	public void requestSwitchUser(Activity activity, SwitchUserHandler handler) {
		activityRef = new SoftReference<Activity>(activity);
		if (handler != null) {
			UniversalPlugController.getInstance().getEventManager()
					.registerEventHandler(handler);
		}
		doSwitchUser();
	}

	protected void notifyLoginCancel() {
		UniversalPlugController
				.getInstance()
				.getEventManager()
				.dispatchEvent(
						new UserLoginEvent(UserLoginEvent.USER_CANCEL, null));
	}

	protected void notifyLoginFailed() {
		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new UserLoginEvent(UserLoginEvent.FAILED, null));

	}

	protected abstract void doLogin();

	protected abstract void doLogout();

	protected abstract void doSwitchUser();

}
