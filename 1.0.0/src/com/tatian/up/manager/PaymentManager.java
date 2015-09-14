package com.tatian.up.manager;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;

import com.tatian.up.event.handler.PaymentHandler;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.NotProguard;

public interface PaymentManager extends NotProguard {

	void requestPay(Activity activity, PaymentRequest request,
			PaymentHandler handler);

	void notifyActivityPayResult(int responseCode, String orderId,
			String productId);

	public static class PaymentRequest implements NotProguard {
		private String productId; // 商品ID
		private String productName; // 商品名称
		private String productDescription; // 商品描述
		private String productIndex; // 计费点索引
		private int count; // 数量

		// 预留位
		private String currency;
		private float amount;
		private float gameCoinAmount;
		private String serverId;
		private String gameUsername;
		private String gameUserId;
		private String gameExtra;

		//

		public PaymentRequest() {
			extraMap = null;
			count = 1;
		}

		public PaymentRequest(String json) {

			extraMap = null;
			count = 1;

			try {
				JSONObject jb = new JSONObject(json);
				if (!jb.isNull("productIndex"))
					productIndex = jb.getString("productIndex");
				if (!jb.isNull("productName"))
					productName = jb.getString("productName");
				if (!jb.isNull("productDescription"))
					productDescription = jb.getString("productDescription");
				if (!jb.isNull("productId"))
					productDescription = jb.getString("productId");
				if (!jb.isNull("count"))
					count = Integer.valueOf(jb.getString("count"));

			} catch (Exception e) {

				Debug.d("JException", e.toString());

			}
		}

		public String getProductIndex() {
			return productIndex;
		}

		public void setProductIndex(String productIndex) {
			this.productIndex = productIndex;
		}

		public String getGameUsername() {
			return gameUsername;
		}

		public PaymentRequest setGameUsername(String gameUsername) {
			this.gameUsername = gameUsername;
			return this;
		}

		public String getGameUserId() {
			return gameUserId;
		}

		public PaymentRequest setGameUserId(String gameUserId) {
			this.gameUserId = gameUserId;
			return this;
		}

		public int getCount() {
			return count;
		}

		public PaymentRequest setCount(int count) {
			this.count = count;
			return this;
		}

		public String getGameExtra() {
			return gameExtra;
		}

		public PaymentRequest setGameExtra(String gameExtra) {
			this.gameExtra = gameExtra;
			return this;
		}

		public String getProductId() {
			return productId;
		}

		public PaymentRequest setProductId(String productId) {
			this.productId = productId;
			return this;
		}

		public String getProductName() {
			return productName;
		}

		public PaymentRequest setProductName(String productName) {
			this.productName = productName;
			return this;
		}

		public String getProductDescription() {
			return productDescription;
		}

		public PaymentRequest setProductDescription(String productDescription) {
			this.productDescription = productDescription;
			return this;
		}

		public String getCurrency() {
			return currency;
		}

		public PaymentRequest setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public float getAmount() {
			return amount;
		}

		public PaymentRequest setAmount(float amount) {
			this.amount = amount;
			return this;
		}

		public float getGameCoinAmount() {
			return gameCoinAmount;
		}

		public PaymentRequest setGameCoinAmount(float gameCoinAmount) {
			this.gameCoinAmount = gameCoinAmount;
			return this;
		}

		public String getServerId() {
			return serverId;
		}

		public PaymentRequest setServerId(String serverId) {
			this.serverId = serverId;
			return this;
		}

		// Not For Game.
		private HashMap<String, String> extraMap;

		protected HashMap<String, String> getExtraMap() {
			return extraMap;
		}

	}
}
