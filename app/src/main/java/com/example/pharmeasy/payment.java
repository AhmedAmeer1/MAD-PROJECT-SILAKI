package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class payment extends AppCompatActivity {

    EditText txtCnumber,txtCname,txtExpdate,txtCvv;
    Button butSave,butShow,butUpdate,butDelete;
    DatabaseReference db;
    Payment1 p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtCnumber = findViewById(R.id.cnumber);
        txtCname = findViewById(R.id.cname);
        txtExpdate = findViewById(R.id.expdate);
        txtCvv = findViewById(R.id.cvvinsert);

        butSave = findViewById(R.id.btnSavePayment);

        p = new Payment1();

//        butSave.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//                db = FirebaseDatabase.getInstance().getReference().child("Payment1");
//
//                try {
//                    if (TextUtils.isEmpty(txtCnumber.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Please Enter a Card No.", Toast.LENGTH_SHORT).show();
//                    else if (TextUtils.isEmpty(txtCname.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Please Enter a Card Name", Toast.LENGTH_SHORT).show();
//                    else if (TextUtils.isEmpty(txtExpdate.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Please Enter an Expiry Date", Toast.LENGTH_SHORT).show();
//                    else if (TextUtils.isEmpty(txtCvv.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Please Enter CVV", Toast.LENGTH_SHORT).show();
//
//                    else {
//                        p.setCardnumber(Integer.parseInt(txtCnumber.getText().toString().trim()));
//                        p.setCardname(txtCname.getText().toString().trim());
//                        p.setExpdate(Integer.parseInt(txtExpdate.getText().toString().trim()));
//                        p.setCvv(Integer.parseInt(txtCvv.getText().toString().trim()));
//
//
//                        db.push().setValue(p);
//                        db.child("P1").setValue(p);
//
//
//                Intent packageContent = null;
//                Intent intent= new Intent(payment.this,OrderConfirmation.class);
//                startActivity(intent);
//
//                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
//                        clearControls();
//
//                    }
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            private void clearControls() {
//                txtCnumber.setText("");
//                txtCname.setText("");
//                txtExpdate.setText("");
//                txtCvv.setText("");
//
//            }
//        });

    }







}