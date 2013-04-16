package com.example.tct_prototype.controller;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonWriter {
	
	//TODO: to use change params to the needed values and set in "object.put"
	public void writeJSON() {
		JSONObject object = new JSONObject();
		try {
			//Example
			object.put("ATT_NAME", "");
			object.put("ATT_FOCUS01", "");
			object.put("ATT_STATE", "");
			object.put("id", "");
			object.put("ATT_FOCUS02", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(object);
	}

}
