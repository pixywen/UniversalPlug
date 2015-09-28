package com.tatian.up.engine.sdkproxy;

import android.app.Activity;
import android.content.Intent;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.engine.controller.Constants;
import com.tatian.up.event.handler.ExitHandler;
import com.tatian.up.index.UniversalPlugIndex;
import com.tatian.up.manager.PaymentManager;
import com.tatian.up.utils.Carrier;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.SIMInfoUtils;

public class FUSIONPlatformProxy extends UniversalPlugPlatform {
	
	public UniversalPlugPlatform proxy;

	public FUSIONPlatformProxy(UniversalPlugRunConfig config, final PlatformInitCompleteCallback callback) {
		super(config, callback);

		// TODO:Init Proxy SDK Here.
		
		SIMInfoUtils.getInstance().init(config.getContext());
		
		String proxy_name="";
		
		String fusion = config.getExtraAttribute("extraLIB");
		
		switch(SIMInfoUtils.getInstance().getCarriers()){
		
		case Carrier.CHINA_MOBILE:
			
			if(fusion==null || fusion=="FUSION")
			  proxy_name = "MM";
			else 
			  proxy_name = "JD";
			
			break;
		case Carrier.CHINA_TELECOM:
			
			proxy_name = "EGAME";
			break;
		case Carrier.CHINA_UNICOM:
			
			proxy_name = "UNIPAY";
			break;
			
		default:
			proxy_name = "JD";
			break;
		}
		
		
		initProxy(proxy_name,config,callback);
		UniversalPlugIndex.getInstance().init(config.getContext(),proxy_name);
		
	}
		
	public void initProxy(String proxyName,UniversalPlugRunConfig config, final PlatformInitCompleteCallback callback){
		
		try {
			Class<?> sdkClass = Class
					.forName(Constants.PROXY_PACKAGE_NAME + proxyName
							+ "PlatformProxy");
			
			config.setExtraAttribute("extraSDK", proxyName);
			
			proxy = (UniversalPlugPlatform) (sdkClass.getConstructor(
					UniversalPlugRunConfig.class,
					PlatformInitCompleteCallback.class).newInstance(config,
					callback));
		} catch (Exception e) {
			Debug.w("UniversalPlugPlatform", "Init SDK Failed : " + proxyName);
			Debug.w(e);
			callback.onPlatformInitComplete(PlatformInitCompleteCallback.INIT_FAILED);
		}
		
	}

	@Override
	public void exit(Activity activity, ExitHandler handler) {
		// TODO Auto-generated method stub
		proxy.exit(activity, handler);
	}

	@Override
	public boolean isMusicEnabled(Activity activity) {
		// TODO Auto-generated method stub
		return proxy.isMusicEnabled(activity);
	}

	@Override
	public void viewMoreGames(Activity activity) {
		// TODO Auto-generated method stub
		proxy.viewMoreGames(activity);
	}

	@Override
	public void onActivityResume(Activity activity) {
		// TODO Auto-generated method stub
		proxy.onActivityResume(activity);
	}

	@Override
	public void onActivityPause(Activity activity) {
		// TODO Auto-generated method stub
		proxy.onActivityPause(activity);
	}

	@Override
	public void onActivityStart(Activity activity) {
		// TODO Auto-generated method stub
		proxy.onActivityStart(activity);
	}

	@Override
	public void onActivityStop(Activity activity) {
		// TODO Auto-generated method stub
		proxy.onActivityStop(activity);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		proxy.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public PaymentManager getPaymentManager() {
		// TODO Auto-generated method stub
		return proxy.getPaymentManager();
	}
	
	

	

}
