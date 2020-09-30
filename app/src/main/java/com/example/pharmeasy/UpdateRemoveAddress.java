package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class UpdateRemoveAddress extends AppCompatActivity {

    EditText txtAddress,txtState,txtCity,txtPostal,txtDate;
    Button butSave,butShow,butUpdate,butDelete;
    DatabaseReference db;
    Delivery d;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remove_address);

        txtAddress = findViewById(R.id.address);
        txtState = findViewById(R.id.state);
        txtCity = findViewById(R.id.city);
        txtPostal = findViewById(R.id.postal);
        txtDate = findViewById(R.id.date);

       // butSave = findViewById(R.id.btnSave);
      //  butShow = findViewById(R.id.btnShow);
        butUpdate = findViewById(R.id.btnSave);
        butDelete = findViewById(R.id.btnDelete);

        d = new Delivery();
        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();


        /////////////////////////////
        db = FirebaseDatabase.getInstance().getReference().child("Delivery").child(uid);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                    txtState.setText(dataSnapshot.child("state").getValue().toString());
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


        /////////////////////////
        butUpdate.setOnClickListener(new View.OnClickListener() {
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

                        System.out.println("BEFORE : "+uid);


                        if(dataSnapshot.hasChild(uid)) {

                            System.out.println("INSIDE");
                            try {
                                d.setId(fAuth.getCurrentUser().getUid());
                                d.setAddress(txtAddress.getText().toString().trim());
                                d.setState(txtState.getText().toString().trim());
                                d.setCity(txtCity.getText().toString().trim());
                                d.setPostalcode(Integer.parseInt(txtPostal.getText().toString().trim()));
                                d.setDeliverydate(txtDate.getText().toString().trim());

                                System.out.println("AFTER");
                                db = FirebaseDatabase.getInstance().getReference().child("Delivery").child(uid);
                                db.setValue(d);
                               clearControls();



                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

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
                                    txtState.setText(dataSnapshot.child("state").getValue().toString());
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
        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Delivery").child("D1");
                db.removeValue();

                Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();
            }


        });
    }}