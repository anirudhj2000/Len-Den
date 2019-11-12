package com.example.len_den;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Split extends AppCompatActivity {

    EditText name,cost,number;
    CheckBox but1,but2,but3;
    Button mButtonDone;
    String message;



    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    public static DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        init();
        mDatabaseHelper = new DatabaseHelper(this,"SplitBD.sqlite",null,1);

        mDatabaseHelper.queryData("CREATE TABLE IF NOT EXISTS SPLIT(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, cost VARCHAR, number VARCHAR)");

        mButtonDone = findViewById(R.id.done_button);


        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mDatabaseHelper.insertData(
                            name.getText().toString(),
                            costCalculator(cost),
                            number.getText().toString()
                    );

                    Toast.makeText(getApplicationContext(), "Added Event", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error adding",Toast.LENGTH_LONG).show();
                }

                sendSMSMessage();

                name.setText("");
                cost.setText("");
                number.setText("");



            }
        });

    }


    public void init()
    {
        name = findViewById(R.id.edit_name);
        cost = findViewById(R.id.edit_cost);
        number = findViewById(R.id.edit_number);
        but1 = findViewById(R.id.equal_split);
        but2 = findViewById(R.id.paid_by_you);
        but3 = findViewById(R.id.you_owe);
    }

    public String costCalculator(EditText e)
    {
        String cost = e.getText().toString();
        int initialCost = Integer.parseInt(cost);
        int finalcost=0;

        if(but1.isChecked() && !(but2.isChecked()) &&  !(but3.isChecked()) )
        {
             finalcost = initialCost/2;
             String str = Integer.toString(finalcost);
             generateMessage(1,finalcost);
             return str;
        }
        else if(!(but1.isChecked()) && but2.isChecked()&&  !(but3.isChecked()))
        {
            finalcost = initialCost;
            String str = Integer.toString(finalcost);
            generateMessage(2,finalcost);
            return str;
        }

        else if (!(but1.isChecked()) && but2.isChecked()&&  !(but3.isChecked()))
        {
            finalcost = -initialCost;
            String str = Integer.toString(finalcost);
            generateMessage(3,finalcost);
            return str;
        }

        return null;
    }

    public void generateMessage(int num,int cost)
    {
        if(num == 1)
        {
            message = "Hey"+ name.getText().toString()+ "! Split from your recent expense is Rs."+cost+" ,please pay whenever convenient";
        }

        else if(num==2)
        {
            message = "Hey!" +name.getText().toString()+" Split from your recent expense is Rs."+cost+" ,please pay whenever convenient";
        }
        else if(num==3)
        {
            message = "Hey "+name.getText().toString()+"! I owe you Rs."+cost+" from the recent expense,i will pay as soon as possible";
        }
    }
    public String givemessage()
    {
        return message;
    }

    protected void sendSMSMessage() {

        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , number.getText().toString());
        smsIntent.putExtra("sms_body"  , givemessage());

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Split.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    //@Override
   /* public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number.getText().toString(), null, givemessage(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }*/
}