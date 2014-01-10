package com.apptodate.database;

import java.util.ArrayList;
import java.util.Calendar;

import com.apptodate.model.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabaseHandler extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "eventsManager";
    
    // Contacts table name
    private static final String TABLE_EVENT = "event";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_CREATE_AT = "created_at";
    private static final String KEY_EVENT_AT = "event_at";
	
	public EventDatabaseHandler (Context context){
		super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlString = 
				"CREATE TABLE " + TABLE_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				KEY_CREATE_AT + " DATETIME , " + KEY_EVENT_AT +" DATETIME" + ");";
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
		values.put(KEY_EVENT_AT, ev.getEventAt());
		values.put(KEY_CREATE_AT, Calendar.getInstance().getTimeInMillis());
		
		// Inserting Row
	    db.insert(TABLE_EVENT, null, values);
	    db.close(); // Closing database connection
	}
	
	public ArrayList<Event> readValuesLocal (){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor recs = db.rawQuery(
				"SELECT " + KEY_ID + ", " + KEY_CREATE_AT +", " + KEY_EVENT_AT + " FROM " + TABLE_EVENT ,
				null
				);
		ArrayList<Event> result = new ArrayList<Event>();
		while (recs.moveToNext()) {
			Event e = new Event();
			e.setId(recs.getLong(0));
			e.setCreatedAt( recs.getLong(1));
			e.setEventAt(recs.getLong(2));
			result.add(e);
		}
		recs.close();
		db.close();
		return result;
	}
}
