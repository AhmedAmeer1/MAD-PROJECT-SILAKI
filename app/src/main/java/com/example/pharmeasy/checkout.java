package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class checkout extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;
    TextView checkoutprice;
    Spinner spinner;
    DatabaseReference db;
    String item;
    Member member;
    FirebaseAuth fAuth;

    String[] province = {"Choose province","Western","Northern","Central","Eastern","Colombo","Northern","North Western","Sabaragamuwa","Uva"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        btn = findViewById(R.id.btnproceed);
        checkoutprice = findViewById(R.id.checkoutprice);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db = database.getReference("Province");
        spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        member = new Member();

//
        //////retrieve total amounts

        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();

        db = FirebaseDatabase.getInstance().getReference().child("Total_cost").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    int retreived_amount = Integer.parseInt(dataSnapshot.child("total_cost").getValue().toString());

                    checkoutprice.setText(""+retreived_amount);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////




        ///////


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



    //inserting  province
    void SaveValue(String item){
        if (item =="Choose province"){
            Toast.makeText(this,"Please select Province",Toast.LENGTH_SHORT).show();
        }
        else{
            fAuth= FirebaseAuth.getInstance();
            String uid =fAuth.getCurrentUser().getUid();

            member.setProvince(item);
            db.child(uid).setValue(member);


            Toast.makeText(this,"Value Saved",Toast.LENGTH_SHORT).show();


            Intent i11 = new Intent(checkout.this,Address.class);
            startActivity(i11);


        }
    }

}