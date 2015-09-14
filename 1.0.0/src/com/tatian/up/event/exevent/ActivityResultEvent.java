package com.tatian.up.event.exevent;

import android.content.Intent;

import com.tatian.up.event.Event;

public class ActivityResultEvent implements Event {
	private int requestCode;
	private int resultCode;
	private Intent data;

	public ActivityResultEvent(int requestCode, int responseCode, Intent data) {
		this.requestCode = requestCode;
		this.resultCode = responseCode;
		this.data = data;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public Intent getData() {
		return data;
	}

}
