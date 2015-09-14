package com.tatian.up.engine.stats;

import com.tatian.up.utils.NotProguard;

public interface STLevel extends NotProguard{

	public void LevelBegin(String level, String type);

	public void LevelComplete(String level);

	public void LevelFailed(String level, String reason);

}
