package com.example.rishavverma.todobasicactivity;

/**
 * Created by RishavVerma on 7/7/2017.
 */

public class Expense {

    public String name;
    public String type;
    public String details;
    public String date;
    public String time;

    public long id;

    public Expense(long UID, String name, String type, String details, String date, String time) {
        this.name = name;
        this.type = type;
        this.details = details;
        this.id = UID;
        this.date=date;
        this.time=time;
    }
}
