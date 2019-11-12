package com.example.len_den;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class expenses extends AppCompatActivity {

    CardView add,delete;
    TextView totalincome,totalexpense;
    static int totalincome1;
    static int totalexpense1;
    int total,temp1,temp2;
    float val1,val2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        PieChart pieChart = findViewById(R.id.pie_chart);
        pieChart.setUsePercentValues(true);

        add    = (CardView) findViewById(R.id.add_income);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(expenses.this,expense2.class);
                startActivity(i);
            }
        });

        int inc1 = getIntent().getIntExtra("income",0);
        int exp1 = getIntent().getIntExtra("expense",0);

        totalincome1 = totalincome1 + inc1;
        temp1  = totalincome1;
        totalexpense1 = totalexpense1 + exp1;
        temp2 = totalexpense1;

        totalincome = findViewById(R.id.total_income);
        totalexpense = findViewById(R.id.total_expense);

        totalincome.setText(""+totalincome1);
        totalexpense.setText(""+totalexpense1);

        totalincome1 = temp1;
        totalexpense1= temp2;


        total = totalexpense1+totalincome1;
        val1 = (float) (totalincome1*100)/total;
        val2 = (float) (totalexpense1*100)/total;

        List<PieEntry> values = new ArrayList<>();
        values.add(new PieEntry( val1,"income"));
        values.add(new PieEntry( val2,"expense"));
        PieDataSet pieDataSet = new PieDataSet(values,"Total");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(expenses.this,dashboard_Activity.class);
        startActivity(i);
    }
}
