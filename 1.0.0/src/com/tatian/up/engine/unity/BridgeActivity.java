package com.tatian.up.engine.unity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tatian.up.engine.UniversalPlugPlatform;
import com.tatian.up.engine.UniversalPlugPlatform.PlatformInitCompleteCallback;
import com.tatian.up.engine.UniversalPlugRunConfig;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.NotProguard;
import com.unity3d.player.UnityPlayerNativeActivity;

/**
 * @deprecated Use UnityPlayerNativeActivity instead.
 */
public class BridgeActivity extends UnityPlayerNativeActivity implements NotProguard{
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		UniversalPlugRunConfig runConfig = UniversalPlugRunConfig.initFromXML(this);
		Log.d("CHANNEL_ID",runConfig.getChannelId());
		
		UniversalPlugPlatform.init(runConfig,
				new UniversalPlugPlatform.PlatformInitCompleteCallback() {

					@Override
					public void onPlatformInitComplete(int code) {
						switch (code) {
						case PlatformInitCompleteCallback.SUCCESS:
							
							Debug.d("Platform init");
;							break;
						default:
							Debug.d("Platform init faild");
							break;
						}
					}

				});

		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Debug.d("Platform onPause");
		UniversalPlugPlatform.getInstance().onActivityPause(this);
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Debug.d("Platform onResume");
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