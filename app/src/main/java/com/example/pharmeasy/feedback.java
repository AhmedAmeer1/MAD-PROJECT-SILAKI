package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    MultiAutoCompleteTextView  description;
    Button send_feedback11;
    DatabaseReference db;
    FirebaseAuth fAuth;
    model_feedback fed= new model_feedback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        description=findViewById(R.id.description);
        send_feedback11=findViewById(R.id.feedback12);




        /////////////////insert start/////////////////////////***********************
        send_feedback11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Feedback");


                if (TextUtils.isEmpty(description.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter an FEEDBACK", Toast.LENGTH_SHORT).show();

                fAuth= FirebaseAuth.getInstance();
                String uid =fAuth.getCurrentUser().getUid();

                fed.setId(uid);
                fed.setDescription(description.getText().toString().trim());

                db.push().setValue(fed);
                clearControls();



                Toast.makeText(getApplicationContext(), "Data  Successfully send", Toast.LENGTH_SHORT).show();


            }


        });






        //////////////////////insert end //////////////////***********************************












    }
    private void clearControls() {
        description.setText("");
    }

}