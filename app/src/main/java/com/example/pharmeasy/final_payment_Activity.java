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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class final_payment_Activity extends AppCompatActivity {
TextView famt,discount,subtotal;
    Button final_payment1;
int loyality_points=0;
Loyality_points  cus_points=new Loyality_points();

FirebaseAuth fAuth;
DatabaseReference db;
String number_pattern;
float retreived_amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment_);


        famt=findViewById(R.id.famt);
        subtotal=findViewById(R.id.subtotal);
        discount=findViewById(R.id.discount);

//////////////retreiving  total amounts

        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();
        ///////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Total_cost").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                   retreived_amount=Integer.parseInt(dataSnapshot.child("total_cost").getValue().toString());

                    subtotal.setText(""+retreived_amount);


                          }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////





        //////////////////******************retreiving  total amounts close*******************************//////////////




//////////////////calculating card discounts //////////////////******************************************************************************************************************
        ///////////////////////////// show   satrt //////////
//        db = FirebaseDatabase.getInstance().getReference().child("Payment1").child(uid);
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChildren()) {
//                    number_pattern=(dataSnapshot.child("cardnumber").getValue().toString());
//                    System.out.println("hiiiiii"+number_pattern);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        }); ///////////////////////////// show   end //////////
//
//
//
//
//
//        Pattern p = Pattern.compile("1234");
//
//        String first4char = number_pattern.substring(0,4);
//        System.out.println(first4char);
//
//        int intForFirst4Char = Integer.parseInt(first4char);
//        int ptoint =  Integer.parseInt(p.toString());
//
//        float dis=0;
//        if(ptoint == intForFirst4Char){
//            dis=retreived_amount*10/100;
//
//            discount.setText(""+dis);
//
//        }else {
//            discount.setText(""+dis);
//
//        }




//////////////////calculating card discounts ends //////////////////




////////*************************************///////////////////////////////////////////////////////////////////////




///////////////////////////////calculating loyality discounts start/////////////////////////////////////////////////

        /////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Loyality_points").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                   int  retreived_loyality_points=Integer.parseInt(dataSnapshot.child("loyality_points").getValue().toString());




                   float loyality_amount=0;
                    if(retreived_loyality_points>=5){
                        loyality_amount=  retreived_amount*10/100;
                        discount.setText(""+loyality_amount);
                        loyality_points=0;

                    }else{
                        discount.setText(""+loyality_amount);
                    }



                }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////



///////////////////////////////calculating loyality discounts end /////////////////////////////////////////////////














        final_payment1 =findViewById(R.id.final_payment1);



    //****************************/////////calculating loyality points***********************************


        ///////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Loyality_points").child(uid);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                 loyality_points=Integer.parseInt(dataSnapshot.child("loyality_points").getValue().toString());



                    ////////////update  start////////////////
                    final_payment1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fAuth= FirebaseAuth.getInstance();
                            String uid =fAuth.getCurrentUser().getUid();


                            DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Loyality_points");
                            updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    fAuth= FirebaseAuth.getInstance();
                                    String uid =fAuth.getCurrentUser().getUid();




                                    if(dataSnapshot.hasChild(uid)) {


                                        try {
                                            int total_points=loyality_points+1;
                                            cus_points.setLoyality_points(total_points);

                                            db = FirebaseDatabase.getInstance().getReference().child("Loyality_points").child(uid);
                                            db.setValue(cus_points);
                                            Intent packageContent = null;
                                            Intent intent= new Intent(final_payment_Activity.this,OrderConfirmation.class);
                                            startActivity(intent);


                                        } catch (NumberFormatException e) {

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


                    });/////update  bracket
                    ///////////////////update end /////////////





                }////big ifff
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

               /////////////////insert starts/////////////////////////***********************
        final_payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Loyality_points");

                try {

                    int total_points=1;

                        fAuth=FirebaseAuth.getInstance();
                        String uid =fAuth.getCurrentUser().getUid();

                    cus_points.setId(uid);
                    cus_points.setLoyality_points(total_points);

                     db.child(uid).setValue(cus_points);





                    Intent packageContent = null;
                    Intent intent= new Intent(final_payment_Activity.this,OrderConfirmation.class);
                    startActivity(intent);

                    }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

        });

        //////////////////////insert end //////////////////***********************************



                }//////big else close



            /////////////////////////  show  end///////////////////////


}/////DataSnapshot

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

    });//ValueEventListener bracket
///////////////////******************calculating loyality points  ends****************** //////////












    }///on create bracket


    }////final  bracket