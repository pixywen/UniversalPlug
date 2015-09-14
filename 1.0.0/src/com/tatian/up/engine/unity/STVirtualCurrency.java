package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STVirtualCurrency extends UnityBridge {

	/**
	 * @param orderId
	 *            订单号 (单机不接)
	 * @param currencyAmount
	 *            货币价格
	 * @param currencyType
	 *            货币单位
	 * @param paymentType
	 *            支付方式
	 *            
	 * @example CurrencyPaymentSuccess("21asdfefa",100.00,"CNY","支付宝" )
	 */
	public static void CurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType) {
		Debug.d("Platform ST STVirtualCurrency.CurrencyPaymentSuccess");
		UniversalPlugPlatform.getInstance().getStatsManager().STVirtualCurrency
				.CurrencyPaymentSuccess(orderId, currencyAmount, currencyType,
						paymentType);
	}

	/**
	 * @param currencyAmount
	 *            货币价格
	 * @param currencyType
	 *            货币单位
	 * @param paymentType
	 *            支付方式
	 * @example CurrencyPaymentSuccess(100.00,"CNY","支付宝" )
	 */
	public static void CurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType) {
		Debug.d("Platform ST STVirtualCurrency.CurrencyPaymentSuccess");
		UniversalPlugPlatform.getInstance().getStatsManager().STVirtualCurrency
				.CurrencyPaymentSuccess(currencyAmount, currencyType,
						paymentType);
	}

	/**
	 * @param orderId
	 *            订单号 (单机不接)
	 * @param currencyAmount
	 *            货币价格
	 * @param currencyType
	 *            货币单位
	 * @param paymentType
	 *            支付方式
	 * @param goodsName
	 *            购买商品名称
	 * @param goodsCoin
	 *            购买商品价格
	 * @param goodsNum
	 *            购买商品数量
	 * @param level
	 *            关卡名称 关卡名称 可为 ""
	 * @param rank
	 *            等级 关卡名称 可为 ""
	 * @example CurrencyPaymentSuccess("21asdfefa",100.00,"CNY","支付宝","大刀","1000",1,"level_1","10")
	 */

	public static void CurrencyPaymentSuccess(String orderId,
			double currencyAmount, String currencyType, String paymentType,
			String goodsName, String goodsCoin, int goodsNum, String level,
			String rank) {
		Debug.d("Platform ST STVirtualCurrency.CurrencyPaymentSuccess");
		UniversalPlugPlatform.getInstance().getStatsManager().STVirtualCurrency
				.CurrencyPaymentSuccess(orderId, currencyAmount, currencyType,
						paymentType, goodsName, goodsCoin, goodsNum, level,
						rank);

	}

	/**
	 * 
	 * @param currencyAmount
	 *            货币价格
	 * @param currencyType
	 *            货币单位
	 * @param paymentType
	 *            支付方式
	 * @param goodsName
	 *            购买商品名称
	 * @param goodsCoin
	 *            购买商品价格
	 * @param goodsNum
	 *            购买商品数量
	 * @param level
	 *            关卡名称 可为 ""
	 * @param rank
	 *            等级 可为 ""
	 *            
	 * @example CurrencyPaymentSuccess(100.00,"CNY","支付宝","大刀","1000",1,"level_1","10")
	 */
	public static void CurrencyPaymentSuccess(double currencyAmount,
			String currencyType, String paymentType, String goodsName,
			String goodsCoin, int goodsNum, String level, String rank) {

		Debug.d("Platform ST STVirtualCurrency.CurrencyPaymentSuccess");
		UniversalPlugPlatform.getInstance().getStatsManager().STVirtualCurrency
				.CurrencyPaymentSuccess(currencyAmount, currencyType,
						paymentType, goodsName, goodsCoin, goodsNum, level,
						rank);

	}

}
