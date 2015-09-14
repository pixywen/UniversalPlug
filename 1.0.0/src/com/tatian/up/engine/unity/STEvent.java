package com.tatian.up.engine.unity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.JsonStringUtil;

public class STEvent extends UnityBridge {

	/**
	 * 
	 * @param eventId 游戏客户端定义的事件ID，唯一标识一个事件
	 * @param jsonMap 事件发生时需要关注的所有属性组成的字典(jsonObject)
	 */
	public static void Event(String eventId, String jsonMap) {
		Debug.d("Platform ST STEvent.Event");
		UniversalPlugPlatform.getInstance().getStatsManager().STEvent.Event(
				eventId, JsonStringUtil.Json2Map(jsonMap));
	}

	/**
	 * 
	 * @param eventId 游戏客户端定义的事件ID，唯一标识一个事件
	 * @param jsonMap 事件发生时需要关注的所有属性组成的字典(jsonObject)
	 * @param count 事件发生的次数
	 */
	public static void Event(String eventId, String jsonMap, int count) {
		Debug.d("Platform ST STEvent.Event");
		UniversalPlugPlatform.getInstance().getStatsManager().STEvent.Event(
				eventId, JsonStringUtil.Json2Map(jsonMap), count);
	}

	/**
	 * 
	 * @param eventId 游戏客户端定义的事件ID，唯一标识一个事件
	 * @param jsonMap 事件发生时需要关注的所有属性组成的字典(jsonObject)
	 */
	public static void EventBegin(String eventId, String jsonMap) {
		Debug.d("Platform ST STEvent.EventBegin");
		UniversalPlugPlatform.getInstance().getStatsManager().STEvent.EventBegin(
				eventId, JsonStringUtil.Json2Map(jsonMap));
	}

	/**
	 * 
	 * @param eventId 游戏客户端定义的事件ID，唯一标识一个事件
	 * @param jsonMap 事件发生时需要关注的所有属性组成的字典(jsonObject)
	 * @example
	 * map_json = {"item": "skill book","count": "10"}
	 * EventEnd("gift",map_json)
	 */
	public static void EventEnd(String eventId, String jsonMap) {
		Debug.d("Platform ST STEvent.EventEnd");
		UniversalPlugPlatform.getInstance().getStatsManager().STEvent.EventEnd(
				eventId, JsonStringUtil.Json2Map(jsonMap));
	}

	/**
	 * 
	 * @param eventId 游戏客户端定义的事件ID，唯一标识一个事件
	 * @param jsonMap 事件发生时需要关注的所有属性组成的字典(jsonObject)
	 * @param duration 事件时长,单位为秒
	 * map_json = {"item": "skill book","count": "10"}
	 * EventEnd("gift",map_json,100)
	 */
	public static void EventDuration(String eventId, String jsonMap,
			long duration) {
		Debug.d("Platform ST STEvent.EventDuration");
		UniversalPlugPlatform.getInstance().getStatsManager().STEvent
				.EventDuration(eventId, JsonStringUtil.Json2Map(jsonMap),
						duration);
	}

}
