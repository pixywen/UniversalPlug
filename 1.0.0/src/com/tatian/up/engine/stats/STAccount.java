package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STAccount extends NotProguard{

	public void AccountSetID(String accountId);

	public void AccountSetType(String type);

	public void AccountLogin(String accountId);

	public void AccountLogin(String accountId, String gameServer);

	public void AccountLogout(String accountId);

	public void AccountSetAge(int age);

	public void AccountSetGender(int gender);

	public void AccountSetGameServer(String gameServer);

	public void AccountSetLevel(int level);

}
