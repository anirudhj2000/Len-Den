package com.example.len_den;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class expense2 extends AppCompatActivity {

    EditText amount,title;
    CheckBox income,expense;
    Button done;

    public static ExpenseDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense2);

        title = (EditText) findViewById(R.id.edit_title_1);
        amount = (EditText) findViewById(R.id.add_amount);
        done = findViewById(R.id.done1_button);
        income = (CheckBox) findViewById(R.id.check_income);
        expense = (CheckBox) findViewById(R.id.check_expense);

        mDatabaseHelper = new ExpenseDatabaseHelper(this,"ExpenseDB.sqlite",null,1);

        mDatabaseHelper.queryData("CREATE TABLE IF NOT EXISTS EXPENSE(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, cost VARCHAR, number VARCHAR)");


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mDatabaseHelper.insertData(
                            title.getText().toString(),
                            amount.getText().toString(),
                            getExpensetext()
                    );

                    Intent i = new Intent(expense2.this,expenses.class);
                    if(income.isChecked() && !(expense.isChecked()))
                    {
                        i.putExtra("income",Integer.parseInt(amount.getText().toString()));
                    }

                    else if(!(income.isChecked()) && expense.isChecked())
                    {
                        i.putExtra("expense",Integer.parseInt(amount.getText().toString()));
                    }
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Added Event", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error adding",Toast.LENGTH_LONG).show();
                }

                title.setText("");
                amount.setText("");




            }
        });

    }

    public String getExpensetext()
    {
        if(income.isChecked() && !(expense.isChecked()))
        {
            String s = "income";
            return s;
        }

        else if(!(income.isChecked()) && expense.isChecked())
        {
            String s = "expense";
            return s;
        }
        else return null;
    }
}
