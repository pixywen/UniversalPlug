package com.tatian.up.index;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

import com.tatian.up.utils.AesUtil;
import com.tatian.up.utils.Debug;
import com.tatian.up.utils.EncryptUtil;

/**
 * 计费点映射
 */
public class UniversalPlugIndex {

	private Map<String, UniversalPlugIndexItem> indexMap;

	private static UniversalPlugIndex instance;
	private final String INDEX = "index.dat";
	private static final String TAG = "IndexHelper";

	public UniversalPlugIndex() {

		this.indexMap = new HashMap<String, UniversalPlugIndexItem>();
	}

	public static UniversalPlugIndex getInstance() {
		if (instance == null)
			instance = new UniversalPlugIndex();

		return instance;

	}

	public boolean init(Context context) {

		initFromXML(context);
		return false;
	}

	public boolean init(Context context, String channel) {

		return initFromXML(context, INDEX, channel);

	}

	private boolean initFromXML(Context context) {
		return initFromXML(context, INDEX);
	}


	private boolean initFromXML(Context context, String path, String channel) {
		try {

			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			
			boolean flag = this.initParser(context, parser, path);
			Debug.d(TAG+" init success ? ", flag+"");
			//parser.setInput(context.getAssets().open(xmlPath), "UTF-8");

			for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; parser
					.next()) {
				eventType = parser.getEventType();
				if (eventType == XmlPullParser.START_TAG) {
					if (parser.getName().equals("index")) {
						UniversalPlugIndexItem item = new UniversalPlugIndexItem();
						String index = "";
						for (int i = 0, len = parser.getAttributeCount(); i < len; i++) {
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("key".equals(attrName)) {
								index = attrValue;
							}

							item.put(attrName, attrValue);
						}

						if (item.get("channel") != null
								&& item.get("channel").equals(channel)) {
							indexMap.put(index, item);
							Debug.d(TAG+" value", item.get("value"));
							Debug.d(TAG+" channel", item.get("channel"));
						}

					}
				}
			}

			return true;

		} catch (Exception e) {
			Debug.e(e);
			throw new RuntimeException("Cannot init index");

		}

	}

	private boolean initFromXML(Context context, String path) {
		try {

			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			
			boolean flag = this.initParser(context, parser, path);
			Debug.d(TAG+" init success ? ", flag+"");

			for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; parser
					.next()) {
				eventType = parser.getEventType();
				if (eventType == XmlPullParser.START_TAG) {
					if (parser.getName().equals("index")) {
						UniversalPlugIndexItem item = new UniversalPlugIndexItem();
						String index = "";
						for (int i = 0, len = parser.getAttributeCount(); i < len; i++) {
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("key".equals(attrName)) {
								index = attrValue;
							}

							item.put(attrName, attrValue);
						}

						indexMap.put(index, item);

					}
				}
			}

			return true;

		} catch (Exception e) {
			Debug.w(e);
			throw new RuntimeException("Cannot init index from XML.");

		}

	}

	public String getValue(String index, String key) {

		if (this.indexMap == null)
			return null;
		UniversalPlugIndexItem item = this.indexMap.get(index);
		return item == null ? null : item.get(key);

	}

	public UniversalPlugIndexItem getItem(String index) {
		if (this.indexMap == null)
			return null;
		return this.indexMap.get(index);

	}
	
	public boolean initParser(Context context,XmlPullParser parser,String path){
		
		try{
			
			//Decode
			InputStream stream = context.getAssets().open(path);
			String source = getStreamString(stream);
			String target = AesUtil.decrypt(EncryptUtil.nativeDecode(source), source);
			InputStream dstream = getStringStream(target);
			parser.setInput(dstream,"UTF-8");
			
		}catch(Exception e){
			
			Debug.e(TAG+" init exception","INDEX_ERROR");
			return false;	
		}
			
		return true;
	}
	
	
	

	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));

				StringBuffer tStringBuffer = new StringBuffer();

				String sTempOneLine = new String("");

				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);

				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					tInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static InputStream getStringStream(String inputString) {

		if (inputString != null && !inputString.trim().equals("")) {

			try {

				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						inputString.getBytes());

				return tInputStringStream;

			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
		return null;
	}

}
