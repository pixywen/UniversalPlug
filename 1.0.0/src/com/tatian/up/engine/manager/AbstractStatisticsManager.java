package com.tatian.up.engine.manager;

import java.util.Map;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.stats.STAccount;
import com.tatian.up.engine.stats.STCoin;
import com.tatian.up.engine.stats.STEvent;
import com.tatian.up.engine.stats.STItem;
import com.tatian.up.engine.stats.STLevel;
import com.tatian.up.engine.stats.STTask;
import com.tatian.up.engine.stats.STVirtualCurrency;
import com.tatian.up.manager.StatisticsManager;

public abstract class AbstractStatisticsManager implements StatisticsManager {

	public static STAccount STAccount;
	public static STCoin STCoin;
	public static STEvent STEvent;
	public static STItem STItem;
	public static STLevel STLevel;
	public static STTask STTask;
	public static STVirtualCurrency STVirtualCurrency;

	private static AbstractStatisticsManager instance;

	/**
	 * 暂留
	 * 
	 * @return
	 */
	private static AbstractStatisticsManager getInstance() {

		if (instance == null) {
			throw new RuntimeException("StatisticsManager has not init.");
		}
		return instance;

	}


	public AbstractStatisticsManager(UniversalPlugRunConfig config) {

		STAccount = new STAccountImpl();
		STCoin = new STCoinImpl();
		STEvent = new STEventImpl();
		STItem = new STItemImpl();
		STLevel = new STLevelImpl();
		STTask = new STTaskImpl();
		STVirtualCurrency = new STVirtualCurrencyImpl();

	}

	public class STLevelImpl implements STLevel {

		@Override
		public void LevelBegin(String level, String type) {
			// TODO Auto-generated method stub
			doLevelBegin(level, type);
		}

		@Override
		public void LevelComplete(String level) {
			// TODO Auto-generated method stub
			doLevelComplete(level);

		}

		@Override
		public void LevelFailed(String level, String reason) {
			// TODO Auto-generated method stub
			doLevelFailed(level, reason);
		}

	}

	protected abstract void doLevelBegin(String level, String type);

	protected abstract void doLevelComplete(String level);

	protected abstract void doLevelFailed(String level, String reason);

	public class STVirtualCurrencyImpl implements STVirtualCurrency {

		@Override
		public void CurrencyPaymentSuccess(String orderId,
				double currencyAmount, String currencyType, String paymentType) {
			// TODO Auto-generated method stub
			doCurrencyPaymentSuccess(orderId, currencyAmount, currencyType,
					paymentType);
		}

		@Override
		public void CurrencyPaymentSuccess(double currencyAmount,
				String currencyType, String paymentType) {
			// TODO Auto-generated method stub
			doCurrencyPaymentSuccess(currencyAmount, currencyType, paymentType);

		}

		@Override
		public void CurrencyPaymentSuccess(String orderId,
				double currencyAmount, String currencyType, String paymentType,
				String goodsName, String goodsCoin, int goodsNum, String level,
				String rank) {
			// TODO Auto-generated method stub
			doCurrencyPaymentSuccess(orderId, currencyAmount, currencyType,
					paymentType, goodsName, goodsCoin, goodsNum, level, rank);
		}

		@Override
		public void CurrencyPaymentSuccess(double currencyAmount,
				String currencyType, String paymentType, String goodsName,
				String goodsCoin, int goodsNum, String level, String rank) {
			// TODO Auto-generated method stub
			doCurrencyPaymentSuccess(currencyAmount, currencyType, paymentType,
					goodsName, goodsCoin, goodsNum, level, rank);

		}

	}

