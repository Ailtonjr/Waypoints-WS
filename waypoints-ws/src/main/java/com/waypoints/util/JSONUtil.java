package com.waypoints.util;

import com.google.gson.Gson;

public class JSONUtil {

	public static String getJSON(Object obj) {
		Gson gson = new Gson();
		gson.toString();
		
		return gson.toJson(obj);
	}
	
}
