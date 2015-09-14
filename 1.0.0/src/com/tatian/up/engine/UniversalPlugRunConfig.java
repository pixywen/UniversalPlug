package com.tatian.up.engine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

import com.tatian.up.engine.controller.Constants;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.NotProguard;

/**
 * 平台运行的参数
 * <p>
 * 初始化平台时需传递这个类的实例
 */
public final class UniversalPlugRunConfig implements NotProguard {
	public static final int SCREEN_ORIENTATION_AUTO = -1;
	public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
	public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
	private Context context;
	private String appId;
	private String appKey;
	private String channelId;
	private Map<String, String> extraAttrMap;
	private Locale locale;
	private boolean checkVersion;
	private int screenOrientation = SCREEN_ORIENTATION_AUTO;
	private boolean debug;

	/**
	 * 运行参数的构造方法
	 * 
	 * @param context
	 *            应用的Context引用
	 * @param appId
	 *            应用的appId
	 * @param appKey
	 *            应用的appKey
	 * @param channelId
	 *            渠道Id
	 */
	public UniversalPlugRunConfig(Context context, String appId, String appKey,
			String channelId) {
		this.context = context;
		this.appId = appId;
		this.appKey = appKey;
		this.channelId = channelId;
		extraAttrMap = new HashMap<String, String>();
		debug = false;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isDebug() {
		return debug;
	}

	private UniversalPlugRunConfig(Context context) {
		this.context = context;
		extraAttrMap = new HashMap<String, String>();
	}

	public void setExtraAttribute(String key, String value) {
		extraAttrMap.put(key, value);
	}

	public String getExtraAttribute(String name) {
		return extraAttrMap.get(name);
	}

	public Map<String, String> getExtraAttributeMap() {
		return extraAttrMap;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public boolean enableCheckVersion() {
		return checkVersion;
	}

	public void setCheckVersion(boolean checkVersion) {
		this.checkVersion = checkVersion;
	}

	public Context getContext() {
		return context;
	}

	public int getScreenOrientation() {
		return screenOrientation;
	}

	public void setScreenOrientation(int screenOrientation) {
		this.screenOrientation = screenOrientation;
	}

	public static UniversalPlugRunConfig initFromXML(Context context) {
		return initFromXML(context, Constants.CONFIG_XML_NAME);
	}

	public static UniversalPlugRunConfig initFromXML(Context context, String xmlPath) {
		try {
			UniversalPlugRunConfig config = new UniversalPlugRunConfig(context);
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(context.getAssets().open(xmlPath), "UTF-8");

			for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; parser
					.next()) {
				eventType = parser.getEventType();
				if (eventType == XmlPullParser.START_TAG) {
					if (parser.getName().equals("RunConfig")) {

						for (int i = 0, len = parser.getAttributeCount(); i < len; i++) {
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("appId".equals(attrName)) {
								config.appId = attrValue;
							} else if ("appKey".equals(attrName)) {
								config.appKey = attrValue;
							} else if ("channelId".equals(attrName)) {
								config.channelId = attrValue;
							} else if ("language".equals(attrName)) {
								config.locale = new Locale(attrValue);
							} else if ("checkVersion".equals(attrName)) {
								config.checkVersion = Boolean
										.parseBoolean(attrValue);
							} else if ("debug".equals(attrName)) {
								config.debug = Boolean.parseBoolean(attrValue);
							} else if ("screenOrientation".equals(attrName)) {
								if ("LANDSCAPE".equalsIgnoreCase(attrValue)) {
									config.screenOrientation = UniversalPlugRunConfig.SCREEN_ORIENTATION_LANDSCAPE;
								} else if ("PORTRAIT"
										.equalsIgnoreCase(attrValue)) {
									config.screenOrientation = UniversalPlugRunConfig.SCREEN_ORIENTATION_PORTRAIT;
								} else {
									config.screenOrientation = UniversalPlugRunConfig.SCREEN_ORIENTATION_AUTO;
								}
							} else {
								config.extraAttrMap.put(attrName, attrValue);
							}
						}

					}
				} else if (eventType == XmlPullParser.END_TAG) {

				}
			}
			return config;
		} catch (Exception e) {
			Debug.w(e);
			throw new RuntimeException("Cannot init RunConfig from XML.");
		}
	}
}
