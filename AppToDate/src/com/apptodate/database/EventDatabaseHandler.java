package com.apptodate.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.apptodate.ApplicationContextProvider;
import com.apptodate.model.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

public class EventDatabaseHandler extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "eventsManager";
    
    // Contacts table name
    private static final String TABLE_EVENT = "event";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_CREATE_AT = "created_at";
    private static final String KEY_DT_START = "date_start";
    private static final String KEY_DT_END = "date_end";
    private static final String KEY_DISPLAY_NAME = "display_name";
    private static final String KEY_CALENDAR_ID = "calendar_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PLACE = "place";
    private static final String KEY_TITLE ="title";
	
	public EventDatabaseHandler (){
		super (ApplicationContextProvider.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlString = 
				"CREATE TABLE " + TABLE_EVENT + "(" + 
						KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						KEY_CREATE_AT + " DATETIME, " + 
						KEY_DT_START +" DATETIME, " + 
						KEY_DISPLAY_NAME + " STRING, " +
						KEY_DESCRIPTION + " STRING, " +
						KEY_PLACE + " STRING, "+
						KEY_TITLE + " STRING " +
						");";
		db.execSQL(sqlString);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
 
        // Create tables again
        onCreate(db);
	}
	
	public void addEvent (Event ev){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_DT_START, getDateTime(ev.getDtstart()));
		values.put(KEY_CREATE_AT, Calendar.getInstance().getTimeInMillis());
		values.put(KEY_DESCRIPTION, ev.getDescription());
		//values.put(KEY_DISPLAY_NAME, "");
		values.put(KEY_PLACE, ev.getPlace());
		values.put(KEY_TITLE, ev.getTitle());
		
		// Inserting Row
	    db.insert(TABLE_EVENT, null, values);
	    db.close(); // Closing database connection
	}
	
	public ArrayList<Event> readDatabaseContent (){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor recs = db.rawQuery(
				"SELECT " + KEY_ID + ", " +
						KEY_CREATE_AT +", " +
						KEY_DT_START + ", " +
						KEY_DESCRIPTION +", " +
						KEY_PLACE + ", " +
						KEY_TITLE +
						" FROM " + TABLE_EVENT ,
				null
				);
		ArrayList<Event> result = new ArrayList<Event>();
		while (recs.moveToNext()) {
			Event e = new Event();
			e.setId(recs.getLong(recs.getColumnIndex(KEY_ID)));
			e.setCreatedAt( recs.getLong(recs.getColumnIndex(KEY_CREATE_AT)));
			e.setDtstart(new Date(recs.getLong(recs.getColumnIndex(KEY_DT_START))));
			e.setDescription(recs.getString(recs.getColumnIndex(KEY_DESCRIPTION)));
			e.setPlace(recs.getString(recs.getColumnIndex(KEY_PLACE)));
			e.setTitle(recs.getString(recs.getColumnIndex(KEY_TITLE)));
			result.add(e);
		}
		recs.close();
		db.close();
		return result;
	}
	
	public ArrayList<Event> readEventByDate(int year, int month, int day){
		SQLiteDatabase db = this.getReadableDatabase();
		Calendar minDate = new GregorianCalendar (year, month, day, 00, 00, 00);
		Calendar maxDate = new GregorianCalendar (year, month, day, 23, 59, 59);
		String [] dateSearchPattern = new String [] { getDateTime(minDate.getTime()), getDateTime(maxDate.getTime())};
		Log.d("ReadEventByDate", "The used pattern for search is " + dateSearchPattern[0] + " until " + dateSearchPattern[1]);
		Cursor recs = db.query(
				TABLE_EVENT,
				null,
				KEY_DT_START + " BETWEEN ? AND ?",
				dateSearchPattern,
				null, null, null);
		ArrayList<Event> result = new ArrayList<Event>();
		while (recs.moveToNext()) {
			Event e = new Event();
			e.setId(recs.getLong(recs.getColumnIndex(KEY_ID)));
			e.setCreatedAt( recs.getLong(recs.getColumnIndex(KEY_CREATE_AT)));
			e.setDtstart(new Date(recs.getLong(recs.getColumnIndex(KEY_DT_START))));
			Log.d("ReadEventByDate", "The setDtstart is: " + recs.getLong(recs.getColumnIndex(KEY_DT_START) ));
			e.setDescription(recs.getString(recs.getColumnIndex(KEY_DESCRIPTION)));
			e.setPlace(recs.getString(recs.getColumnIndex(KEY_PLACE)));
			e.setTitle(recs.getString(recs.getColumnIndex(KEY_TITLE)));
			result.add(e);
		}
		recs.close();
		db.close();
		return result;
	}
	
	private String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
}
}
