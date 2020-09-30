package com.example.pharmeasy.ui.cart;

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

import com.example.pharmeasy.R;
import com.example.pharmeasy.checkout;

public class CartFragment extends Fragment {

    private CartViewModel mViewModel;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // return inflater.inflate(R.layout.fragment_cart, container, false);

        View v= inflater.inflate(R.layout.fragment_cart, container, false);


      // cart details
        Button place_your_order=(Button)v.findViewById(R.id.btnSave);
        place_your_order.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getActivity(), checkout.class));
            }
        });

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}
