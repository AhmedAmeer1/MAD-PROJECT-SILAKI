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

import java.util.Calendar;


public class Address extends AppCompatActivity {

    public void gotopayment(View v){
        Intent packageContent = null;
        Intent i = new Intent(this,payment.class);
        startActivity(i);
    }

    public void backtocheckout(View v){
        Intent packageContent = null;
        Intent i1 = new Intent(this,checkout.class);
        startActivity(i1);
    }

    EditText txtAddress,txtState,txtCity,txtPostal,txtDate;
    Button butSave,butShow,butUpdate,butDelete;
    DatabaseReference db;
    Delivery d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        txtAddress = findViewById(R.id.address);
        txtState = findViewById(R.id.state);
        txtCity = findViewById(R.id.city);
        txtPostal = findViewById(R.id.postal);
        txtDate = findViewById(R.id.date);
        final Calendar calender = Calendar.getInstance();

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Calendar calender = Calendar.getInstance();
                
            }
        });

        butSave = findViewById(R.id.btnSave);

        butUpdate = findViewById(R.id.btnUpdate);
        butDelete = findViewById(R.id.btnDelete);

        d = new Delivery();

        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Delivery");

                try {
                    if (TextUtils.isEmpty(txtAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter an Address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtState.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a State", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtCity.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a City", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtPostal.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Postal Code", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Expected Delivery Date", Toast.LENGTH_SHORT).show();
                    else {

                        d.setAddress(txtAddress.getText().toString().trim());
                        d.setState(txtState.getText().toString().trim());
                        d.setCity(txtCity.getText().toString().trim());
                        d.setPostalcode(Integer.parseInt(txtPostal.getText().toString().trim()));
                        d.setDeliverydate(Integer.parseInt(txtDate.getText().toString().trim()));

                        db.push().setValue(d);
                        db.child("D1").setValue(d);

                        Intent i1 = new Intent(Address.this,checkout.class);
                        startActivity(i1);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }
            } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

        }

            private void clearControls() {
                txtAddress.setText("");
                txtState.setText("");
                txtCity.setText("");
                txtPostal.setText("");
                txtDate.setText("");
            }
            });

    }


}