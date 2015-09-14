package com.tatian.up.engine.unity;

import java.util.HashMap;
import java.util.Map;

import com.tatian.up.utils.JsonStringUtil;
import com.tatian.up.utils.NotProguard;

public class UnityBridge implements NotProguard {

	public static String Status2String(String status, Map<String, String> params) {

		if (params == null)
			params = new HashMap<String, String>();

		params.put("status", status);
		return JsonStringUtil.Map2Json(params);

	}

}
