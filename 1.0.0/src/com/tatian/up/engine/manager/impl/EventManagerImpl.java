package com.tatian.up.engine.manager.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.EventManager;
import com.tatian.up.event.Event;
import com.tatian.up.event.Handle;
import com.tatian.up.event.handler.EventHandler;
import com.tatian.up.event.handler.OnceEventHandler;
import com.tatian.up.utils.Debug;

public class EventManagerImpl implements EventManager {
	private Map<String, Collection<EventHandler>> handlerMap;

	public EventManagerImpl() {
		handlerMap = new HashMap<String, Collection<EventHandler>>();
	}

	@Override
	public void init(UniversalPlugRunConfig config) {

	}

	@Override
	public void release() {
		if (handlerMap != null) {
			handlerMap.clear();
			handlerMap = null;
		}
	}

	@Override
	public void registerEventHandler(EventHandler eventHandler) {
		Method[] methods = getAllDeclaredMethods(eventHandler.getClass());
		for (Method method : methods) {
			Handle handleAnnotation = method.getAnnotation(Handle.class);
			if (handleAnnotation != null) {
				registerEventHandlerByTag(handleAnnotation.value().getName(), eventHandler);
			}
		}

	}

	@Override
	public void unRegisterEventHandler(EventHandler eventHandler) {
		Method[] methods = getAllDeclaredMethods(eventHandler.getClass());
		for (Method method : methods) {
			Handle handleAnnotation = method.getAnnotation(Handle.class);
			if (handleAnnotation != null) {
				unRegisterEventHandlerByTag(handleAnnotation.value().getName(), eventHandler);
			}
		}
	}

	@Override
	public <T extends Event> void dispatchEvent(T event) {
		String eventTag = event.getClass().getName();
		Collection<EventHandler> handlerList = handlerMap.get(eventTag);
		if (handlerList != null) {
			synchronized (handlerList) {
				for (EventHandler eventHandler : handlerList) {
					Method[] methods = getAllDeclaredMethods(eventHandler.getClass());
					for (Method method : methods) {
						Handle handleAnnotation = method.getAnnotation(Handle.class);
						if (handleAnnotation != null) {
							if (eventTag.equals(handleAnnotation.value().getName())) {
								try {
									method.setAccessible(true);
									method.invoke(eventHandler, event);
								} catch (Exception e) {
									Debug.w("EventManager", "Cannot invoke this method : " + method.getName());
									Debug.w(e);
								}
								if (eventHandler instanceof OnceEventHandler) {
									unRegisterEventHandlerByTag(handleAnnotation.value().getName(), eventHandler);
								}
							}
						}
					}
				}
			}
		}
	}

	private void registerEventHandlerByTag(String eventTag, EventHandler eventHandler) {
		Collection<EventHandler> handlerList = handlerMap.get(eventTag);
		if (handlerList == null) {
			handlerList = new ConcurrentLinkedQueue<EventHandler>();
			handlerMap.put(eventTag, handlerList);
		}
		synchronized (handlerList) {
			handlerList.add(eventHandler);
		}
	}

	private void unRegisterEventHandlerByTag(String eventTag, EventHandler eventHandler) {
		Collection<EventHandler> handlerList = handlerMap.get(eventTag);
		synchronized (handlerList) {
			if (handlerList != null && handlerList.contains(eventHandler)) {
				handlerList.remove(eventHandler);
			}
		}
	}

	private Method[] getAllDeclaredMethods(Class<?> clazz) {
		List<Method> list = new ArrayList<Method>();
		while (clazz != null) {
			for (Method method : clazz.getDeclaredMethods()) {
				list.add(method);
			}
			clazz = clazz.getSuperclass();
		}
		return list.toArray(new Method[list.size()]);
	}

}
