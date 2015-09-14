package com.tatian.up.engine.stats;

import java.util.Map;

import com.tatian.up.utils.NotProguard;

public interface STEvent extends NotProguard{
	
	public void Event(String eventid,Map<String,String> map);
	public void Event(String eventid,Map<String,String> map,int count);
	public void EventBegin(String eventid,Map<String,String> map);
	public void EventEnd(String eventid,Map<String,String> map);
	public void EventDuration(String eventid,Map<String,String> map,long duration);

}
