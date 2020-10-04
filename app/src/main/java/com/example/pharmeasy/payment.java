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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment extends AppCompatActivity {

    EditText txtCnumber,txtCname,txtExpdate,txtCvv;
    Button btnSavePayment,butShow,butUpdate,butDelete;
    DatabaseReference db;
    Payment1 p;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtCnumber = findViewById(R.id.number);
        txtCname = findViewById(R.id.cardholder_name);
        txtExpdate = findViewById(R.id.exp_date);
        txtCvv = findViewById(R.id.cvvupdate);

        btnSavePayment = findViewById(R.id.btnSavePayment);

        p = new Payment1();
        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();






        ///////////////////////////////////show//////////////////////////////

        db = FirebaseDatabase.getInstance().getReference().child("Payment1").child(uid);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtCnumber.setText(dataSnapshot.child("cardnumber").getValue().toString());
                    txtCname.setText(dataSnapshot.child("cardname").getValue().toString());
                    txtCvv.setText(dataSnapshot.child("cvv").getValue().toString());
                    txtExpdate.setText(dataSnapshot.child("expdate").getValue().toString());




                    ///////////////////////update start

                    btnSavePayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Payment1");
                            updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    fAuth= FirebaseAuth.getInstance();
                                    String uid =fAuth.getCurrentUser().getUid();

                                    if(dataSnapshot.hasChild(uid)) {

                                        try {
                                            p.setCardnumber(Long.parseLong(txtCnumber.getText().toString().trim()));
                                            p.setCardname(txtCname.getText().toString().trim());
                                            p.setCvv(Integer.parseInt(txtCvv.getText().toString().trim()));
                                            p.setExpdate(Integer.parseInt(txtExpdate.getText().toString().trim()));

                                            db = FirebaseDatabase.getInstance().getReference().child("Payment1").child(uid);
                                            db.setValue(p);
                                            // clearControls();

                                            //Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                            Intent packageContent = null;
                                            Intent intent11= new Intent(payment.this,final_payment_Activity.class);
                                            startActivity(intent11);

                                        } catch (NumberFormatException e) {
                                            Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }


                    });



                    ///////////update end


                }
                else


                  //  Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
//////insert  start/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                btnSavePayment.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View view) {

                            db = FirebaseDatabase.getInstance().getReference().child("Payment1");

                            try {
                                if (TextUtils.isEmpty(txtCnumber.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please Enter a Card No.", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(txtCname.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please Enter a Card Name", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(txtExpdate.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please Enter an Expiry Date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(txtCvv.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please Enter CVV", Toast.LENGTH_SHORT).show();

                                else {
                                    fAuth= FirebaseAuth.getInstance();
                                    String uid =fAuth.getCurrentUser().getUid();


                                    /////
                                    final String Cardnumber=txtCnumber.getText().toString().trim();
                                    final String Cardname=txtCname.getText().toString().trim();
                                    final String Expdate=txtExpdate.getText().toString().trim();
                                    final String  Cvv=txtCvv.getText().toString().trim();
                                    ////



                                    p.setId(uid);
                                    p.setCardnumber(Long.parseLong(Cardnumber));
                                    p.setCardname(Cardname);
                                    p.setExpdate(Integer.parseInt(Expdate));
                                    p.setCvv(Integer.parseInt(Cvv));





                                    db.child(uid).setValue(p);


                                    Intent packageContent = null;
                                    Intent intent= new Intent(payment.this,final_payment_Activity.class);
                                    startActivity(intent);

                                   // Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                    //clearControls();

                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }

                        private void clearControls() {
                            txtCnumber.setText("");
                            txtCname.setText("");
                            txtExpdate.setText("");
                            txtCvv.setText("");

                        }
                    });

////////////////////////////////////////////////////////////////////////insert end      //////////////////////////////////////////////////////////////////




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /////////////show end /////////////////////////




    }







}