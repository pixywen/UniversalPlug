package com.tatian.up.manager;

import android.app.Activity;

import com.tatian.up.entity.User;
import com.tatian.up.event.handler.SwitchUserHandler;
import com.tatian.up.event.handler.UserLoginHandler;
import com.tatian.up.event.handler.UserLogoutHandler;
import com.tatian.up.utils.NotProguard;

/**
 * 控制用户的Manager
 */
public interface UserManager extends NotProguard {
	/**
	 * 获取当前登录的用户
	 * 
	 * @return 当前登录的用户
	 */
	User getActiveUser();

	/**
	 * 设置是否进行自动登录
	 */
	void enableAutoLogin(boolean enabled);

	/**
	 * 请求用户中心,调用该方法会调出用户中心的Activity
	 */
	//void requestUserCenter(Activity activity, UserInfoHandler handler);
	
	/**
	 * 请求登录,调用该方法会弹出登录的Activity
	 */
	void requestLogin(Activity activity, UserLoginHandler handler);

	/**
	 * 请求登出
	 */
	void requestLogout(UserLogoutHandler handler);

	/**
	 * 请求切换账号
	 */
	void requestSwitchUser(Activity activity, SwitchUserHandler handler);

	/**
	 * 传递游戏用户信息
	 */
	void bindGameUserInfo(GameUserInfo gameUserInfo);

	public static class GameUserInfo implements NotProguard {
		private String username;
		private String userId;
		private String serverId;

		public String getUsername() {
			return username;
		}

		public GameUserInfo setUsername(String username) {
			this.username = username;
			return this;
		}

		public String getUserId() {
			return userId;
		}

		public GameUserInfo setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public String getServerId() {
			return serverId;
		}

		public GameUserInfo setServerId(String serverId) {
			this.serverId = serverId;
			return this;
		}

	}
}
