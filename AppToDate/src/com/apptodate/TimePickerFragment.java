package com.apptodate;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
	
	private int hour;
	private int minute;
	
	private final Button btnTimePicker;
	private final Calendar c;
	private final Activity activity;
	
	public TimePickerFragment(Activity activity){
		this.activity = activity;
		btnTimePicker = (Button) this.activity.findViewById(R.id.btnTimePicker);
        // Use the current time as the default values for the picker
        c = Calendar.getInstance();
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        
        // Refresh button lable with new time
        setButtonTime();
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, this.hour, this.minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    	
    	// Save selected time and hour 
        this.hour = hourOfDay;
        this.minute = minute;
        
        // Refresh button lable with new time
        setButtonTime();
    }

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	
	private void setButtonTime (){
		// Set selected time and hour to calendar object
		c.set(0, 0, 0, this.hour, this.minute);
		
		// Formating string with local format
		String s = DateFormat.getTimeFormat(activity).format(c.getTime());
		
		// Set selected time to button lable
		btnTimePicker.setText(s);
		Log.d("Set time", "The time is set to " + s);
	}

}
