package com.mhacks.logistica;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.*;

public class RegisterActivity extends Activity {

	private EditText usernameField;
	private EditText passwordField;
	private TextView errorField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		usernameField = (EditText) findViewById(R.id.register_username);
		passwordField = (EditText) findViewById(R.id.register_password);
		errorField = (TextView) findViewById(R.id.error_messages);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void register(final View v) {
		if(usernameField.getText().length() == 0 || passwordField.getText().length() == 0) {
			return;
		}
		
		v.setEnabled(false);
		ParseUser user = new ParseUser();
		user.setUsername(usernameField.getText().toString());
		user.setPassword(passwordField.getText().toString());
		errorField.setText("");
		
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				if(e == null) {
					Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					switch(e.getCode()) {
					case ParseException.USERNAME_TAKEN:
						errorField.setText("Username has already been taken");
						break;
					case ParseException.USERNAME_MISSING:
						errorField.setText("Please enter a username");
						break;
					case ParseException.PASSWORD_MISSING:
						errorField.setText("Please enter a password");
						break;
						
					default:
						errorField.setText(e.getLocalizedMessage());
					}
					v.setEnabled(true);
				}
			}
		});
		
	}
	
	public void showLogin(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

}
