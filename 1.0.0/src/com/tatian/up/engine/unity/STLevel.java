package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STLevel extends UnityBridge {

	/**
	 * 开始关卡
	 * 
	 * @param level
	 * @param no
	 *            关卡的顺序
	 * @warn 与 DataEye 参数顺序相反
	 * @example STLevel.LevelBegin("level_boss","3")
	 */
	public static void LevelBegin(String level, String no) {
		Debug.d("Platform ST STLevel.LevelBegin");
		UniversalPlugPlatform.getInstance().getStatsManager().STLevel.LevelBegin(
				level, no);
	}

	/**
	 * 完成关卡
	 * 
	 * @param level
	 *            关卡ID或关卡名
	 * @example STLevel.LevelComplete("level_boss")
	 */
	public static void LevelComplete(String level) {
		Debug.d("Platform ST STLevel.LevelComplete");
		UniversalPlugPlatform.getInstance().getStatsManager().STLevel
				.LevelComplete(level);
	}

	/**
	 * 关卡失败
	 * 
	 * @param level
	 *            关卡ID或关卡名
	 * @param reason
	 *            失败原因
	 * @example STLevel.LevelFailed("level_boss","大刀折断")
	 */
	public static void LevelFailed(String level, String reason) {
		Debug.d("Platform ST STLevel.LevelFailed");
		UniversalPlugPlatform.getInstance().getStatsManager().STLevel.LevelFailed(
				level, reason);
	}

}
