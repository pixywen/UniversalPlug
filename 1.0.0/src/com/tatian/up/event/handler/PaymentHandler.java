package com.tatian.up.event.handler;

import com.tatian.up.event.Handle;
import com.tatian.up.event.PaymentEvent;

public abstract class PaymentHandler implements OnceEventHandler {
	@Handle(PaymentEvent.class)
	private void onPaymentSuccess0(PaymentEvent event) {
		switch (event.getResult()) {
		case PaymentEvent.SUCCESS:
			onPaymentSuccess(event);
			break;
		case PaymentEvent.USER_CANCEL:
			onUserCancel();
			break;	
		case PaymentEvent.FAILED:
			onPaymentFailed();
			break;
//		case PaymentEvent.USER_TOKEN_UNAVAILABLE:
//			onUserTokenUnavailable();
//			break;
//		case PaymentEvent.PROCESSING:
//			onPaymentProcessing();
//			break;
//		case PaymentEvent.SERVER_ERROR:
//			onServerError();
//			break;
		
		}
	}
	
	protected abstract void onPaymentSuccess(PaymentEvent event);
	
	protected abstract void onPaymentFailed();
	
	protected abstract void onUserCancel();
	
//	protected abstract void onPaymentProcessing();
//
//	protected abstract void onUserTokenUnavailable();
//
//	protected abstract void onServerError();

}
