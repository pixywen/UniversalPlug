package com.tatian.up.engine.manager;

import com.tatian.up.event.Event;
import com.tatian.up.event.handler.EventHandler;

public interface EventManager extends Manager {
	void registerEventHandler(EventHandler eventHandler);

	void unRegisterEventHandler(EventHandler eventHandler);

	<T extends Event> void dispatchEvent(T event);
}
