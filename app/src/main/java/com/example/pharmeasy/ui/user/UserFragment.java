package com.example.pharmeasy.ui.user;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pharmeasy.ContactUs;
import com.example.pharmeasy.R;
import com.example.pharmeasy.SignIn;
import com.example.pharmeasy.Signup;
import com.example.pharmeasy.UpdateRemoveAddress;
import com.example.pharmeasy.feedback;
import com.example.pharmeasy.payment_update;
import com.example.pharmeasy.personal_details;
import com.example.pharmeasy.welcome;

public class UserFragment extends Fragment {

    private UserFragmentViewModel mViewModel;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_user, container, false);


        //personal details
        Button b1=(Button)v.findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), personal_details.class));
            }
        });

        //feedback
        Button b12=(Button)v.findViewById(R.id.button12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), feedback.class));
            }
        });

        //logout
        Button b4=(Button)v.findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SignIn.class));
            }
        });


        Button b13=(Button)v.findViewById(R.id.button13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), payment_update.class));
            }
        });


        //contact us
        Button b3=(Button)v.findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ContactUs.class));
            }
        });


        //delivery details
        Button b2=(Button)v.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdateRemoveAddress.class));
            }
        });













        return v;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserFragmentViewModel.class);
        // TODO: Use the ViewModel










    }








}
