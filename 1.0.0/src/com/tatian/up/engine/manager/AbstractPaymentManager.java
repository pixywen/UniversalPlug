package com.tatian.up.engine.manager;

import java.lang.ref.SoftReference;

import android.app.Activity;

import com.tatian.up.engine.controller.UniversalPlugController;
import com.tatian.up.event.PaymentEvent;
import com.tatian.up.event.handler.PaymentHandler;
import com.tatian.up.manager.PaymentManager;

public abstract class AbstractPaymentManager implements PaymentManager {
	
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

	@Override
	public void requestPay(Activity activity, PaymentRequest request, PaymentHandler handler) {
		UniversalPlugController.getInstance().getActivityManager().notifyActivityActive(activity);
		activityRef = new SoftReference<Activity>(activity);
		if (handler != null) {
			UniversalPlugController.getInstance().getEventManager().registerEventHandler(handler);
		}

		doPay(request);
	}

	protected void notifyCancelPay() {
		UniversalPlugController.getInstance().getEventManager().dispatchEvent(new PaymentEvent(PaymentEvent.USER_CANCEL, null, null));
	}

	protected void notifyPayFailed() {
		UniversalPlugController.getInstance().getEventManager().dispatchEvent(new PaymentEvent(PaymentEvent.FAILED, null, null));
	}
	
	protected void notifyPaySuccess(String orderId, String productId) {
		UniversalPlugController.getInstance().getEventManager().dispatchEvent(new PaymentEvent(PaymentEvent.SUCCESS, orderId, productId));
	}

	protected abstract void doPay(PaymentRequest request);
	public abstract void notifyActivityPayResult(int responseCode,String orderId,String productId);

}
