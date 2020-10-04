package com.example.pharmeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  OrderConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        Button btn45 =(Button)findViewById(R.id.button45);


        btn45.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent packageContent = null;
                Intent intent= new Intent(OrderConfirmation.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }




//    Button btn= (Button) findViewById(R.id.button2);
//
//    btn.setOnClickListener(new View.OnClickListener)

    public void Launch(View v){
        Intent i = new Intent(this,UpdateRemoveAddress.class);
        startActivity(i);
    }

}