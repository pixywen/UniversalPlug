package com.tatian.up.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tatian.up.demo.unipay.R;
import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugPlatform.PlatformInitCompleteCallback;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.event.PaymentEvent;
import com.tatian.up.event.handler.ExitHandler;
import com.tatian.up.event.handler.PaymentHandler;
import com.tatian.up.manager.PaymentManager;

public class MainActivity extends Activity {
	private Button loginBtn, logoutBtn, switchUserBtn, payBtn;
	private TextView loginStateText;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		UniversalPlugRunConfig runConfig = UniversalPlugRunConfig.initFromXML(this);
		
		UniversalPlugPlatform.init(runConfig,
				new UniversalPlugPlatform.PlatformInitCompleteCallback() {

					@Override
					public void onPlatformInitComplete(int code) {
						switch (code) {
						case PlatformInitCompleteCallback.SUCCESS:
							new Handler(Looper.getMainLooper())
									.post(new Runnable() {
										@Override
										public void run() {
											selfInit();
										}
									});
							break;
						default:

							finish();
							break;
						}
					}

				});

	}

	private void selfInit() {
		setContentView(R.layout.test_activity_main);

		UniversalPlugPlatform.getInstance().getExceptionManager()
				.enableAutoCatchException();

		handler = new Handler(Looper.getMainLooper());
		loginStateText = (TextView) findViewById(R.id.test_login_state);
		showTitles(getResources().getString(R.string.up_demo_notlogin));
		loginBtn = (Button) findViewById(R.id.test_login_android);
		loginBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				UniversalPlugPlatform
				.getInstance().viewMoreGames(MainActivity.this);
	
			}
		});
		logoutBtn = (Button) findViewById(R.id.test_logout);
		logoutBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				UniversalPlugPlatform
				.getInstance().exit(MainActivity.this, new ExitHandler(){

					@Override
					protected void onExit() {
						// TODO Auto-generated method stub
						showToast("onExit");
					}

					@Override
					protected void onCancel() {
						// TODO Auto-generated method stub
						showToast("onCancel");
					}
					
				});

				
			}
		});

		switchUserBtn = (Button) findViewById(R.id.test_switchuser);
		switchUserBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				showToast("isMusicEnabled ="
						+ UniversalPlugPlatform
						.getInstance().isMusicEnabled(MainActivity.this));
				
	
			}
		});

		payBtn = (Button) findViewById(R.id.test_pay);
		payBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				PaymentManager.PaymentRequest request = new PaymentManager.PaymentRequest();

				request.setProductId("1");
				request.setProductName("大刀");
				request.setProductIndex("1");
			
			
				
				UniversalPlugPlatform
						.getInstance()
						.getPaymentManager()
						.requestPay(MainActivity.this, request,
								new PaymentHandler() {

									@Override
									protected void onPaymentSuccess(
											PaymentEvent event) {
										showToast("Payment Success , orderId : "
												+ event.getOrderId());
									}

									@Override
									protected void onUserCancel() {
										showToast("User Cancel Payment.");
									}

									@Override
									protected void onPaymentFailed() {
										showToast("Payment Failed.");
									}

									
									
						});
				
			}

			
		});

	}

	protected void showTitles(final String titles) {
		handler.post(new Runnable() {
			@Override
			public void run() {

				loginStateText.setText(titles);

			}
		});
	}

	protected void showToast(final String message) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		UniversalPlugPlatform.getInstance().onActivityPause(this);
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		UniversalPlugPlatform.getInstance().onActivityResume(this);
		super.onResume();
	}

	@Override
	protected void onStart() {
		

		UniversalPlugPlatform.getInstance().onActivityStart(this);
		super.onStart();
	}

	@Override
	protected void onStop() {

		
		UniversalPlugPlatform.getInstance().onActivityStop(this);
		super.onStop();
	}

	@Override
	protected void onDestroy() {

		UniversalPlugPlatform.release();
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		UniversalPlugPlatform.getInstance().onActivityResult(requestCode,
				resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

}
