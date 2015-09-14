package com.tatian.up.index;

import java.util.HashMap;
import java.util.Map;

public class UniversalPlugIndexItem {

	private Map<String, String> item;

	public UniversalPlugIndexItem() {
		this.item = new HashMap<String, String>();
	}

	public String get(String key) {

		return item.get(key);

	}

	public String put(String key, String value) {

		return item.put(key, value);

	}
}
