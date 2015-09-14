package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STTask extends NotProguard{

	public void TaskBegin(String task, String type);

	public void TaskComplete(String task);

	public void TaskFailed(String task, String reason);

}
