package com.apptodate;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	private int year;
	private int month;
	private int day;
	
	private final Button btnDatePicker;
	private final Calendar c;
	private final Activity activity;

	public DatePickerFragment (Activity activity){
		this.activity = activity;
		btnDatePicker = (Button) this.activity.findViewById(R.id.btnDatePicker);
		
		// Use the current date as the default date in the picker
        c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);
        
        setButtonDate();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, this.year, this.month, this.day);

	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        setButtonDate();
    }

	
	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	private void setButtonDate (){
		c.set(year, month, day);
		String s = DateFormat.getDateFormat(activity).format(c.getTime());
		btnDatePicker.setText(s);
		Log.d("Set time", "The date is set to " + s);
	}
}
