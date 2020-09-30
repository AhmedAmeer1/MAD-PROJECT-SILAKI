package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button btn2 =(Button)findViewById(R.id.butUpdate);


        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent packageContent = null;
                Intent intent= new Intent(payment.this,OrderConfirmation.class);
                startActivity(intent);
            }
        });

    }

//    public void ActivityThree(View view){
//        Intent i1 = new Intent(this,OrderConfirmation.class);
//        startActivity(i1);
//    }





}