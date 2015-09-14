package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;

public class STTask extends UnityBridge {

	
	/**
	 * 开始任务
	 * @param task 任务Id或者任务名
	 * @param type
	 * @example TaskBegin("kill_boss", "任务类型")
	 */
	public static void TaskBegin(String task, String type) {
		Debug.d("Platform ST STTask.TaskBegin");
		UniversalPlugPlatform.getInstance().getStatsManager().STTask.TaskBegin(
				task, type);
	}

	
	/**
	 * 任务完成
	 * @param task 任务Id或者任务名
	 * @example TaskComplete("kill_boss")
	 */
	public static void TaskComplete(String task) {
		Debug.d("Platform ST STTask.TaskComplete");
		UniversalPlugPlatform.getInstance().getStatsManager().STTask
				.TaskComplete(task);
	}

	
	/**
	 * 任务失败
	 * @param task 任务Id或者任务名
	 * @param reason 任务失败原因
	 * @example TaskFailed("kill_boss", "血量耗尽")
	 */
	public static void TaskFailed(String task, String reason) {
		Debug.d("Platform ST STTask.TaskFailed");
		UniversalPlugPlatform.getInstance().getStatsManager().STTask.TaskFailed(
				task, reason);

	}

}
