package com.tatian.up.engine.manager.impl;

import java.util.Map;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.AbstractStatisticsManager;
import com.tatian.up.utils.Debug;

public class StatisticsManagerImpl extends AbstractStatisticsManager{

	public StatisticsManagerImpl(UniversalPlugRunConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
		Debug.d("Statistics","StatisticsManagerImpl");
	}

	@Override
	protected void doLevelBegin(String level, String type) {
		// TODO Auto-generated method stub
		Debug.d("Statistics","doLevelBegin");
	}

	@Override
	protected void doLevelComplete(String level) {
		// TODO Auto-generated method stub
		Debug.d("Statistics","doLevelComplete");
		
	}

	@Override
	protected void doLevelFailed(String level, String reason) {
		// TODO Auto-generated method stub
		Debug.d("Statistics","doLevelFailed");
		
	}

	@Override
	protected void doCurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEvent(String eventid, Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEvent(String eventid, Map<String, String> map, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEventBegin(String eventid, Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEventEndEventEnd(String eventid, Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEventDuration(String eventid, Map<String, String> map,
			long duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetID(String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountLogin(String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountLogin(String accountId, String gameServer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountLogout(String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetAge(int age) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetGender(int gender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetGameServer(String gameServer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doAccountSetLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCoinSetNum(long coinNum, String coinType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCoinGain(String id, String coinType, long gain, long left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCoinLost(String id, String coinType, long lost, long left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doItemGet(String itemId, String itemType, int itemCnt,
			String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doItemConsume(String itemId, String itemType, int itemCnt,
			String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doItemBuy(String itemId, String itemType, int itemCnt,
			int vituralCurrency, String currencyType, String consumePoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doTaskBegin(String task, String types) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doTaskComplete(String task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doTaskFailed(String task, String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType,
			String goodsName, String goodsCoin, int goodsNum, String level,
			String rank) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doCurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType, String goodsName,
			String goodsCoin, int goodsNum, String level, String rank) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doItemConsume(String itemId, String itemType,
			int itemCnt, String reason, int itemCoin, String coinType,
			String level) {
		// TODO Auto-generated method stub
		
	}

	
	

}
