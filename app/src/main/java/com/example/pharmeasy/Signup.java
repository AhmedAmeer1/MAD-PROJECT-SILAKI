package com.example.pharmeasy;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText address,email,phone,password;
    FirebaseAuth fAuth;
    Button regBtn;
    Register  register;
    ProgressBar progressBar;

    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        address=findViewById(R.id.address);
        email=findViewById(R.id.t2);
        phone=findViewById(R.id.number);
        password=findViewById(R.id.logPassword);

        progressBar=findViewById(R.id.progressBar);




        register=new Register ();

        regBtn=findViewById(R.id.buttonup);
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
           // startActivity(new Intent(getApplicationContext(),MainActivity.class));

            Intent i=new Intent(this, MainActivity.class);
        }

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Address=address.getText().toString().trim();
                final String Email=email.getText().toString().trim();
                final String Phone=phone.getText().toString().trim();
                final String  Password=password.getText().toString().trim();



               if(TextUtils.isEmpty(Email)){
                    email.setError("Please enter email!!");
                   return;
               }

               if(TextUtils.isEmpty(Phone)){
                   phone.setError("Please enter phone!!");
                    return;
                }
                if(TextUtils.isEmpty(Password)) {
                    password.setError("Please enter password!!");
                   return;
               }
                if(TextUtils.isEmpty(Address)){
                    address.setError("Please enter address!!");
                    return;
               }

                if(Phone.length()!=10){
                    phone.setError("phone number must have 10 digits");
                    return;
                }



                if(Password.length()<6){
                    password.setError("Password must have length at least 6");
                    return;
                }


                progressBar.setVisibility(view.VISIBLE);
                dbRef= FirebaseDatabase.getInstance().getReference().child("Register");
                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            register.setId(fAuth.getCurrentUser().getUid());
                            register.setEmail(Email);
                            register.setAddress(Address);
                            register.setContact_number(Integer.parseInt(Phone));
                            register.setPassword(Password);

                            dbRef.child(String.valueOf(register.getId())).setValue(register);


                            Toast.makeText(getApplicationContext(),"registration successful",Toast.LENGTH_SHORT).show();


                            ////////////add dialog box


                            AlertDialog.Builder builder =new AlertDialog.Builder(Signup.this);

                            builder.setCancelable(true);

                            builder.setTitle("Welcome");
                            builder.setMessage("You have Successfully Registered");
///////////intent

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent packageContent = null;
                                    Intent intent1= new Intent(Signup.this,SignIn.class);
                                    startActivity(intent1);
                                }
                            });

                            builder.show();

////////////////////////////////////////////
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"registration unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}