package com.apptodate.database;

import com.apptodate.model.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	public DatabaseHandler (Context con){
		super (con, "apptodate.db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		EventDAO.getInstantace().onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE event;");
		onCreate(db);
	}

}
