package com.tatian.up.engine.sdkproxy;

import android.app.Activity;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.manager.AbstractPaymentManager;
import com.tatian.up.engine.manager.impl.StatisticsManagerImpl;
import com.tatian.up.engine.manager.impl.UserManagerImpl;
import com.tatian.up.index.UniversalPlugIndex;
import com.tatian.up.utils.Debug;
import com.unicom.dcLoader.Utils;
import com.unicom.dcLoader.Utils.UnipayPayResultListener;

public class UNIPAYPlatformProxy extends UniversalPlugPlatform {

	public UNIPAYPlatformProxy(UniversalPlugRunConfig config,
			final PlatformInitCompleteCallback callback) {
		super(config, callback);

		// TODO:Init Proxy SDK Here.


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

			Debug.d("UNIPAYPlatformProxy doPay");
			final Activity activity = this.getActivityContext();

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					Utils.getInstances().pay(
							activity,
							UniversalPlugIndex.getInstance().getValue(
									request.getProductIndex(), "value"),
							new UnipayPayResultListener() {

								@Override
								public void PayResult(String arg0, int arg1,
										int arg2, String arg3) {
									// TODO Auto-generated method stub
									Debug.d("desc="
											+ UniversalPlugIndex
													.getInstance()
													.getValue(
															request.getProductIndex(),
															"value"));
									Debug.d("payresult=" + arg0 + " " + arg1
											+ " " + arg2 + " " + arg3);
									switch (arg1) {
									case 1:
										notifyPaySuccess(
												request.getProductIndex(), "");
										break;
									case 2:
										notifyPayFailed();
										break;
									case 3:
										notifyCancelPay();
										break;

									default:
										notifyPayFailed();
										break;

									}

								}
							});

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
		super.doExit(activity);
	}

	@Override
	public boolean isMusicEnabled(Activity activity) {
		// TODO Auto-generated method stub
		return super.isMusicEnabled(activity);
	}

	@Override
	public void viewMoreGames(Activity activity) {
		// TODO Auto-generated method stub
		super.viewMoreGames(activity);
	}

	@Override
	public void onActivityResume(Activity activity) {
		// TODO Auto-generated method stub
		Utils.getInstances().onResume(activity);
		super.onActivityResume(activity);
	}

	@Override
	public void onActivityPause(Activity activity) {
		// TODO Auto-generated method stub
		Utils.getInstances().onPause(activity);
		super.onActivityPause(activity);
	}
	
	

}
