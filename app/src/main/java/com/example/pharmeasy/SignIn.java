package com.example.pharmeasy;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText  email,password;
    Button  logBtn,createbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email=findViewById(R.id.logMail);
        password=findViewById(R.id.password);
        fAuth=FirebaseAuth.getInstance();

        logBtn=findViewById(R.id.login);
        createbtn=findViewById(R.id.createbtn);
        progressBar=findViewById(R.id.progressBar);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12= new Intent(SignIn.this,Signup.class);
                startActivity(intent12);
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String Email=email.getText().toString().trim();
                String  Password=password.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Please enter the email..");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Please enter the email correctly..");
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Please enter the password");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);
                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"login Unsuccessful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.INVISIBLE);


                            AlertDialog.Builder builder =new AlertDialog.Builder(SignIn.this);

                            builder.setCancelable(true);

                            builder.setTitle("ERROR");
                            builder.setMessage("EMAIL OR PASSWORD IS INCORRECT");
///////////intent

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent packageContent = null;
                                    Intent intent1= new Intent(SignIn.this,SignIn.class);
                                    startActivity(intent1);
                                }
                            });

                            builder.show();

                        }
                    }
                });
            }
        });

    }

}