package com.apptodate.model;

import java.util.ArrayList;

import com.apptodate.database.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class EventDAO {
	
	// Variable for Singleton-Pattern
	private static EventDAO singleton;
	
	// Instance methode for Singleton-Pattern
	public static EventDAO getInstantace(){
		// Check if variable is null
		if (singleton == null){
			// Initialize Variable
			singleton = new EventDAO();
		}
		// return Singleton
		return singleton;
	}
	
	public void onCreate (SQLiteDatabase db){
		String sqlString = 
				"create table event(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
				"event_at DATETIME" +
				");";
		db.execSQL(sqlString);
	}
	
	public void saveValuesLocal (Event ev){
		SQLiteDatabase con = Database.getInstantace().getWritableDatabase();
		SQLiteStatement stmt = con.compileStatement(
				"INSERT INTO event(event_at) VALUES(?)");
		stmt.bindLong(1, ev.eventAt);
		stmt.executeInsert();
		stmt.close();
		con.close();
	}
	
	public ArrayList<Event> readValuesLocal (){
		SQLiteDatabase con = Database.getInstantace().getReadableDatabase();
		Cursor recs = con.rawQuery("SELECT _id,created_at, event_at	FROM event",new String[] {});
		ArrayList<Event> result = new ArrayList<Event>();
		while (recs.moveToNext()) {
			Event e = new Event();
			e.id = recs.getLong(0);
			e.createdAt = recs.getLong(1);
			e.eventAt = recs.getLong(2);
			result.add(e);
		}
		recs.close();
		con.close();
		return result;
	}

}
