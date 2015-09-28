package com.tatian.up.engine.sdkproxy;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import cn.egame.terminal.paysdk.EgameExitListener;
import cn.egame.terminal.paysdk.EgamePay;
import cn.egame.terminal.paysdk.EgamePayListener;
import cn.play.dserv.CheckTool;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.AbstractPaymentManager;
import com.tatian.up.engine.manager.impl.StatisticsManagerImpl;
import com.tatian.up.engine.manager.impl.UserManagerImpl;
import com.tatian.up.index.UniversalPlugIndex;
import com.tatian.up.utils.Debug;

public class EGAMEPlatformProxy extends UniversalPlugPlatform {

	public EGAMEPlatformProxy(UniversalPlugRunConfig config,
			final PlatformInitCompleteCallback callback) {
		super(config, callback);

		// TODO:Init Proxy SDK Here.

		EgamePay.init((Activity) config.getContext());
		CheckTool.init((Activity) config.getContext());

		new Thread() {
			public void run() {
				if (callback != null) {
					callback.onPlatformInitComplete(0);
				}
			};
		}.start();
	}

	@Override
	protected void createManager() {
		paymentManager = new PaymentManagerProxy();
		userManager = new UserManagerImpl();
		statsManager = new StatisticsManagerImpl(null);
	}

	private class PaymentManagerProxy extends AbstractPaymentManager {

		@Override
		protected void doPay(final PaymentRequest request) {

			HashMap<String, String> payParams = new HashMap<String, String>();
			payParams.put(
					EgamePay.PAY_PARAMS_KEY_TOOLS_ALIAS,
					UniversalPlugIndex.getInstance().getValue(
							request.getProductIndex(), "value"));

			EgamePay.pay(this.getActivityContext(), payParams,
					new EgamePayListener() {

						@Override
						public void payCancel(Map<String, String> params) {
							// TODO Auto-generated method stub
							Debug.d("egame payCancel");
							notifyCancelPay();
						}

						@Override
						public void payFailed(Map<String, String> params,
								int errorInt) {
							// TODO Auto-generated method stub
							Debug.d("ErrorCode" + errorInt);
							notifyPayFailed();

						}

						@Override
						public void paySuccess(Map<String, String> params) {
							// TODO Auto-generated method stub
							notifyPaySuccess(request.getProductIndex(), "");

						}

					});

		}

		@Override
		public void notifyActivityPayResult(int responseCode, String orderId,
				String productId) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	protected void doExit(final Activity activity) {
		// TODO Auto-generated method stub
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				EgamePay.exit(activity, new EgameExitListener() {

					@Override
					public void exit() {
						notifyExitSuccess();
					}

					@Override
					public void cancel() {
						notifyCancelExit();

					}
				});
			}

		});

	}

	@Override
	public boolean isMusicEnabled(Activity activity) {
		// TODO Auto-generated method stub
		return super.isMusicEnabled(activity);
	}

	@Override
	public void viewMoreGames(Activity activity) {
		// TODO Auto-generated method stub
		//super.viewMoreGames(activity);
		EgamePay.moreGame(activity);
	}

}
