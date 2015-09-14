package com.tatian.up.event;

/**
 * 用户支付事件
 */
public final class PaymentEvent implements Event {
	public static final int SUCCESS = 0;
	public static final int USER_CANCEL = 1;
	public static final int FAILED = 3;
	//public static final int PROCESSING = 5;
	//public static final int USER_TOKEN_UNAVAILABLE = 2;
	//public static final int SERVER_ERROR = 4;
	private int result;
	private String orderId;
	private String productId;

	public PaymentEvent(int result, String orderId, String productId) {
		this.result = result;
		this.orderId = orderId;
		this.productId = productId;
	}

	public int getResult() {
		return result;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

}
