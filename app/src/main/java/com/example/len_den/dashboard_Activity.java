package com.example.len_den;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard_Activity extends AppCompatActivity {

    CardView mCard1,mCard2,mCard3,mCard4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);

        mCard1 = findViewById(R.id.card_1);
        mCard2  =findViewById(R.id.card_2);
        mCard3 = findViewById(R.id.card_3);
        mCard4 = findViewById(R.id.card_4);

        mCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard_Activity.this,Split.class);
                startActivity(i);
            }
        });

        mCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard_Activity.this,expenses.class);
                startActivity(i);
            }
        });

        mCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard_Activity.this,History.class);
                startActivity(i);
            }
        });

        mCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard_Activity.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }
}
