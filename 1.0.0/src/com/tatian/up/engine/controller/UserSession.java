package com.tatian.up.engine.controller;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.entity.User;
import com.tatian.up.event.SwitchUserEvent;
import com.tatian.up.event.UserLoginEvent;
import com.tatian.up.event.UserLogoutEvent;
import com.tatian.up.event.UserRegisterEvent;

public class UserSession {
	private String loginToken;
	private User activeUser;

	public User getActiveUser() {
		return activeUser;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void Clear() {

		loginToken = null;
		activeUser = null;
	}

	public void notifyLogoutNotLogin() {
		UniversalPlugController
				.getInstance()
				.getEventManager()
				.dispatchEvent(
						new UserLogoutEvent(UserLogoutEvent.NOT_LOGIN, null));

	}

	public void notifyLogoutSuccess(User tempUser) {
		UniversalPlugController
				.getInstance()
				.getEventManager()
				.dispatchEvent(
						new UserLogoutEvent(UserLogoutEvent.SUCCESS, tempUser));
		UniversalPlugPlatform.getInstance().getToolbarManager().hide();
	}

	public void notifyRegisterSuccess(String username, String password,
			String displayName) {
		User user = new UserImpl("NO_UID", displayName, "NO_TOKEN", null, null);
		UniversalPlugController.getInstance().getEventManager()
				.dispatchEvent(new UserRegisterEvent(user));
	}

	public void notifyLoginSuccess(String userId, String displayName,
			String loginToken, String tpName, String tpUserId) {
		this.loginToken = loginToken;
		User user = new UserImpl(userId, displayName, loginToken, tpName,
				tpUserId);
		if (activeUser == null) {
			activeUser = user;
			UniversalPlugController
					.getInstance()
					.getEventManager()
					.dispatchEvent(
							new UserLoginEvent(UserLoginEvent.SUCCESS, user));
			UniversalPlugController.getInstance().getEventManager()
					.dispatchEvent(new SwitchUserEvent(null, user));
		} else {
			User tempUser = activeUser;
			activeUser = user;
			UniversalPlugController
					.getInstance()
					.getEventManager()
					.dispatchEvent(
							new UserLogoutEvent(UserLogoutEvent.SUCCESS,
									tempUser));
			UniversalPlugController
					.getInstance()
					.getEventManager()
					.dispatchEvent(
							new UserLoginEvent(UserLoginEvent.SUCCESS, user));
			UniversalPlugController.getInstance().getEventManager()
					.dispatchEvent(new SwitchUserEvent(tempUser, user));
		}

	}

	private final class UserImpl implements User {
		private String userId;
		private String displayName;
		private String token;
		private String tpName;
		private String tpUserId;

		public UserImpl(String userId, String displayName, String token,
				String tpName, String tpUserId) {
			this.token = token;
			this.userId = userId;
			this.displayName = displayName;
			this.tpName = tpName;
			this.tpUserId = tpUserId;
		}

		@Override
		public String getUserId() {
			return userId;
		}

		@Override
		public String getDisplayName() {
			return displayName;
		}

		@Override
		public String getToken() {
			return token;
		}

		@Override
		public String getThirdPlatformName() {
			return tpName;
		}

		@Override
		public String getThirdPlatformUserId() {
			return tpUserId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((userId == null) ? 0 : userId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserImpl other = (UserImpl) obj;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}

	}
}
