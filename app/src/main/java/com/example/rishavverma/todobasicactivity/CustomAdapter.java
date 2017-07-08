package com.example.rishavverma.todobasicactivity;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RishavVerma on 7/7/2017.
 */

public class CustomAdapter extends ArrayAdapter<Expense> {

    ArrayList<Expense> expenseArrayList;
    Context context;


    public CustomAdapter(@NonNull Context context,ArrayList<Expense> expenseArrayList) {
        super(context,0);
        this.context=context;
        this.expenseArrayList = expenseArrayList;
    }

    @Override
    public int getCount() {
        return expenseArrayList.size();
    }

    static class ExpenseViewHolder
    {
        TextView name;
        TextView price;
        TextView type;
        //long id;
        ExpenseViewHolder(TextView name, TextView type, TextView price)
        {
            this.name = name;
            this.type = type;
            this.price = price;
            //this.id = id;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_list,null);
            TextView customName = (TextView) convertView.findViewById(R.id.customName);
            TextView customType = (TextView) convertView.findViewById(R.id.customType);
            TextView customDetails = (TextView) convertView.findViewById(R.id.customDetails);

            ExpenseViewHolder expenseViewHolder = new ExpenseViewHolder(customName,customType,customDetails);
            convertView.setTag(expenseViewHolder);
        }
        Expense e = expenseArrayList.get(position);
        ExpenseViewHolder expenseViewHolder = (ExpenseViewHolder)convertView.getTag();
        expenseViewHolder.name.setText(e.name);
        expenseViewHolder.type.setText(e.type);
        expenseViewHolder.price.setText(e.details);

        return convertView;
    }
}
