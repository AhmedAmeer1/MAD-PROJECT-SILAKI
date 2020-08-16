package com.example.pharmeasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signup =(Button)findViewById(R.id.buttonup);

        signup.setOnClickListener(new View.OnClickListener() {


            @Override

            /////
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(Signup.this);

                builder.setCancelable(true);

                builder.setTitle("Welcome");
                builder.setMessage("You have Successfully Registered");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent packageContent = null;
                        Intent intent1= new Intent(Signup.this,SignIn.class);
                        startActivity(intent1);
                    }
                });

                builder.show();

////////////////////////////////////////////



            }
        });


    }
}