package com.example.tct_prototype.view;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.tct_prototype.R;
import com.example.tct_prototype.R.color;
import com.example.tct_prototype.R.id;
import com.example.tct_prototype.R.layout;
import com.example.tct_prototype.R.menu;
import com.example.tct_prototype.controller.Server_Connection;
import com.example.tct_prototype.model.TeamFocus;
import android.view.View.OnClickListener;

public class WeeksTable extends Activity {

	private TableLayout table;
	private static int fontSizeForTable = 16;

	private ArrayList<TeamFocus> teamfocus = new ArrayList<TeamFocus>();

	public ArrayList<TeamFocus> getTeamfocus() {
		return teamfocus;
	}

	public void setTeamfocus(ArrayList<TeamFocus> teamfocus) {
		this.teamfocus = teamfocus;
		createRow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weeks_table);

		this.table = (TableLayout) findViewById(R.id.objects);

		// start async task with request
		new Server_Connection.Async().execute(this);
	}
	
	@Override
	protected void onStart(){
		super.onStart();

		// start async task with request
		new Server_Connection.Async().execute(this);
	}
	
	@Override
	protected void onResume(){
		super.onRestart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weeks_general, menu);
		return true;
	}

	private void goToDetailActivity(TeamFocus tf) {
		Intent intent = new Intent(WeeksTable.this, WeeksDetail.class);
		Bundle b = new Bundle();
		b.putString("id",tf.getTf_Id()); //Your id
		b.putString("name",tf.getTf_Name());
		b.putString("focus01",tf.getTf_Focus01());
		b.putString("focus02",tf.getTf_Focus02());
		b.putString("state",tf.getTf_State());
		intent.putExtras(b); //Put your id to your next Intent
		startActivity(intent);
		finish();
	}

	public void createRow() {

		Log.d("WeeksTable", Integer.toString(this.teamfocus.size()));
		/* Create a new row to be dynamically added. */

		WeeksTable.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// Go through each item in the array
				Log.d("WeeksTable.this.runOnUiThread", "run");
				int i = 0;
				for (final TeamFocus tf : teamfocus) {
					// Create a TableRow and give it an ID
					final TableRow tr = new TableRow(WeeksTable.this);
					tr.setId(i);

					// Create a TextView to house the id
					TextView tf_id = new TextView(WeeksTable.this);
					tf_id.setId(i);
					tf_id.setTextSize(fontSizeForTable);
					tf_id.setGravity(Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					tf_id.setWidth(250);
					tf_id.setText(tf.getTf_Id());
					tr.addView(tf_id);

					// Create a TextView to house the value of the after-tax
					// income
					TextView tf_name = new TextView(WeeksTable.this);
					tf_name.setId(i);
					tf_name.setText(tf.getTf_Name());
					tf_name.setTextSize(fontSizeForTable);
					tf_name.setWidth(400);
					tf_name.setGravity(Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					tf_name.setTextColor(Color.BLACK);
					tr.addView(tf_name);
										
					// Create a TextView to house the value of the focus01
					TextView tf_focus01 = new TextView(WeeksTable.this);
					tf_focus01.setId(i);
					tf_focus01.setText(tf.getTf_Focus01());
					tf_focus01.setTextColor(Color.BLACK);
					tf_focus01.setWidth(400);
					tf_focus01.setTextSize(fontSizeForTable);
					tf_focus01.setGravity(Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					tr.addView(tf_focus01);

					// Create a TextView to house the value of focus02
					TextView tf_focus02 = new TextView(WeeksTable.this);
					tf_focus02.setId(i);
					tf_focus02.setText(tf.getTf_Focus02());
					tf_focus02.setTextColor(Color.BLACK);
					tf_focus02.setWidth(400);
					tf_focus02.setTextSize(fontSizeForTable);
					tf_focus02.setGravity(Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					tr.addView(tf_focus02);

					// Create a TextView to house the value of the state
					TextView tf_state = new TextView(WeeksTable.this);
					tf_state.setId(i);
					tf_state.setText(tf.getTf_State());
					tf_state.setWidth(200);
					tf_state.setTextSize(fontSizeForTable);
					tf_state.setGravity(Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					tf_state.setTextColor(Color.BLACK);
					tr.addView(tf_state);

					
					tr.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							// TODO:
							tr.setBackgroundResource(R.color.blue);
							goToDetailActivity(tf);
						}
					});

					// Add the TableRow to the TableLayout
					WeeksTable.this.table.addView(tr);
					i++;
				}
			}
		});
	}
}