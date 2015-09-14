package com.tatian.up.event;

/**
 * 用户支付事件
 */
public final class ExitEvent implements Event {
	public static final int EXIT = 0;
	public static final int CANCEL = 1;
	private int result;
	
	public ExitEvent(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

}
