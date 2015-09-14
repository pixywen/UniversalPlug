package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STItem extends UnityBridge {

	/**
	 * 
	 * @param itemId
	 *            道具名称或ID (唯一标识符)
	 * @param itemType
	 *            道具类型
	 * @param itemCnt
	 *            道具数量
	 * @param vituralCurrency
	 *            虚拟币单价
	 * @param currencyType
	 *            虚拟币种类
	 * @param consumePoint
	 *            保留 ""
	 * @example STItem.ItemBuy("血瓶","消耗品",20 ,3000,"金币" )
	 */
	public static void ItemBuy(String itemId, String itemType, int itemCnt,
			int vituralCurrency, String currencyType, String consumePoint) {
		Debug.d("Platform ST STItem.ItemBuy");
		UniversalPlugPlatform.getInstance().getStatsManager().STItem.ItemBuy(
				itemId, itemType, itemCnt, vituralCurrency, currencyType,
				consumePoint);
	}

	/**
	 * 
	 * @param itemId
	 *            道具名称或ID (唯一标识符)
	 * @param itemType
	 *            道具类型
	 * @param itemCnt
	 *            道具数量
	 * @param reason
	 *            获得原因
	 * @example STItem.ItemGet("大刀","武器",1,"战斗奖励")
	 */

	public static void ItemGet(String itemId, String itemType, int itemCnt,
			String reason) {

		Debug.d("Platform ST STItem.ItemBuy");
		UniversalPlugPlatform.getInstance().getStatsManager().STItem.ItemGet(
				itemId, itemType, itemCnt, reason);

	}

	/**
	 * 
	 * @param itemId
	 *            道具名称或ID (唯一标识符)
	 * @param itemType
	 *            道具类型
	 * @param itemCnt
	 *            道具数量
	 * @param reason
	 *            消耗原因
	 * @example STItem.ItemConsume("血瓶","消耗品",1,"补血")
	 */

	public static void ItemConsume(String itemId, String itemType, int itemCnt,
			String reason) {

		Debug.d("Platform ST STItem.ItemBuy");
		UniversalPlugPlatform.getInstance().getStatsManager().STItem.ItemConsume(
				itemId, itemType, itemCnt, reason);

	}

	/**
	 * 
	 * @param itemId
	 *            道具名称或ID (唯一标识符)
	 * @param itemType
	 *            道具类型
	 * @param itemCnt
	 *            道具数量
	 * @param reason
	 *            消耗原因
	 * @param itemCoin
	 *            道具对应虚拟币价格
	 * @param coinType
	 *            道具对应虚拟币
	 * @param level
	 *            关卡名称
	 *            
	 * @example STItem.ItemConsume("血瓶","消耗品",1,"补血",3000,"金币","给我一把大吧 第一关")
	 */
	public static void ItemConsume(String itemId, String itemType, int itemCnt,
			String reason, int itemCoin, String coinType, String level) {

		Debug.d("Platform ST STItem.ItemBuy");
		UniversalPlugPlatform.getInstance().getStatsManager().STItem.ItemConsume(
				itemId, itemType, itemCnt, reason, itemCoin, coinType, level);

	}

}
