package com.example.tct_prototype.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TeamFocus {

	private String tf_Id;
	private String tf_Name;
	private String tf_Focus01;
	private String tf_Focus02;
	private String tf_State;

	private final String json_id = "id";
	private final String json_name = "ATT_NAME";
	private final String json_focus01 = "ATT_FOCUS01";
	private final String json_focus02 = "ATT_FOCUS01";
	private final String json_state = "ATT_STATE";

	public TeamFocus() {

	}

	public TeamFocus(String tf_Id, String tf_Name, String tf_Focus01,
			String tf_Focus02, String tf_State) {
		super();
		this.tf_Id = tf_Id;
		this.tf_Name = tf_Name;
		this.tf_Focus01 = tf_Focus01;
		this.tf_Focus02 = tf_Focus02;
		this.tf_State = tf_State;
	}

	public String getTf_Id() {
		return tf_Id;
	}

	public void setTf_Id(String tf_Id) {
		this.tf_Id = tf_Id;
	}

	public String getTf_Name() {
		return tf_Name;
	}

	public void setTf_Name(String tf_Name) {
		this.tf_Name = tf_Name;
	}

	public String getTf_Focus01() {
		return tf_Focus01;
	}

	public void setTf_Focus01(String tf_Focus01) {
		this.tf_Focus01 = tf_Focus01;
	}

	public String getTf_Focus02() {
		return tf_Focus02;
	}

	public void setTf_Focus02(String tf_Focus02) {
		this.tf_Focus02 = tf_Focus02;
	}

	public String getTf_State() {
		return tf_State;
	}

	public void setTf_State(String tf_State) {
		this.tf_State = tf_State;
	}

	public TeamFocus parseJsonToTeamFocus(JSONObject jsonObject, TeamFocus tmp_Tf) throws JSONException {
		//Log.d("TeamFocus", "parseJsonToTeamFocus");
		//TODO: check for impl.
		if (jsonObject.has(this.json_id)) {
			//tmp_Tf.tf_Id = Integer.parseInt(jsonObject.getString(json_id));
			tmp_Tf.tf_Id = jsonObject.getString(json_id);
		}
		if (jsonObject.has(this.json_name)) {
			tmp_Tf.tf_Name = jsonObject.getString(json_name);
		}
		if (jsonObject.has(this.json_focus01)) {
			tmp_Tf.tf_Focus01 = jsonObject.getString(json_focus01);
		}
		if (jsonObject.has(this.json_focus02)) {
			tmp_Tf.tf_Focus02 = jsonObject.getString(json_focus02);
		}
		if (jsonObject.has(this.json_state)) {
			tmp_Tf.tf_State = jsonObject.getString(json_state);
		}

		return tmp_Tf;
	}
}
