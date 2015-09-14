package com.tatian.up.event.exevent;

import com.tatian.up.event.Event;

public class ActivityPayResultEvent implements Event  {

	private int resultCode;

	public ActivityPayResultEvent(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
}
