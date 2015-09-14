package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STCoin extends UnityBridge {

	/**
	 * 设置虚拟币总量
	 * @param coinNum 玩家剩余货币总量
	 * @param coinType 货币类型
	 * @example CoinSetNum(72112, "金币")
	 */
	public static void CoinSetNum(long coinNum, String coinType) {
		Debug.d("Platform ST STCoin.CoinSetNum");
		UniversalPlugPlatform.getInstance().getStatsManager().STCoin.CoinSetNum(
				coinNum, coinType);
	}

	/**
	 * 虚拟币增加
	 * @param reason 玩家获得虚拟币原因
	 * @param coinType 货币类型
	 * @param gain 玩家获得虚拟币数量
	 * @param left 玩家剩余虚拟币数量，加上获得虚拟币后的剩余总量
	 * @example CoinGain("每日奖励","金币",1200,5000)
	 */
	public static void CoinGain(String reason, String coinType, long gain, long left) {

		Debug.d("Platform ST STCoin.CoinGain");
		UniversalPlugPlatform.getInstance().getStatsManager().STCoin.CoinGain(reason,
				coinType, gain, left);

	}

	/**
	 * 
	 * @param reason 玩家获得虚拟币原因
	 * @param coinType 货币类型
	 * @param lost 玩家消耗虚拟币数量
	 * @param left 玩家剩余虚拟币数量，加上获得虚拟币后的剩余总量
	 * @example CoinLost("购买体力","金币",1200,3800)
	 */
	public static void CoinLost(String reason, String coinType, long lost, long left) {

		Debug.d("Platform ST STCoin.CoinLost");
		UniversalPlugPlatform.getInstance().getStatsManager().STCoin.CoinLost(reason,
				coinType, lost, left);

	}

}
