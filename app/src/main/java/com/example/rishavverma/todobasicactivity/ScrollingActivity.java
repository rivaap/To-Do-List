package com.example.rishavverma.todobasicactivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

public class ScrollingActivity extends AppCompatActivity {


    ImageButton dateButton;
    TextView dateTextView;
    TextView timeTextView;
    ImageButton timeButton;

    boolean isDateTimeSet;

    java.util.Calendar todoCalendar;
    int todoYear;
    int todoMonth;
    int todoDayOfMonth;
    int todoHourOfDay;
    int todoMinute;

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
        String str4 = getIntent.getString("editWindowDateKey");
        String str5 = getIntent.getString("editWindowTimeKey");
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        TextView timeTextViewEdit = (TextView) findViewById(R.id.timeTextViewEdit);
        TextView dateTextViewEdit = (TextView) findViewById(R.id.dateTextViewEdit);
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        timeTextViewEdit.setText("");
        dateTextViewEdit.setText("");
        editText1.setText(str1);
        editText2.setText(str2);
        editText3.setText(str3);
        dateTextViewEdit.setText(str4);
        timeTextViewEdit.setText(str5);




        //////////////////////////////////////////////calendar code

        dateButton = (ImageButton) findViewById(R.id.dateButtonEdit);
        dateTextView = (TextView) findViewById(R.id.dateTextViewEdit);
        timeButton = (ImageButton) findViewById(R.id.timeButtonEdit);
        timeTextView = (TextView) findViewById(R.id.timeTextViewEdit);

        final java.util.Calendar currCalendar = java.util.Calendar.getInstance();
        final int currYear = currCalendar.get(java.util.Calendar.YEAR);
        final int currMonth = currCalendar.get(java.util.Calendar.MONTH);
        final int currDay = currCalendar.get(java.util.Calendar.DAY_OF_MONTH);
        final int currHour = currCalendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int currMin = currCalendar.get(java.util.Calendar.MINUTE);

        todoCalendar = java.util.Calendar.getInstance();
        todoYear = todoCalendar.get(java.util.Calendar.YEAR);
        todoMonth = todoCalendar.get(java.util.Calendar.MONTH);
        todoDayOfMonth = todoCalendar.get(java.util.Calendar.DAY_OF_MONTH);
        todoHourOfDay = 10;
        todoMinute = 0;

        isDateTimeSet = false;

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ScrollingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        todoYear = year;
                        todoMonth = month;
                        todoDayOfMonth = dayOfMonth;
                        todoCalendar.set(todoYear, todoMonth, todoDayOfMonth, todoHourOfDay, todoMinute);
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy");
                        dateTextView.setText(sdf.format(todoCalendar.getTime()));
                        isDateTimeSet = true;
                    }
                },currYear,currMonth,currDay);
                datePickerDialog.getDatePicker().setMinDate(currCalendar.getTimeInMillis());
                datePickerDialog.show();

            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScrollingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        todoHourOfDay = hourOfDay;
                        todoMinute = minute;
                        todoCalendar.set(todoYear, todoMonth, todoDayOfMonth, todoHourOfDay, todoMinute);
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("h:mm a");
                        timeTextView.setText(sdf.format(todoCalendar.getTime()));
                        isDateTimeSet = true;
                    }
                },currHour,currMin,false);
                timePickerDialog.show();

            }
        });


        //////////////////////////////////////////////





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                EditText editText1 = (EditText) findViewById(R.id.editText1);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);

                TextView dateTextViewEdit = (TextView) findViewById(R.id.dateTextViewEdit);
                TextView timeTextViewEdit = (TextView) findViewById(R.id.timeTextViewEdit);

                DBOpenHelper dbOpenHelper = new DBOpenHelper(ScrollingActivity.this);
                SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBOpenHelper.TABLE_COL1, editText1.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL2, editText2.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL3, editText3.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL4, dateTextViewEdit.getText().toString());
                contentValues.put(DBOpenHelper.TABLE_COL5, timeTextViewEdit.getText().toString());


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
