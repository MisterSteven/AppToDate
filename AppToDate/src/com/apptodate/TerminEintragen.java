package com.apptodate;

import java.util.ArrayList;
import java.util.Calendar;

import com.apptodate.database.EventDatabaseHandler;
import com.apptodate.model.Event;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class TerminEintragen extends Activity {

	static final int DATE_DIALOG_ID = 999;
	
	private int year;
	private int month;
	private int day;
	private Event event;
	private EventDatabaseHandler edh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_termin_eintragen);
		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		event = new Event();
		edh = new EventDatabaseHandler(this);
		readDBContent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.termin_eintragen, menu);
		return true;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month, day);
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public void btnDateClick (View view){
		showDialog(DATE_DIALOG_ID);
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
