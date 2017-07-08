package com.example.rishavverma.todobasicactivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasicActivity extends AppCompatActivity {

    //private final static long TO_EDIT_PAGE = 101;
    int a=0;
    long UID;
    ListView listView;
    ArrayList<Expense> dbData;
    CustomAdapter customAdapter;
//    public String editWindowName;
//    public String editWindowType;
//    public String editWindowDetails;





    public void removeDataFromDBandList(int i) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(BasicActivity.this);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();

        database.delete(DBOpenHelper.TABLE_NAME, DBOpenHelper.ID + " = " + "\"" + dbData.get(i).id + "\"", null);
    }

    public void addDataToDBandList(EditText addDialogEditText1,EditText addDialogEditText2,EditText addDialogEditText3)
    {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(BasicActivity.this);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBOpenHelper.TABLE_COL1, addDialogEditText1.getText().toString());
        contentValues.put(DBOpenHelper.TABLE_COL2, addDialogEditText2.getText().toString());
        contentValues.put(DBOpenHelper.TABLE_COL3, addDialogEditText3.getText().toString());
        UID = database.insert(DBOpenHelper.TABLE_NAME,null, contentValues);


        Log.d("rTag", UID + "");

    }

    public void showDBdataInList(ArrayList dbData) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(BasicActivity.this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME, null, null, null, null, null, null);
        //String stringID = "";

        dbData.clear();
        while (cursor.moveToNext()) {

            long longID = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.ID));
            String stringNAME = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TABLE_COL1));
            String stringTYPE = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TABLE_COL2));
            String stringDetails = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TABLE_COL3));
            Expense e = new Expense(longID,stringNAME,stringTYPE,stringDetails);

            dbData.add(e);


        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listView = (ListView) findViewById(R.id.listView1);
        dbData = new ArrayList<>();

        customAdapter = new CustomAdapter(this, dbData);
        listView.setAdapter(customAdapter);


        showDBdataInList(dbData);
        customAdapter.notifyDataSetChanged();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BasicActivity.this);
                builder.setTitle("Planning is required for Efficiency");
                builder.setCancelable(false);
                final View v = getLayoutInflater().inflate(R.layout.add_dialog_box,null);
                builder.setView(v);

                final EditText addDialogEditText1 = (EditText) v.findViewById(R.id.addDialogEditText1);
                final EditText addDialogEditText2 = (EditText) v.findViewById(R.id.addDialogEditText2);
                final EditText addDialogEditText3 = (EditText) v.findViewById(R.id.addDialogEditText3);

                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(!(addDialogEditText1.getText().toString().isEmpty() && addDialogEditText2.getText().toString().
                                isEmpty() && addDialogEditText3.getText().toString().isEmpty()))
                        {

                            addDataToDBandList(addDialogEditText1,addDialogEditText2,addDialogEditText3);
                            addDialogEditText1.setText("");
                            addDialogEditText2.setText("");
                            addDialogEditText3.setText("");
                            Log.d("rTag",  "");
                            Log.d("rTag", UID + "");
                            Snackbar.make(view, "Added", Snackbar.LENGTH_SHORT)
                              .setAction("Action", null).show();

                        }
                        else{
                            Snackbar.make(view, "WTF Bruh..!", Snackbar.LENGTH_SHORT)
                             .setAction("Action", null).show();
                        }
                        Log.d("rTag",  "here");
                        showDBdataInList(dbData);
                        customAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


//                Intent intent = new Intent(BasicActivity.this,ScrollingActivity.class);
//                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
            }




        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Expense e = dbData.get(i);

                String editWindowName = e.name;
                String editWindowType = e.type;
                String editWindowDetails = e.details;
                long id = dbData.get(i).id;
                Bundle bundle = new Bundle();
                bundle.putString("editWindowNameKey",editWindowName);
                bundle.putString("editWindowTypeKey",editWindowType);
                bundle.putString("editWindowDetailsKey",editWindowDetails);
                bundle.putLong("ID",id);
                Intent intent = new Intent(BasicActivity.this, ScrollingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);





            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, final View view,final int index, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BasicActivity.this);
                builder.setTitle("DELETE..?");
                builder.setCancelable(false);

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeDataFromDBandList(index);
                        dbData.remove(index);
                        Snackbar.make(view, "Deleted ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        customAdapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Snackbar.make(view, "Not Deleted :/", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(BasicActivity.this,AboutActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
