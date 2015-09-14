package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STCoin extends NotProguard{
	
	public void CoinSetNum(long coinNum,String coinType);
	public void CoinGain(String id,String coinType,long gain,long left);
	public void CoinLost(String id,String coinType,long lost,long left);

}
