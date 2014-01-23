package com.apptodate;

import java.util.ArrayList;
import com.apptodate.database.EventDatabaseHandler;
import com.apptodate.model.Event;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	CalendarView cal;
	LinearLayout calItems;
	LinearLayout calItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
		cal = (CalendarView) findViewById(R.id.calendarView1);
		calItems = (LinearLayout) findViewById(R.id.linearLayout2);
		calItem = (LinearLayout) findViewById(R.layout.calendar_item);
		cal.setOnDateChangeListener(new OnDateChangeListener() {
		EventDatabaseHandler edh = new EventDatabaseHandler();
		ArrayList<Event> array = new ArrayList<Event>();
		
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				array = edh.readEventByDate(year, month, dayOfMonth);
				
				TextView Time = (TextView) findViewById(R.id.textView2);
				TextView Title = (TextView) findViewById(R.id.textView3);
				DateFormat df = new DateFormat();
				Event event;
				if (array.size() > 0) {
					for (int i = 0; i < array.size(); i++) {
						
						event = array.get(i);
						//getResources().getString(R.string.appointmentClock); = event.getDtstart().toString();
						Title.setText(event.getTitle());
						//Time.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss", event.getDtstart()));
						Time.setText(event.getDtstart().toString());
						//Time.setText(event.getDtstart().toString());
					}
				}
				else {
					Title.setText(R.string.appointmentTitle);
					Time.setText(R.string.appointmentClock);
				}
				//readEventByDate(int year, int month, int day)
				Toast.makeText(getBaseContext(),"Selected Date is\n\n"
					+dayOfMonth+" : "+month+" : "+year , 
					Toast.LENGTH_LONG).show();
			}
		});
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
