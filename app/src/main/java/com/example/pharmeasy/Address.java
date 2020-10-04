package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    Button address_confirm;
    DatabaseReference db;
    Delivery d;
    FirebaseAuth fAuth;

    int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        txtAddress = findViewById(R.id.address);

        txtCity = findViewById(R.id.city);
        txtPostal = findViewById(R.id.postal);
        txtDate = findViewById(R.id.date);
        final Calendar calender = Calendar.getInstance();

        address_confirm = findViewById(R.id.address_confirm);




        d = new Delivery();
        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calender = Calendar.getInstance();
                mDay = calender.get(calender.YEAR);
                mMonth = calender.get(calender.MONTH);
                mYear = calender.get(calender.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Address.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i+"/"+i1+"/"+i2);
                    }
                },mDay,mMonth,mYear);
                datePickerDialog.show();
            }
        });











        ///////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Delivery").child(uid);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                    // txtState.setText(dataSnapshot.child("state").getValue().toString());
                    txtCity.setText(dataSnapshot.child("city").getValue().toString());
                    txtPostal.setText(dataSnapshot.child("postalcode").getValue().toString());
                    txtDate.setText(dataSnapshot.child("deliverydate").getValue().toString());








                    ////////////update  start////////////////
                    address_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fAuth= FirebaseAuth.getInstance();
                            String uid =fAuth.getCurrentUser().getUid();


                            DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Delivery");
                            updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    fAuth= FirebaseAuth.getInstance();
                                    String uid =fAuth.getCurrentUser().getUid();




                                    if(dataSnapshot.hasChild(uid)) {


                                        try {
                                            d.setId(fAuth.getCurrentUser().getUid());
                                            d.setAddress(txtAddress.getText().toString().trim());
                                            //d.setState(txtState.getText().toString().trim());
                                            d.setCity(txtCity.getText().toString().trim());
                                            d.setPostalcode(Integer.parseInt(txtPostal.getText().toString().trim()));
                                            d.setDeliverydate(txtDate.getText().toString().trim());


                                            db = FirebaseDatabase.getInstance().getReference().child("Delivery").child(uid);
                                            db.setValue(d);


                                            clearControls();

                                            Intent packageContent = null;
                                            Intent intent15= new Intent(Address.this,payment.class);
                                            startActivity(intent15);





                                        } catch (NumberFormatException e) {

                                        }

                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

                                }

                                private void clearControls() {
                                    fAuth= FirebaseAuth.getInstance();
                                    String uid =fAuth.getCurrentUser().getUid();

                                    db = FirebaseDatabase.getInstance().getReference().child("Delivery").child(uid);
                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChildren()) {
                                                txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                                                //txtState.setText(dataSnapshot.child("state").getValue().toString());
                                                txtCity.setText(dataSnapshot.child("city").getValue().toString());
                                                txtPostal.setText(dataSnapshot.child("postalcode").getValue().toString());
                                                txtDate.setText(dataSnapshot.child("deliverydate").getValue().toString());
                                            }
                                            else
                                                Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }


                    });
                    ///////////////////update end /////////////





                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();



                /////////////////insert start/////////////////////////***********************
                address_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db = FirebaseDatabase.getInstance().getReference().child("Delivery");

                        try {
                            if (TextUtils.isEmpty(txtAddress.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter an Address", Toast.LENGTH_SHORT).show();

                            else if (TextUtils.isEmpty(txtCity.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter a City", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtPostal.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter Postal Code", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtDate.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter Expected Delivery Date", Toast.LENGTH_SHORT).show();
                            else {
                                fAuth=FirebaseAuth.getInstance();
                                String uid =fAuth.getCurrentUser().getUid();

                                d.setId(uid);
                                d.setAddress(txtAddress.getText().toString().trim());
                                //d.setState(txtState.getText().toString().trim());
                                d.setCity(txtCity.getText().toString().trim());
                                d.setPostalcode(Integer.parseInt(txtPostal.getText().toString().trim()));
                                d.setDeliverydate(txtDate.getText().toString().trim());




                                db.child(uid).setValue(d);

                                Intent i1 = new Intent(Address.this,payment.class);
                                startActivity(i1);

                                //Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();


                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                    }

                    private void clearControls() {
                        txtAddress.setText("");
                        ///txtState.setText("");
                        txtCity.setText("");
                        txtPostal.setText("");
                        txtDate.setText("");
                    }
                });

                //////////////////////insert end //////////////////***********************************




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /////////////////////////  show  end///////////////////////







    }


}