package com.mhacks.logistica;

import com.parse.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Parse.initialize(this, "6xvNnQDDas1SBRTTU21BsXldbOUyLw2Ch93Jp7tY", "S3UjFnLPDWEYh0cuksp5P3Q2NMQIJ0MFNqpyklBr"); 
		ParseAnalytics.trackAppOpened(getIntent());
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
		
		setContentView(R.layout.activity_main);

	}

	public void logOut(View v) {
		ParseUser.logOut();
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
