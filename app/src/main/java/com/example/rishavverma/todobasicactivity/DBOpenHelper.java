package com.example.rishavverma.todobasicactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RishavVerma on 7/7/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper{

    final static String TABLE_NAME="tablename";
    final static String ID = "id";
    final static String TABLE_COL1 = "name";
    final static String TABLE_COL2 = "type";
    final static String TABLE_COL3 = "details";
    final static String DBNAME = "database.db";

    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table "+TABLE_NAME+ " ( "+ID+" integer primary key autoincrement, "+TABLE_COL1+" text, "+TABLE_COL2+" text, "+TABLE_COL3+" text);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nothing yet
    }
}
