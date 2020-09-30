package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class checkout extends AppCompatActivity {

    TextView totamt;
    int num=3400;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        totamt=findViewById(R.id.famt);

        totamt.setText(""+78655);

        Intent i=new Intent(this, final_payment_Activity.class);
        i.putExtra("One","34445");





    }






    public void ActivityTwo(View v){
        Intent i = new Intent(this, Address.class);
        startActivity(i);
    }

    public void ActivityThree(View view){
        Intent i1 = new Intent(this,payment.class);
        startActivity(i1);
    }








}