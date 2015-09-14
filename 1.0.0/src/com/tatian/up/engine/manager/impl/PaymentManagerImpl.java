package com.tatian.up.engine.manager.impl;

import com.tatian.up.engine.manager.AbstractPaymentManager;


public class PaymentManagerImpl extends AbstractPaymentManager {

	@Override
	protected void doPay(PaymentRequest request) {
		// TODO Auto-generated method stub
		
		notifyPayFailed();
		
	}

	@Override
	public void notifyActivityPayResult(int responseCode, String orderId,
			String productId) {
		// TODO Auto-generated method stub
		
	}

}
