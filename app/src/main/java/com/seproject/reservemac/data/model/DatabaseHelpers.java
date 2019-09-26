package com.seproject.reservemac.data.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Created by AB0031 on 18-07-2017.
 */


public class DatabaseHelpers extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MAC";
    public static final String USER_TABLE_NAME = "Users";
    public static final String ROLE = "ROLE";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String UTA_ID = "UTA_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME ";
    public static final String STREETADDRESS = "STREETADDRESS";
    public static final String  CONTACTNO= "CONTACTNO";
    public static final String ZIPCODE = "ZIPCODE";
    public static final String REVOKED = "REVOKED";
    public static final String EMAIL = "EMAIL";
    public static final String NOSHOWS= "NOSHOWS";




    public DatabaseHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        String alltask= "CREATE TABLE " + USER_TABLE_NAME+ " (" +
                USERNAME + " TEXT PRIMARY KEY , " +
                FIRST_NAME+ " TEXT, " +
                LAST_NAME + " TEXT, " +
                ROLE + " TEXT, " +
                PASSWORD+ " TEXT, " +
                UTA_ID + " TEXT, " +
                STREETADDRESS + " TEXT, " +
                CONTACTNO + " INTEGER, "+
                ZIPCODE + " INTEGER, "+
                REVOKED + " INTEGER, "+
                NOSHOWS + " INTEGER, "+
                EMAIL+ " TEXT " + ")";


        sqLiteDatabase.execSQL(alltask);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}