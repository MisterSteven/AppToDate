package com.apptodate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.termin_eintragen, menu);
		return true;
	}
	
	// Dialog for date selection
	public void showDatePickerDialog (View view){
	    this.datePicker.show(getFragmentManager(), "datePicker");
	}
	
	// Dialog for time selection
	public void showTimePickerDialog (View view){
	    this.timePicker.show(getFragmentManager(), "timePicker");
	}
	
	public void btnSaveClick (View view){
		// Read out inserted description
		event.setDescription(this.getContentOfView(R.id.textDescription));
		
		// Read out inserted place
		event.setPlace(this.getContentOfView(R.id.textPlace));
		
		// Read out inserted title
		event.setTitle(this.getContentOfView(R.id.textTitle));
		
		// Creating date and time
		Calendar cal = new GregorianCalendar();
		DatePickerFragment dpf = (DatePickerFragment) this.datePicker;
		TimePickerFragment tpf = (TimePickerFragment) this.timePicker;
		cal.set(dpf.getYear(), dpf.getMonth(), dpf.getDay(), tpf.getHour(), tpf.getMinute());
		
		// Saving date and time in long
		event.setDtstart(cal.getTimeInMillis());
		
		// Inserting event in database
		edh.addEvent(event);
		
		// Close this activity
		this.finish();
	}
	
	// Clear every editable field
	public void clearContent (View view){
		// create new DatePicker object
		this.datePicker = new DatePickerFragment(this);
		// create new TimePicker object
		this.timePicker = new TimePickerFragment(this);
		
		// clear string in field Description
		((TextView) findViewById(R.id.textDescription)).setText("");
		
		// clear string in field Place
		((TextView) findViewById(R.id.textPlace)).setText("");
	}

	// Read out content of ViewElement
	private String getContentOfView (int id){
		return ((TextView) findViewById(id)).getText().toString();
	}
}
