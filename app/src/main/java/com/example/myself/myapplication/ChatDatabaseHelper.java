package com.example.myself.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Myself on 2018-03-03.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION_NUM=10;
    public static final String DATABASE_NAME="messages.db";
    public final String KEY_MESSAGE="Message";
    private final String KEY_ID = "_ID";
    public final String TABLE_NAME="Messages";
//    private static int oldVersion=0;
//    private static int newVersion=11;
    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null,VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        sqLiteDatabase.execSQL( "CREATE TABLE " + TABLE_NAME + " ( _"+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_MESSAGE+" text);" );
//        onOpen(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.e("On upgrade is called", "");
        //Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
//        oldVersion=newVersion;
//        newVersion++;

    }

//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//    }
}
