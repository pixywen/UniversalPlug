package com.tatian.up.engine.manager;

import com.tatian.up.engine.UniversalPlugRunConfig;

public interface Manager {
	void init(UniversalPlugRunConfig config);

	void release();
}
