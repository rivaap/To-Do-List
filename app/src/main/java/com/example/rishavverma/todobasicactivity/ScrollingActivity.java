package com.example.rishavverma.todobasicactivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import java.util.Calendar;

public class ScrollingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle getIntent = getIntent().getExtras();
        String str1 = getIntent.getString("editWindowNameKey");
        String str2 = getIntent.getString("editWindowTypeKey");
        String str3 = getIntent.getString("editWindowDetailsKey");
        final long recievedDBid = getIntent.getLong("ID");
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText1.setText(str1);
        editText2.setText(str2);
        editText3.setText(str3);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                EditText editText1 = (EditText) findViewById(R.id.editText1);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);

                DBOpenHelper dbOpenHelper = new DBOpenHelper(ScrollingActivity.this);
                SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBOpenHelper.TABLE_COL1, editText1.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL2, editText2.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL3, editText3.getText().toString());


                database.update(DBOpenHelper.TABLE_NAME,contentValues,DBOpenHelper.ID + " = " + "\"" + recievedDBid + "\"", null);
                //Log.d("rex", recievedDBid+"");


//                String editWindowNameBack =editText1.getText().toString() ;
//                String editWindowTypeBack = editText2.getText().toString();
//                String editWindowDetailsBack =editText3.getText().toString() ;

//                Bundle bundle = new Bundle();
//                bundle.putString("editWindowNameBackKey",editWindowNameBack);
//                bundle.putString("editWindowTypeBackKey",editWindowTypeBack);
//                bundle.putString("editWindowDetailsBackKey",editWindowDetailsBack);
                Intent intent = new Intent(ScrollingActivity.this, BasicActivity.class);
                //intent.putExtras(bundle);
                startActivity(intent);




//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });








    }
}