	protected abstract void doCurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType);

	protected abstract void doCurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType);

	protected abstract void doCurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType,
			String goodsName, String goodsCoin, int goodsNum, String level,
			String rank);

	protected abstract void doCurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType, String goodsName,
			String goodsCoin, int goodsNum, String level, String rank);

	public class STEventImpl implements STEvent {

		@Override
		public void Event(String eventid, Map<String, String> map) {
			// TODO Auto-generated method stub
			doEvent(eventid, map);
		}

		@Override
		public void Event(String eventid, Map<String, String> map, int count) {
			// TODO Auto-generated method stub

			doEvent(eventid, map, count);

		}

		@Override
		public void EventBegin(String eventid, Map<String, String> map) {
			// TODO Auto-generated method stub

			doEventBegin(eventid, map);

		}

		@Override
		public void EventEnd(String eventid, Map<String, String> map) {
			// TODO Auto-generated method stub

			doEventEndEventEnd(eventid, map);

		}

		@Override
		public void EventDuration(String eventid, Map<String, String> map,
				long duration) {
			// TODO Auto-generated method stub
			doEventDuration(eventid, map, duration);

		}

	}

	protected abstract void doEvent(String eventid, Map<String, String> map);

	protected abstract void doEvent(String eventid, Map<String, String> map,
			int count);

	protected abstract void doEventBegin(String eventid, Map<String, String> map);

	protected abstract void doEventEndEventEnd(String eventid,
			Map<String, String> map);

	protected abstract void doEventDuration(String eventid,
			Map<String, String> map, long duration);

	public class STAccountImpl implements STAccount {

		@Override
		public void AccountSetID(String accountId) {
			// TODO Auto-generated method stub
			doAccountSetID(accountId);

		}

		@Override
		public void AccountSetType(String type) {
			// TODO Auto-generated method stub
			doAccountSetType(type);
		}

		@Override
		public void AccountLogin(String accountId) {
			// TODO Auto-generated method stub
			doAccountLogin(accountId);
		}

		@Override
		public void AccountLogin(String accountId, String gameServer) {
			// TODO Auto-generated method stub
			doAccountLogin(accountId, gameServer);
		}

		@Override
		public void AccountLogout(String accountId) {
			// TODO Auto-generated method stub
			doAccountLogout(accountId);

		}

		@Override
		public void AccountSetAge(int age) {
			// TODO Auto-generated method stub
			doAccountSetAge(age);
		}

		@Override
		public void AccountSetGender(int gender) {
			// TODO Auto-generated method stub
			doAccountSetGender(gender);
		}

		@Override
		public void AccountSetGameServer(String gameServer) {
			// TODO Auto-generated method stub
			doAccountSetGameServer(gameServer);

		}

		@Override
		public void AccountSetLevel(int level) {
			// TODO Auto-generated method stub
			doAccountSetLevel(level);
		}

	}

	protected abstract void doAccountSetID(String accountId);

	protected abstract void doAccountSetType(String type);

	protected abstract void doAccountLogin(String accountId);

	protected abstract void doAccountLogin(String accountId, String gameServer);

	protected abstract void doAccountLogout(String accountId);

	protected abstract void doAccountSetAge(int age);

	protected abstract void doAccountSetGender(int gender);

	protected abstract void doAccountSetGameServer(String gameServer);

	protected abstract void doAccountSetLevel(int level);

	public class STCoinImpl implements STCoin {

		@Override
		public void CoinSetNum(long coinNum, String coinType) {
			// TODO Auto-generated method stub
			doCoinSetNum(coinNum, coinType);
		}

		@Override
		public void CoinGain(String id, String coinType, long gain, long left) {
			// TODO Auto-generated method stub
			doCoinGain(id, coinType, gain, left);
		}

		@Override
		public void CoinLost(String id, String coinType, long lost, long left) {
			// TODO Auto-generated method stub

			doCoinLost(id, coinType, lost, left);

		}

	}

	protected abstract void doCoinSetNum(long coinNum, String coinType);

	protected abstract void doCoinGain(String id, String coinType, long gain,
			long left);

	protected abstract void doCoinLost(String id, String coinType, long lost,
			long left);

	public class STItemImpl implements STItem {

		@Override
		public void ItemGet(String itemId, String itemType, int itemCnt,
				String reason) {
			// TODO Auto-generated method stub
			doItemGet(itemId, itemType, itemCnt, reason);

		}

		@Override
		public void ItemConsume(String itemId, String itemType, int itemCnt,
				String reason) {
			// TODO Auto-generated method stub
			doItemConsume(itemId, itemType, itemCnt, reason);
		}

		@Override
		public void ItemBuy(String itemId, String itemType, int itemCnt,
				int vituralCurrency, String currencyType, String consumePoint) {
			// TODO Auto-generated method stub
			doItemBuy(itemId, itemType, itemCnt, vituralCurrency, currencyType,
					consumePoint);
		}

		@Override
		public void ItemConsume(String itemId, String itemType, int itemCnt,
				String reason, int itemCoin, String coinType, String level) {
			// TODO Auto-generated method stub
			doItemConsume(itemId, itemType, itemCnt, reason,
					itemCoin, coinType, level);
		}

	}

	protected abstract void doItemGet(String itemId, String itemType,
			int itemCnt, String reason);

	protected abstract void doItemConsume(String itemId, String itemType,
			int itemCnt, String reason);

	protected abstract void doItemConsume(String itemId,
			String itemType, int itemCnt, String reason, int itemCoin,
			String coinType, String level);

	protected abstract void doItemBuy(String itemId, String itemType,
			int itemCnt, int vituralCurrency, String currencyType,
			String consumePoint);

	public class STTaskImpl implements STTask {

		@Override
		public void TaskBegin(String task, String type) {
			// TODO Auto-generated method stub
			doTaskBegin(task, type);
		}

		@Override
		public void TaskComplete(String task) {
			// TODO Auto-generated method stub

			doTaskComplete(task);
		}

		@Override
		public void TaskFailed(String task, String reason) {
			// TODO Auto-generated method stub
			doTaskFailed(task, reason);
		}

	}

	protected abstract void doTaskBegin(String task, String types);

	protected abstract void doTaskComplete(String task);

	protected abstract void doTaskFailed(String task, String reason);

};
