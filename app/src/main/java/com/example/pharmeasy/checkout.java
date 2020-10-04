package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class checkout extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;
    Spinner spinner;
    DatabaseReference db;
    String item;
    Member member;

    String[] province = {"Choose province","Western ","Northern ","Central ","Eastern","Colombo","Northern","North Western","Sabaragamuwa","Uva"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

         spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        btn = findViewById(R.id.btnproceed);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db = database.getReference("Province");
        spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        member = new Member();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,province);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveValue(item);
            }



        });

    }

    public void ActivityTwo(View v){
        Intent i = new Intent(this, Address.class);
        startActivity(i);
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

        item = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void SaveValue(String item){
        if (item =="Choose province"){
            Toast.makeText(this,"Please select Province",Toast.LENGTH_SHORT).show();
        }
        else{
            member.setProvince(item);
            String id = db.push().getKey();
            db.child(id).setValue(member);
            Toast.makeText(this,"Value Saved",Toast.LENGTH_SHORT).show();


                Intent i11 = new Intent(checkout.this, payment.class);
                startActivity(i11);
           

        }
    }

}