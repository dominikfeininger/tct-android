package com.example.tct_prototype.view;

import com.example.tct_prototype.R;
import com.example.tct_prototype.R.id;
import com.example.tct_prototype.R.layout;
import com.example.tct_prototype.R.menu;
import com.example.tct_prototype.controller.Server_Connection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class WeeksDetail extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weeks_detail);
		Bundle b = getIntent().getExtras();
		showData(b);
		
		//not used, using back method instead directly
		/*
        final Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });
        */
    }
	
	public void back(View view) {
	     // TODO: change view
		Intent myIntent = new Intent(this, WeeksTable.class);
		startActivity(myIntent);
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weeks_general, menu);
		return true;
	}
	
	public void showData(Bundle b){
		
		//  update
		TextView id = (TextView)findViewById(R.id.id);
		//Log.d("id", b.getString("id"));
		id.setText(b.getString("id"));
		TextView name = (TextView)findViewById(R.id.name);
		name.setText(b.getString("name"));
		TextView focus01 = (TextView)findViewById(R.id.focus01);
		focus01.setText(b.getString("focus01"));
		TextView focus02 = (TextView)findViewById(R.id.focus02);
		focus02.setText(b.getString("focus02"));
		TextView state = (TextView)findViewById(R.id.state);
		state.setText(b.getString("state"));
		
	}


}
