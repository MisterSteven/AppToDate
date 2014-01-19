package com.apptodate;

import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.apptodate.database.EventDatabaseHandler;
import com.apptodate.model.Event;

public class TerminEintragen extends Activity {

	private Event event;
	private EventDatabaseHandler edh;
	private DialogFragment timePicker;
	private DialogFragment datePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_termin_eintragen);
		event = new Event();
		edh = new EventDatabaseHandler(this);
		this.datePicker = new DatePickerFragment(this);
		this.timePicker = new TimePickerFragment(this);
		readDBContent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.termin_eintragen, menu);
		return true;
	}
	
	public void showDatePickerDialog (View view){
	    this.datePicker.show(getFragmentManager(), "datePicker");
	}
	
	public void showTimePickerDialog (View view){
	    this.timePicker.show(getFragmentManager(), "timePicker");
	}
	
	public void btnSaveClick (View view){
		event.setDescription(this.getContentOfView(R.id.textDescription));
		event.setPlace(this.getContentOfView(R.id.textPlace));
		edh.addEvent(event);
	}

	private String getContentOfView (int id){
		return ((TextView) findViewById(id)).getText().toString();
	}
	
	private void readDBContent(){
		ArrayList<Event> array = edh.readDatabaseContent();
		for(Event event:array){
			Log.d("EventDatabase", "ID:" + event.getId() + " Description:" + event.getDescription() + " Place: " + event.getPlace());
		}
	}
}
