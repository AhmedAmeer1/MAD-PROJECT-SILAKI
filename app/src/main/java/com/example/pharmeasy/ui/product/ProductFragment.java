package com.example.pharmeasy.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pharmeasy.R;

public class ProductFragment extends Fragment {

    private ProductViewModel ProductViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductViewModel =
                ViewModelProviders.of(this).get(ProductViewModel.class);
        View root = inflater.inflate(R.layout.fragment_product, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();




        return root;
    }
}
