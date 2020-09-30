package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment_update extends AppCompatActivity {

    EditText txtCnumber,txtCname,txtExpdate,txtCvv;
    Button butSave,butShow,butUpdate,butDelete;
    DatabaseReference db;
    Payment1 p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_update);

        txtCnumber = findViewById(R.id.number);
        txtCname = findViewById(R.id.cardholder_name);
        txtExpdate = findViewById(R.id.exp_date);
        txtCvv = findViewById(R.id.cvvupdate);

        butSave = findViewById(R.id.btnSavePayment);
        butShow = findViewById(R.id.btnShowPayment);
        butUpdate = findViewById(R.id.btnUpdateP);
        butDelete = findViewById(R.id.btnDeletePayment);

        p = new Payment1();



        /////////////////////////////

//        db = FirebaseDatabase.getInstance().getReference().child("Payment1").child("P1");
//
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChildren()) {
//                 txtCnumber.setText(dataSnapshot.child("cardnumber").getValue().toString());
//                    txtCname.setText(dataSnapshot.child("cardname").getValue().toString());
//                   txtCvv.setText(dataSnapshot.child("cvv").getValue().toString());
//                   txtExpdate.setText(dataSnapshot.child("expdate").getValue().toString());
//
//                }
//                else
//                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        /////////////////////////

//        butUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Payment1");
//                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        if(dataSnapshot.hasChild("P1")) {
//
//                            try {
//                                p.setCardnumber(Integer.parseInt(txtCnumber.getText().toString().trim()));
//                                p.setCardname(txtCname.getText().toString().trim());
//                                p.setCvv(Integer.parseInt(txtCvv.getText().toString().trim()));
//                                p.setExpdate(Integer.parseInt(txtExpdate.getText().toString().trim()));
//
//                                db = FirebaseDatabase.getInstance().getReference().child("Payment1").child("P1");
//                                db.setValue(p);
////                                clearControls();
//
//                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
//
//                            } catch (NumberFormatException e) {
//                                Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//
//
//        });

        /////////////

//        butDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                db = FirebaseDatabase.getInstance().getReference().child("Payment1").child("P1");
//                db.removeValue();
//                clearControls();
//                Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();
//            }
//
//            private void clearControls() {
//                txtCnumber.setText("");
//                txtCname.setText("");
//                txtCvv.setText("");
//                txtExpdate.setText("");
//
//            }
//        });

        //////////
    }
}