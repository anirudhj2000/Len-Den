package com.example.len_den;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    GridView gridView;
    ArrayList<SplitManager> list;
    Splitlistadapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new Splitlistadapter(this,R.layout.list_item,list);
        gridView.setAdapter(adapter);

        Cursor cursor = Split.mDatabaseHelper.getData("SELECT * FROM SPLIT");
        list.clear();
        while(cursor.moveToNext())
        {
            int id  = cursor.getInt(0);
            String name = cursor.getString(1);
            String cost = cursor.getString(2);
            String price= cursor.getString(3);

            list.add(new SplitManager(id,name,cost,price));
        }
        adapter.notifyDataSetChanged();
    }
}
