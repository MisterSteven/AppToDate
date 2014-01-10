package com.apptodate.database;

import java.util.ArrayList;

import com.apptodate.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHandler extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "userManager";
    
    // Contacts table name
    private static final String TABLE_USER = "user";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_SECOUND_NAME = "secound_name";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_COURSE = "course";
    
    public UserDatabaseHandler (Context context){
		super (context, DATABASE_NAME, null, DATABASE_VERSION);
	}
    
    @Override
	public void onCreate(SQLiteDatabase db) {
		String sqlString = 
				"CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						KEY_FIRST_NAME + " TEXT , " + KEY_SECOUND_NAME + " TEXT , " + KEY_SEMESTER + " INTEGER , " + KEY_COURSE +" TEXT" + ");";
		db.execSQL(sqlString);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
 
        // Create tables again
        onCreate(db);
	}
	
	public void addUser (User user){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, user.getFirstName());
		values.put(KEY_SECOUND_NAME, user.getLastName());
		values.put(KEY_SEMESTER, user.getSemester());
		values.put(KEY_COURSE, user.getCourse());
		
		// Inserting Row
	    db.insert(TABLE_USER, null, values);
	    db.close(); // Closing database connection
	}
	
	public ArrayList<User> readAllUser (){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor recs = db.rawQuery(
				"SELECT " + KEY_ID + ", " + KEY_FIRST_NAME +", " + KEY_SECOUND_NAME + ", " + KEY_SEMESTER + ", " + KEY_COURSE + " FROM " + TABLE_USER ,
				null
				);
		ArrayList<User> result = new ArrayList<User>();
		while (recs.moveToNext()) {
			User user = new User ();
			user.setId(recs.getLong(0));
			user.setFirstName( recs.getString(1));
			user.setLastName(recs.getString(2));
			user.setSemester(recs.getInt(3));
			user.setCourse(recs.getString(4));
			result.add(user);
		}
		recs.close();
		db.close();
		return result;
	}
}
