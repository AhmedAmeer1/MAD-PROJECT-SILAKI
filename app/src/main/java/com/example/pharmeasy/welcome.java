package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button signup =(Button)findViewById(R.id.buttonup);
        Button signin =(Button)findViewById(R.id.button2in);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent packageContent = null;
                Intent intent= new Intent(welcome.this,Signup.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent packageContent = null;
                Intent intent1= new Intent(welcome.this,SignIn.class);
                startActivity(intent1);
            }
        });










    }
}