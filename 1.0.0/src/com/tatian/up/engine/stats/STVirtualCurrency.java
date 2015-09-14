package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STVirtualCurrency extends NotProguard{

	public void CurrencyPaymentSuccess(String orderId, double currencyAmount,
			String currencyType, String paymentType);

	public void CurrencyPaymentSuccess(double currencyAmount, String currencyType,
			String paymentType);
	
	public void CurrencyPaymentSuccess(String orderId, double currencyAmount, String currencyType,
			String paymentType, String goodsName, String goodsCoin, int goodsNum, String level, String rank);
	
	/**
	 * 
	 * @param currencyAmount 货币价格
	 * @param currencyType 货币单位
	 * @param paymentType 支付方式
	 * @param goodsName 购买商品名称
	 * @param goodsCoin 购买商品价格
	 * @param goodsNum 购买商品数量
	 * @param level 关卡名称
	 * @param rank 等级
	 */
	public void CurrencyPaymentSuccess(double currencyAmount, String currencyType,
			String paymentType ,String goodsName, String goodsCoin, int goodsNum, String level, String rank);

}
