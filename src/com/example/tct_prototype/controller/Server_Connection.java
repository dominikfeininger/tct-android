package com.example.tct_prototype.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.apinso.gof.common.util.rest.RestUtil;
import com.example.tct_prototype.R;
import com.example.tct_prototype.model.TeamFocus;
import com.example.tct_prototype.view.WeeksTable;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Server_Connection {

	public static class Async extends AsyncTask<WeeksTable, Void, String> {
		
		private WeeksTable myWeeksTable;

		// starts here
		@Override
		protected String doInBackground(WeeksTable... params) {
			this.myWeeksTable = params[0];
			
			// status update
			TextView mText = new TextView(myWeeksTable);
			mText.setText("Request in Progress");
			
			try {
				// returns param to onPostExcecute
				return startRequest();
			} catch (Throwable t) {
				t.printStackTrace();
			}
			return null;
		}

		// delegates to here
		@Override
		protected void onPostExecute(String result) {
			try {			
				// status update
				TextView mText = new TextView(myWeeksTable);
				mText = (TextView) this.myWeeksTable.findViewById(R.id.ResponseText);
				mText.setText("received data");
				Log.d("Server_Connection", "onPostExecute");
				//Log.d("Server_Connection.onPostExecute.esult.toString()", result.toString());
				myWeeksTable.setTeamfocus(JsonParser.convertForTeamFocus(new StringBuilder(result)));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// standard snyc. request method
		public String startRequest() {
			WebResource webResource = RestUtil
					.getWebResource(
							"https://host17.min-group.com/rest/01/0056/OBT_WEEK?relations=false&ref=false&changeInfo=false&attCodes=ATT_NAME;ATT_STATE;ATT_FOCUS01;ATT_FOCUS02;ATT_FOCUS03;ATT_FOCUS04;ATT_FOCUS05",
							"min_communication", "min#1303");
			webResource = webResource.queryParam("relations", "false");
			webResource = webResource.queryParam("ref", "false");
			webResource = webResource.queryParam("changeInfo", "false");

			ClientResponse response = webResource
					.header("Content-Encoding", RestUtil.UTF_8)
					.acceptLanguage("de").get(ClientResponse.class);

			com.sun.jersey.api.client.ClientResponse.Status responseStatus = response
					.getClientResponseStatus();
			if (!com.sun.jersey.api.client.ClientResponse.Status.OK
					.equals(responseStatus)) {
				// TODO Fehlermeldung
				return "Fehler: " + responseStatus.toString();
			}

			StringBuilder result;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						response.getEntityInputStream(), "Cp1252"));
				String temp = "";
				result = new StringBuilder();
				while ((temp = reader.readLine()) != null) {
					result.append(temp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return e.getLocalizedMessage();
			}
			//Log.d("Server_Connection.startRequest", resultString.toString());
			return result.toString();
		}
	}
}
