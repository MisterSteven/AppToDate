package com.apptodate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btnProfile (View view){
		//Intent intent = new Intent(MainActivity.this, TerminEintragen.class);
		Log.d("Profile-Button", "You have clicked the invade moon button!");
        //startActivity(intent);
	}
	
	public void btnAddEvent (View view){
		Intent intent = new Intent(MainActivity.this, TerminEintragen.class);
		Log.d("Add-Button", "You have clicked the button for adding a new event");
        startActivity(intent);
	}
	
	public void btnInbox (View view){
		Intent intent = new Intent(MainActivity.this, InboxActivity.class);
		Log.d("Inbox-Button", "You have clicked the Inbox-Button");
        startActivity(intent);
	}

}
