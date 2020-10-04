package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class final_payment_Activity extends AppCompatActivity {
    TextView famt,carddiscount,subtotal,delivery_chg,tloyalty,final_amount11;
    Button final_payment1btn;
    int loyality_points=0;
    Loyality_points  cus_points=new Loyality_points();

    float delivery_charge=0;
    float loyality_amount=0;
    int retreived_amount=0;
    float Cdiscount=0;
    float display_total=0;

    FirebaseAuth fAuth;
    DatabaseReference db;
    String  number_pattern;
    //long number_pattern;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment_);


        famt=findViewById(R.id.final_amount11);
        subtotal=findViewById(R.id.tprice);
        carddiscount=findViewById(R.id.checkoutprice);
        delivery_chg=findViewById(R.id.delivery_chg);
        tloyalty=findViewById(R.id.tloyalty);
        final_amount11=findViewById(R.id.final_amount11);
        final_payment1btn =findViewById(R.id.final_payment1btn);


        ////////////////////calculating  delivery charges start //////////////////////////
        fAuth= FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();
        ///////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Province").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    String province=(dataSnapshot.child("province").getValue().toString());
                    System.out.println("province i sequal to :"+province);

                    delivery_charge  =  calculating_address(province);
                    delivery_chg.setText(""+delivery_charge);
               }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////



        ////////////////////////calculating  delivery charges  end/////////////////////////////











//////////////retreiving  total amounts


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



        Cdiscount=1111;
        carddiscount.setText("("+""+Cdiscount+")");
//////////////////calculating card discounts //////////////////******************************************************************************************************************
        /////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Payment1").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                  // number_pattern=Long.parseLong(dataSnapshot.child("cardnumber").getValue().toString());
                    number_pattern=(dataSnapshot.child("cardnumber").getValue().toString());

                    //calling the  calculating discount method  and assign the value to Cdiscount
                    Cdiscount= calculating_card_discout(number_pattern,retreived_amount);
                    famt.setText(""+Cdiscount);



                }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////



//
//        //String  nnn =""+number_pattern;
//        Pattern p = Pattern.compile("1234");
//        System.out.println("byeeeeeeeeeeeeeeeeeeeeee"+number_pattern);
//        String first4char = number_pattern.substring(1,5);
//        System.out.println(first4char);
//
//
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
//



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

                    loyality_amount= calculating_loyality_discout(retreived_loyality_points,retreived_amount);
                    tloyalty.setText(""+loyality_amount);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }); ///////////////////////////// show   end //////////



///////////////////////////////calculating loyality discounts end /////////////////////////////////////////////////


















        //****************************/////////calculating loyality points***********************************


        ///////////////////////////// show   satrt //////////
        db = FirebaseDatabase.getInstance().getReference().child("Loyality_points").child(uid);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    loyality_points=Integer.parseInt(dataSnapshot.child("loyality_points").getValue().toString());

                    ////////////update  start////////////////
                    final_payment1btn.setOnClickListener(new View.OnClickListener() {
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

                                            ////////////add dialog box
                                            AlertDialog.Builder builder =new AlertDialog.Builder(final_payment_Activity.this);
                                            builder.setCancelable(true);
                                            builder.setTitle("Thank you");
                                            builder.setMessage("You have Successfully Ordered");

                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent packageContent = null;
                                                    Intent intent1= new Intent(final_payment_Activity.this,MainActivity.class);
                                                    startActivity(intent1);
                                                }
                                            });
                                            builder.show();


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


                    });





                }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
                    /////////////////insert starts/////////////////////////***********************
                    final_payment1btn.setOnClickListener(new View.OnClickListener() {
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

                                ////////////add dialog box
                                AlertDialog.Builder builder =new AlertDialog.Builder(final_payment_Activity.this);
                                builder.setCancelable(true);
                                builder.setTitle("Thank you");
                                builder.setMessage("You have Successfully Ordered");

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent packageContent = null;
                                        Intent intent1= new Intent(final_payment_Activity.this,MainActivity.class);
                                        startActivity(intent1);
                                    }
                                });
                                builder.show();
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



        System.out.println("retreived_amount:"+retreived_amount);

        System.out.println("delivery_charge:"+delivery_charge);

        System.out.println("Cdiscount:"+Cdiscount);

        System.out.println("loyality_amount:"+loyality_amount);


        display_total=50000;

        //display_total=(retreived_amount+delivery_charge)-(Cdiscount+loyality_amount);





        final_amount11.setText("rs"+display_total);

    }///on create bracket



    //method for calculating  address
    public float calculating_address(String province){
        float totalp=0;

       if(province.equals("Western")){
           totalp=100;
        }else if(province.equals("Central")){
           totalp=200;
        }else if(province.equals("Northern")){
           totalp=200;
        }else if(province.equals("Eastern")){
           totalp=280;
        }else if(province.equals("Colombo")){
           totalp=230;
        }else if(province.equals("Northern")){
           totalp=220;
        }else if(province.equals("North Western")){
           totalp=280;
        }else if(province.equals("Sabaragamuwa")){
           totalp=260;
        }else if(province.equals("Uva")){
           totalp=170;
        }
      return totalp;
    }

    //calculating  loyality discount
    public int calculating_loyality_discout(int points,int amt1){

      int tot=0;
        if(points>2){
            tot=  amt1*10/100;

        }else{
            tot= 0;
        }

        return tot;

    }



    //calculating  card discount
    public float calculating_card_discout(String cardnumber,float amount){
            float amt=0;
        Pattern p = Pattern.compile("1234");

        String first4char = cardnumber.substring(0,4);
        System.out.println(first4char);

        int intForFirst4Char = Integer.parseInt(first4char);
        int ptoint =  Integer.parseInt(p.toString());

        if(ptoint == intForFirst4Char){
           return  amt=amount*10/100;

        }else {
            return amt=0;
        }
   }

}////final  bracket


