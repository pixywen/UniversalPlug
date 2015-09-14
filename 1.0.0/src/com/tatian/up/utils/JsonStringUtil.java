package com.tatian.up.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonStringUtil {

	public static String Map2Json(Map<String, String> params) {

		if (params == null)
			return "";
		return StringMap2JsonObject(params);

	}

	private static String StringMap2JsonObject(Map<String, String> params) {

		JSONObject jo = new JSONObject();
		for (Entry<String, String> entry : params.entrySet()) {

			try {
				jo.put(entry.getKey(), entry.getValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return String.valueOf(jo);

	}
	
	public static Map<String, String> Json2Map(String json){
		
		return Json2StringMap(json);
		
	}
	/**
	 * JsonStrng(JsonObject) to Map 仅解析一层
	 * 
	 * @param json
	 * @return
	 */
	private static Map<String, String> Json2StringMap(String json) {

		Map<String, String> map = new HashMap<String, String>();

		try {
			JSONObject jo = new JSONObject(json);

			Iterator<?> keys = jo.keys();
			while (keys.hasNext()) {
				String key = keys.next().toString();
				String value = (String) jo.get(key);
				map.put(key, value);
				Debug.e("JsonStringUtil", "Json2StringMap key: " + key + " ," +"value "+value);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Debug.e("JsonStringUtil", "Json2StringMap Exception" + e);
			return map;
		}

		return map;

	}

}
