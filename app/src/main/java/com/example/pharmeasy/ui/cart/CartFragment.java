package com.example.pharmeasy.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.pharmeasy.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CartFragment extends Fragment{

    private static View cartView;
    private RecyclerView cartList;
    private DatabaseReference cartreference;
     float totalCost = 0;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        cartView=inflater.inflate(R.layout.fragment_cart,container,false);
        cartList=(RecyclerView)cartView.findViewById(R.id.cartList);
        cartList.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList.setNestedScrollingEnabled(true);
        cartreference = FirebaseDatabase.getInstance().getReference().child("cart");
        return cartView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartreference, Cart.class)
                        .build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CartViewHolder holder, int i, @NonNull final Cart model) {
                holder.productName.setText(model.getProductName());
                Picasso.get().load(model.getProductImage()).into(holder.productImage);
                holder.price.setText(model.getPrice());
                holder.finalPrice.setText(model.getPrice());
                final float price = Float.parseFloat(model.getPrice().substring(model.getPrice().lastIndexOf(" ")+1));
                totalCost = totalCost + price;
                holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                    @Override
                    public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                        float finalPrice = price*Integer.parseInt(holder.elegantNumberButton.getNumber());
                        holder.finalPrice.setText("LKR "+finalPrice);
                        if(oldValue<newValue)
                        totalCost = totalCost + price;
                        else
                            totalCost = totalCost - price;

                        holder.total.setText("LKR "+totalCost);

                    }
                });
                holder.deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("cart").child(model.getCartId());
                        dr.removeValue();
                        Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                        totalCost = totalCost - price*Integer.parseInt(holder.elegantNumberButton.getNumber());
                        holder.total.setText("LKR "+totalCost);
                    }
                });
//                holder.updateBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("cart").child(model.getCartId());
//                        System.out.println(holder.elegantNumberButton.getNumber());
//                        Cart crt = new Cart(model.getPrice(),model.getProductName(),model.getProductImage(),model.getDescription(),model.getCartId(),holder.elegantNumberButton.getNumber());
//                        ref.setValue(crt);
//
//                        Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_LONG).show();
//
//                    }
//                });


                holder.description.setText(model.getDescription());
                holder.total.setText("LKR "+totalCost);


            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitemview, parent, false);
                CartViewHolder cartViewHolder = new CartViewHolder(view);
                return cartViewHolder;
            }

        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        cartList.setAdapter(adapter);
    }



    public static class CartViewHolder extends RecyclerView.ViewHolder{
        TextView productName,price,description,finalPrice,total;
        ElegantNumberButton elegantNumberButton;
        ImageView productImage;
        Button deletebtn,updateBtn;
        public CartViewHolder(@NonNull View itemView){
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.price);
            description=itemView.findViewById(R.id.qty);
            productImage=itemView.findViewById(R.id.productImage);
            finalPrice = itemView.findViewById(R.id.finalPrice);
            elegantNumberButton=itemView.findViewById(R.id.elegantNumberButton);
            deletebtn=itemView.findViewById(R.id.delete);
            total=cartView.findViewById(R.id.total);
            updateBtn=cartView.findViewById(R.id.update);


        }
    }

}
