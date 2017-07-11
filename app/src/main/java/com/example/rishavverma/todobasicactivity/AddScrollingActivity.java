package com.example.rishavverma.todobasicactivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Date;

public class AddScrollingActivity extends AppCompatActivity {



//    EditText addDialogEditText1;
//    Spinner spinner;
//    EditText addDialogEditText2;
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

    public long UID;

//    public void addDataToDBandList(EditText addDialogEditText1, EditText addDialogEditText2, EditText addDialogEditText3,TextView dateTextView, TextView timeTextView)
//    {
//        DBOpenHelper dbOpenHelper = new DBOpenHelper(AddScrollingActivity.this);
//        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBOpenHelper.TABLE_COL1, addDialogEditText1.getText().toString());
//        contentValues.put(DBOpenHelper.TABLE_COL2, addDialogEditText2.getText().toString());
//        contentValues.put(DBOpenHelper.TABLE_COL3, addDialogEditText3.getText().toString());
//        contentValues.put(DBOpenHelper.TABLE_COL4, dateTextView.getText().toString());
//        contentValues.put(DBOpenHelper.TABLE_COL5, timeTextView.getText().toString());
//        UID = database.insert(DBOpenHelper.TABLE_NAME,null, contentValues);
//
//        Log.d("rTag", UID + "");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Bundle getIntent = getIntent().getExtras();
//        final long recievedDBid = getIntent.getLong("ID");

//        addDialogEditText1 = (EditText) findViewById(R.id.addDialogEditText1);
//        addDialogEditText2 = (EditText) findViewById(R.id.addDialogEditText2);

        dateButton = (ImageButton) findViewById(R.id.dateButton);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeButton = (ImageButton) findViewById(R.id.timeButton);
        timeTextView = (TextView) findViewById(R.id.timeTextView);

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddScrollingActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddScrollingActivity.this, new TimePickerDialog.OnTimeSetListener() {
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


        final EditText addDialogEditText1 = (EditText) findViewById(R.id.addDialogEditText1);
        final EditText addDialogEditText2 = (EditText) findViewById(R.id.addDialogEditText2);
        final EditText addDialogEditText3 = (EditText) findViewById(R.id.addDialogEditText3);
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        final TextView timeTextView = (TextView) findViewById(R.id.timeTextView);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!(addDialogEditText1.getText().toString().isEmpty() && addDialogEditText2.getText().toString().isEmpty() && addDialogEditText3.getText().toString().isEmpty()))
                {
                    DBOpenHelper dbOpenHelper = new DBOpenHelper(AddScrollingActivity.this);
                    Log.d("rTag", "a");
                    SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
                    Log.d("rTag", "b");
                    ContentValues contentValues = new ContentValues();
                    Log.d("rTag", "c");
                    contentValues.put(DBOpenHelper.TABLE_COL1, addDialogEditText1.getText().toString());
                    contentValues.put(DBOpenHelper.TABLE_COL2, addDialogEditText2.getText().toString());
                    contentValues.put(DBOpenHelper.TABLE_COL3, addDialogEditText3.getText().toString());
                    Log.d("rTag", "g");
                    contentValues.put(DBOpenHelper.TABLE_COL4, dateTextView.getText().toString());
                    Log.d("rTag", "h");
                    contentValues.put(DBOpenHelper.TABLE_COL5, timeTextView.getText().toString());
                    Log.d("rTag", "i");

                    UID = database.insert(DBOpenHelper.TABLE_NAME, null, contentValues);

                    Log.d("rTag", UID + "");
                    Snackbar.make(view, "Added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                    // addDataToDBandList(addDialogEditText1,addDialogEditText2,addDialogEditText3,dateTextView,timeTextView);
//                long uid = UID;
//                Bundle bundle = new Bundle();
//                bundle.putLong("UID",uid);
                    Intent intent = new Intent(AddScrollingActivity.this, BasicActivity.class);
                    // intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Snackbar.make(view, "WTF Bruh..!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }







            }
        });
    }
}
