package com.tatian.up.engine.unity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.entity.User;
import com.tatian.up.event.PaymentEvent;
import com.tatian.up.event.handler.ExitHandler;
import com.tatian.up.event.handler.PaymentHandler;
import com.tatian.up.event.handler.UserLoginHandler;
import com.tatian.up.manager.PaymentManager.PaymentRequest;
import com.tatian.up.utils.Debug;
import com.unity3d.player.UnityPlayer;

public class STAgent extends UnityBridge {

	/**
	 * 
	 * @param paymentRequest
	 * @param gameObject
	 * @param runtimeScript
	 */
	public static void pay(String paymentRequest, final String gameObject,
			final String runtimeScript) {
		Debug.d("Platform pay");
		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;

		PaymentRequest request = new PaymentRequest(paymentRequest);

		UniversalPlugPlatform.getInstance().getPaymentManager()
				.requestPay(activity, request, new PaymentHandler() {

					@Override
					protected void onPaymentSuccess(PaymentEvent event) {
						Map<String, String> params = new HashMap<String, String>();
						params.put("productIndex", event.getOrderId());
						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onPaymentSuccess", params));

					}

					@Override
					protected void onUserCancel() {

						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onUserCancel", null));

					}

					@Override
					protected void onPaymentFailed() {

						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onPaymentFailed", null));

					}

				});

	}

	/**
	 * 
	 * @param gameObject
	 * @param runtimeScript
	 */
	public static void login(final String gameObject, final String runtimeScript) {

		Debug.d("Platform login");
		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;
		UniversalPlugPlatform.getInstance().getUserManager()
				.requestLogin(activity, new UserLoginHandler() {

					@Override
					protected void onLoginSuccess(User user) {
						// TODO Auto-generated method stub

						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onLoginSuccess", null));

					}

					@Override
					protected void onUserCancel() {
						// TODO Auto-generated method stub
						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onUserCancel", null));
					}

					@Override
					protected void onLoginFailed() {
						// TODO Auto-generated method stub
						UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
								Status2String("onLoginFailed", null));
					}

				});

	}

	/**
	 * 
	 * @param gameObject
	 * @param runtimeScript
	 */
	public static void exit(final String gameObject, final String runtimeScript) {

		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;

		UniversalPlugPlatform.getInstance().exit(activity, new ExitHandler() {

			@Override
			protected void onExit() {
				// TODO Auto-generated method stub
				UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
						Status2String("onExit", null));
				Debug.d("Platform onExit");
			}

			@Override
			protected void onCancel() {
				// TODO Auto-generated method stub
				UnityPlayer.UnitySendMessage(gameObject, runtimeScript,
						Status2String("onCancel", null));
				Debug.d("Platform onCancel");

			}

		});

	}

	/**
	 * 
	 * @return
	 */
	public static boolean isMusicEnabled() {
		Debug.d("Platform isMusicEnabled");
		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;
		return UniversalPlugPlatform.getInstance().isMusicEnabled(activity);
	}

	public static void viewMoreGames() {
		Debug.d("Platform viewMoreGames");
		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;
		UniversalPlugPlatform.getInstance().viewMoreGames(activity);
	}

	public static int getChannel() {
		Debug.d("Platform getChannel");
		String Channel = UniversalPlugPlatform.getInstance().getChannel();
		int channel_id = 0;
		try {

			channel_id = Integer.parseInt(Channel);

		} catch (Exception e) {

			return channel_id;

		}

		return channel_id;

	}

	public static String getChannelID() {

		String Channel = UniversalPlugPlatform.getInstance().getChannel();
		return Channel;
	}

	public static String getPackageName() {

		String packageName = UniversalPlugPlatform.getInstance().getPackageName();
		return packageName;
	}

	public static void about() {
		Debug.d("Platform about");
		Activity activity = com.unity3d.player.UnityPlayer.currentActivity;
		UniversalPlugPlatform.getInstance().about(activity);

	}
}
