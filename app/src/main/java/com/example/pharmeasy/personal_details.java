package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class personal_details extends AppCompatActivity {

    TextView password,email,number;
    EditText address;
    Button butSave, butShow, butUpdate, butDelete;
    DatabaseReference dbRef;
    Register register = new Register();
    FirebaseAuth fAuth;
    Register reg11=new Register();
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);




        email=findViewById(R.id.t2);
        password=findViewById(R.id.logPassword);
        address=findViewById(R.id.address);
        number=findViewById(R.id.number);

        butUpdate = findViewById(R.id.btnSavePayment);
        butDelete = findViewById(R.id.feedback12);

        fAuth=FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();


        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                   number.setText(dataSnapshot.child("contact_number").getValue().toString());



                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        //////////////////////////////////


//////////////////update


        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Register");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fAuth=FirebaseAuth.getInstance();
                        String uid =fAuth.getCurrentUser().getUid();

                        if(dataSnapshot.hasChild(uid)) {

                            try {
                                register.setId(fAuth.getCurrentUser().getUid());
                                register.setEmail(email.getText().toString().trim());
                                register.setPassword(password.getText().toString().trim());
                                register.setAddress(address.getText().toString().trim());
                                register.setContact_number(Integer.parseInt(number.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
                                dbRef.setValue(register);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

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

//////////////DELETE

        butDelete.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
              fAuth=FirebaseAuth.getInstance();
              String uid =fAuth.getCurrentUser().getUid();

                dbRef = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();


              Intent packageContent = null;
              Intent intent1= new Intent(personal_details.this,SignIn.class);
              startActivity(intent1);

            }
       });




    }


    private void clearControls() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Register").child("reg1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                    number.setText(dataSnapshot.child("contact_number").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    ///////////////

    private String getId() {
        return (fAuth.getCurrentUser().getUid());
    }



}