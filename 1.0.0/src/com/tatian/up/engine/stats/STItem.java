package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STItem extends NotProguard{

	public void ItemBuy(String itemId, String itemType, int itemCnt,
			int vituralCurrency, String currencyType, String consumePoint);

	public void ItemGet(String itemId, String itemType, int itemCnt,
			String reason);

	public void ItemConsume(String itemId, String itemType, int itemCnt,
			String reason);

	/**
	 * 
	 * @param itemId 道具名称或ID (唯一标识符)
	 * @param itemType 道具类型
	 * @param itemCnt 道具数量
	 * @param reason 消耗原因
	 * @param itemCoin 道具对应虚拟币价格
	 * @param coinType 道具对应虚拟币
	 * @param level 关卡名称
	 */
	public void ItemConsume(String itemId, String itemType, int itemCnt,
			String reason, int itemCoin, String coinType, String level);

}
