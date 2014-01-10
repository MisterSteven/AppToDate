package com.apptodate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Database {

	// Variable for Singleton-Pattern
		private static Database singleton;
		
		// Instance methode for Singleton-Pattern
		public static Database getInstantace(){
			// Check if variable is null
			if (singleton == null){
				return null;
			}
			// return Singleton
			return singleton;
		}
		public static void initInstantace(Context con){
			// Check if variable is null
			if (singleton == null){
				// Initialize Variable
				singleton = new Database(con);
			}
		}
		
	private DatabaseHandler db;
	
	public Database(Context con){
		db = new DatabaseHandler (con);
	}
	
	public SQLiteDatabase getWritableDatabase(){
		return db.getWritableDatabase();
	}
	
	public SQLiteDatabase getReadableDatabase(){
		return db.getReadableDatabase();
	}
}
