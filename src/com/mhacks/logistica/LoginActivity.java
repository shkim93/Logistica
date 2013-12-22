package com.mhacks.logistica;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.*;

public class LoginActivity extends Activity {

	private EditText usernameField;
	private EditText passwordField;
	private TextView errorField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		usernameField = (EditText) findViewById(R.id.login_username);
		passwordField = (EditText) findViewById(R.id.login_password);
		errorField = (TextView) findViewById(R.id.error_messages);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void login(final View v) {
		v.setEnabled(false);
		ParseUser.logInInBackground(usernameField.getText().toString(), passwordField.getText().toString(), new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if(user != null) {
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					switch(e.getCode()) {
					case ParseException.USERNAME_TAKEN:
						errorField.setText("That username is already in use");
						break;
					case ParseException.USERNAME_MISSING:
						errorField.setText("Please enter a username");
						break;
					case ParseException.PASSWORD_MISSING:
						errorField.setText("Please enter a password");
						break;
					case ParseException.OBJECT_NOT_FOUND:
						errorField.setText("Invalid username/password combination");
						break;
					}
					v.setEnabled(true);
				}
			}
		});
	}
	
	public void showRegister(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		finish();
	}

}
