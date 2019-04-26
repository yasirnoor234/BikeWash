package com.example.yasirnoor.bikewash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "register.db";
    public static final int DBVersion =1;
    public static final String TABLE_NAME = "register";

    /*public static final String COL_1 ="Id";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "ConfirmPassword";
    public static final String COL_6 = "Phone";
    */

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String registrationTable = "CREATE TABLE register(firstname VARCHAR,email VARCHAR,password VARCHAR,confirmpassword VARCHAR,phone VARCHAR);";
        db.execSQL(registrationTable);

        //db.execSQL("CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,Email TEXT,Password TEXT,ConfirmPassword TEXT,Phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //DROP Old Table If Exists
        onCreate(db);


    }
}
