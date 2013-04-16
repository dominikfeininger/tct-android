package com.example.tct_prototype.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.tct_prototype.model.TeamFocus;
import android.util.Log;

public class JsonParser {

	// resturns one single objekt with the specified key
	public static String getJSONObject(StringBuilder result, String attributeKey)throws JSONException {
		Log.d("result", result.toString());
		String resultString = new String();

		JSONObject jsonObject = new JSONObject(result.toString());
		JSONArray array = jsonObject.getJSONArray("objects");

		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);

			if (obj.has(attributeKey)) {
				resultString = obj.getString(attributeKey);
			}
		}
		return resultString;
	}

	// parse the whole incomming JSON srting and collect the important data
	public static StringBuilder parseJson(StringBuilder result)
			throws JSONException {

		StringBuilder resultString = new StringBuilder();
		JSONObject jsonObject = new JSONObject(result.toString());
		JSONArray array = jsonObject.getJSONArray("objects");

		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);

			if ("WS0010|WS0020".contains(obj.getString("ATT_STATE"))) {
				resultString.append(obj.getString("ATT_NAME")).append("\n");
				if (obj.has("ATT_FOCUS01")) {
					resultString.append(obj.getString("ATT_FOCUS01")).append(
							"\n");
				}
				if (obj.has("ATT_FOCUS02")) {
					resultString.append(obj.getString("ATT_FOCUS02")).append(
							"\n");
				}
				// Focus >2 not required
				if (obj.has("ATT_FOCUS03")) {
					resultString.append(obj.getString("ATT_FOCUS03")).append(
							"\n");
				}
				if (obj.has("ATT_FOCUS04")) {
					resultString.append(obj.getString("ATT_FOCUS04")).append(
							"\n");
				}
				if (obj.has("ATT_FOCUS05")) {
					resultString.append(obj.getString("ATT_FOCUS05")).append(
							"\n");
				}
				resultString.append("\n\n");
			}
		}
		return resultString;
	}

	public static ArrayList<TeamFocus> convertForTeamFocus(StringBuilder result)throws JSONException {
		Log.d("JsonParser", "convertForTeamFocus");
		ArrayList<TeamFocus> tmp_tf_array = new ArrayList<TeamFocus>();

		//Log.d("JsonParser.convertForTeamFocus.jsonObject", result.toString());
		JSONObject obj = new JSONObject(result.toString());
		
		JSONArray array = obj.getJSONArray("objects");
		Log.d("JsonParser.convertForTeamFocus", Integer.toString(array.length()));
		//Log.d("JsonParser.convertForTeamFocus.Objects", array.toString());
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject tmpObj = array.getJSONObject(i);
			TeamFocus tmp_tf = new TeamFocus();
			tmp_tf_array.add(tmp_tf.parseJsonToTeamFocus(tmpObj, tmp_tf));
		}
		
		return tmp_tf_array;
	}
}
