package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class final_payment_Activity extends AppCompatActivity {
TextView famt,discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment_);

/////////////patter checking
        Pattern p = Pattern.compile("1234");

        discount=findViewById(R.id.discount);


        String  num1="1234451234567";
        String  num2="123435557";

        String first4char = num1.substring(0,4);
        System.out.println(first4char);

        int intForFirst4Char = Integer.parseInt(first4char);
        int ptoint =  Integer.parseInt(p.toString());


        if(ptoint == intForFirst4Char){


            discount.setText(""+111111);

        }else {
            discount.setText(""+00000);

        }


//////////////////


famt=findViewById(R.id.famt);

String finalamount=getIntent().getStringExtra("One");

famt.setText(finalamount);






        Button final_payment =(Button)findViewById(R.id.btnSavePayment);


        final_payment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent packageContent = null;
                Intent intent= new Intent(final_payment_Activity.this,OrderConfirmation.class);
                startActivity(intent);
            }
        });








    }
}