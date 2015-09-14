package com.tatian.up.engine.manager.impl;

import com.tatian.up.engine.manager.AbstractUserManager;
import com.tatian.up.utils.Debug;

public class UserManagerImpl extends AbstractUserManager{
	
	

	public UserManagerImpl() {
		super();
		// TODO Auto-generated constructor stub
		Debug.e("Statistics","UserManagerImpl");
		
	}

	@Override
	protected void doLogin() {
		// TODO Auto-generated method stub
		
		notifyLoginFailed();
		
	}

	@Override
	protected void doLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doSwitchUser() {
		// TODO Auto-generated method stub
		
	}

}
