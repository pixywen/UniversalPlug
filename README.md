# UniversalPlug

##OverView


UniversalPlug(短代版本) 旨在为CP提供快速接入**运营商短代计费**的方案，包括 移动MM，移动基地，联通，爱游戏各本平台SDK接入及多网融合SDK接入。

##ChangeLog


* 15Sep14 提供各运营商基本接口

##EnvironmentSetup

####1.Import

SDK 结构如下，将assets，libs，res 目录下内容复制到目标工程

    
    .
    ├── assets
    │    └── UniversalPlugSDK.xml                      //配置文件
    ├── libs     
    │    ├── armeabi
    │    ├── armeabi-v7a
    │    ├── mips
    │    ├── x86 
    │    └── universal_plug_sdk_xx_release.jar       //SDK lib
    └── res
         └── values
                └── com_tatian_up_extend_strings.xml   //自定义字符串配置文件
                
                
####2.Manifest Config
2.1 **permissions**

	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
	
2.2 **Activitys and Services**

	<activity
        android:name="com.tatian.ip.ui.SplashActivity"
        android:configChanges="keyboardHidden|orientation|screenSize">
        <intent-filter>
           <action android:name="android.intent.action.MAIN" />

           <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
        
    <activity
         android:name="com.tatian.up.ui.ExceptionActivity"
         android:configChanges="keyboardHidden|orientation|screenSize" >
    </activity>
    
    
    
2.3 **继承或使用UniversalPlugApplication(*)**

	import com.tatian.up.engine.UniversalPlugApplication;

	public class YourApplication extends UniversalPlugApplication{

		@Override
		public void onCreate() {

			super.onCreate();
		}
	
	}
	
2.4 **继承BridgeActivity(*)**


	import com.tatian.up.engine.unity.BridgeActivity;

	public class YourMainActivity extends BridgeActivity{

    	//...

	}
                 
                
2.5 **配置闪屏页**

* 设置SplashActivity为入口Activity

		<activity
            android:name="com.tatian.up.ui.SplashActivity"
            android:configChanges="keyboardHidden|orientation|screenSize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>	
        
        
        								  	                                                          
								  	                                                            
* 去掉原主Activity filter

		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />


* 配置闪屏路径**up_main_activity**,配置文件位于 **/values/com_tatian_extend_strings.xml**. UniversalPlug SDK 将会在闪屏结束之后调用游戏主Activity

		<resources>

    		<string name="up_exit_title">是否退出游戏?</string>  
    		<string name="up_exit_confirm">确定</string>      
    		<string name="up_exit_cancel">再玩玩</string>     
    		<string name="up_splash_path_1"></string> 
    		<string name="up_splash_path_2"></string> 
    		<string name="up_splash_path_3"></string>  
    		<string name="up_splash_path_4"></string>    
    		<string name="up_splash_path_5"></string>  
    		<string name="up_splash_duration">5</string>   
    		<string name="up_main_activity">com.up.demo.MainActivity</string>
    		<string name="up_about_content">版权声明:\n\n\n版权声明正文</string>
    		<string name="up_about_title">关于</string> 

		</resources>



##  APIs

**1.支付接口(STAgent.pay)(*)**

	/**
     * 
     * @param paymentRequest 支付请求
     * @param gameObject UnityGameObject 名
     * @param runtimeScript String 回调函数名
     */
    public static void pay(String paymentRequest, final String gameObject,
            final String runtimeScript){


    }
    
**支付参数(paymentRequest Json格式字符串)**

		{
          "productIndex":"001",                          //必填，商品计费索引
          "productDescription":"一把大刀(默认为空)",       //商品描述，可为空
          "count":"默认为1"，                            //商品数量，默认为1
          "productName":"大刀(默认为空)"                 //商品名称
        }

**回调参数(Json格式字符串)**
 	
 		{
    		"status":"onPaymentSuccess",  //支付成功
    		"productIndex":"1",          //商品索引码    
    	}

    	{"status":"onUserCancel"}  //用户取消支付

   	 	{"status":"onPaymentFailed"}  //支付失败

**2.退出接口(STAgent.exit)(*)**

当运营商(渠道)存在退出接口时，此接口会自动调用运营商(渠道)退出接口，

若运营商(渠道)无退出接口，则会调用SDK自带Dialog与用户交互。

SDK Dialog 退出提示内容(标题)及选项字段可由用户在com_tatian_up_extend_strings.xml中自行配置

    /**
     * 
     * @param gameObject String UnityGameObject 名
     * @param runtimeScript String 回调函数名
     */
    public static void exit(final String gameObject, final String runtimeScript)

回调参数(json格式字符串)


    {"status":"onExit"}  //退出成功(程序在此回调处理资源回收等操作)

    {"status":"onCancel"}  //用户取消退出
    
**3.查看更多游戏接口(STAgent.viewMoreGames)(*)**

当运营商(渠道)存在查看更多接口时，会调用其接口，若无，则不会做处理。


    public static void viewMoreGames()
    
**4.是否打开游戏声音接口(STAgent.isMusicEnabled)(*)**

    /**
     * 
     * @return boolean 是否打开游戏声音
     */
    public static void isMusicEnabled()
    
**5.获得渠道号接口(STAgent.getChannel)**

提供给用户用于确认当前渠道号，详细参照渠道对照表

    /**
     * 
     * @return int 渠道编码(参照对照表)
     */
    public static int getChannel()
    
**6.关于接口(STAgent.about)**

此接口调用关于页，页面内容包括(客服、版权声明、官网链接，具体内容根据渠道不同可自定义)

默认内容可由用户在com_tatian_up_extend_strings.xml中自行配置


    public static void about()
    
##  Technical Support

Please contact: wen.tao.cnu@gmail.com


