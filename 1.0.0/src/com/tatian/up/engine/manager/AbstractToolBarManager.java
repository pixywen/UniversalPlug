package com.tatian.up.engine.manager;

import java.lang.ref.SoftReference;

import android.app.Activity;

import com.tatian.up.engine.controller.UniversalPlugController;
import com.tatian.up.manager.ToolBarManager;

public abstract class AbstractToolBarManager implements ToolBarManager {
	
	protected SoftReference<Activity> activityRef;

	protected Activity getActivityContext() {
		if (activityRef == null) {
			return (Activity) UniversalPlugController.getInstance().getContext();
		}
		if (activityRef.get() == null) {
			return (Activity) UniversalPlugController.getInstance().getContext();
		}
		return activityRef.get();
	}

}
