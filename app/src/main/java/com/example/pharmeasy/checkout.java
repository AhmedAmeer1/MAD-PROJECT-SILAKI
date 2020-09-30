package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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