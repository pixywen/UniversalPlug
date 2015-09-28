package com.tatian.up.engine.sdkproxy;

import android.app.Activity;
import cn.cmgame.billing.api.BillingResult;
import cn.cmgame.billing.api.GameInterface;
import cn.cmgame.billing.api.GameInterface.GameExitCallback;
import cn.cmgame.billing.api.GameInterface.IPayCallback;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.AbstractPaymentManager;
import com.tatian.up.engine.manager.impl.StatisticsManagerImpl;
import com.tatian.up.engine.manager.impl.UserManagerImpl;
import com.tatian.up.index.UniversalPlugIndex;

public class JDPlatformProxy extends UniversalPlugPlatform {

	public JDPlatformProxy(UniversalPlugRunConfig config,
			final PlatformInitCompleteCallback callback) {
		super(config, callback);

		// TODO:Init Proxy SDK Here.

		GameInterface.initializeApp((Activity) config.getContext());

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
		statsManager= new StatisticsManagerImpl(null);
	}
	
	
	

	@Override
	protected String doGetProxyVersion() {
		// TODO Auto-generated method stub
		return "20130";
	}




	private class PaymentManagerProxy extends AbstractPaymentManager {

		@Override
		protected void doPay(final PaymentRequest request) {

			GameInterface.doBilling(
					this.getActivityContext(),
					true,
					true,
					UniversalPlugIndex.getInstance().getValue(
							request.getProductIndex(), "value"), null,
					new IPayCallback() {

						@Override
						public void onResult(int resultCode,
								String billingIndex, Object obj) {
							// TODO Auto-generated method stub

							switch (resultCode) {
							case BillingResult.SUCCESS:
								notifyPaySuccess(request.getProductIndex(), "");
								break;
							case BillingResult.FAILED:
								notifyPayFailed();
								break;
							default:
								notifyPayFailed();
								break;
							}

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
	protected void doExit(Activity activity) {
		// TODO Auto-generated method stub
		GameInterface.exit(activity, new GameExitCallback(){

			@Override
			public void onCancelExit() {
				// TODO Auto-generated method stub
				notifyCancelExit();
			}

			@Override
			public void onConfirmExit() {
				// TODO Auto-generated method stub
				notifyExitSuccess();
			}
			
		});
	}

	@Override
	public boolean isMusicEnabled(Activity activity) {
		// TODO Auto-generated method stub
		return GameInterface.isMusicEnabled();
	}

	@Override
	public void viewMoreGames(Activity activity) {
		// TODO Auto-generated method stub
		GameInterface.viewMoreGames(activity);
	}

}
