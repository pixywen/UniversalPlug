package com.tatian.up.event.handler;

import com.tatian.up.event.ExitEvent;
import com.tatian.up.event.Handle;

public abstract class ExitHandler implements OnceEventHandler {
	@Handle(ExitEvent.class)
	private void onExitSuccess0(ExitEvent event) {
		switch (event.getResult()) {
		case ExitEvent.EXIT:
			onExit();
			break;
		case ExitEvent.CANCEL:
			onCancel();
			break;

		}
	}

	protected abstract void onExit();

	protected abstract void onCancel();

}
