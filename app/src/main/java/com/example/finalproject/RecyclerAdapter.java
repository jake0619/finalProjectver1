package com.example.finalproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    //String [] data;
    ArrayList<Product> data;
    Context context;
    private static final String LOG_TAG =
            RecyclerAdapter.class.getSimpleName();

    public RecyclerAdapter(ArrayList<Product> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Product currProduct = data.get(position);
        String pName = data.get(position).getpName();
        //Display text from string array on to text view in individual cards
        holder.bindTo(currProduct);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        TextView descView;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView =itemView.findViewById(R.id.textTitle);
            descView = itemView.findViewById(R.id.textDesc);
            imgView = itemView.findViewById(R.id.imgView);
            itemView.setOnClickListener(this);
        }

        void bindTo(Product currProduct){

            //if product contains an image link
            if(currProduct.getImageLink() != ""){

                Glide.with(context).load(currProduct.getImageLink()).into(imgView);
            }
            else{
                //default image
                Glide.with(context).load("https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/spinner-512.png").into(imgView);
            }

            titleView.setText(currProduct.getpName());
            descView.setText(currProduct.getpPrice());
        }

        @Override
        public void onClick(View v) {
            Product currProduct = data.get(getAdapterPosition());
            String pName = currProduct.getpName();
            Toast.makeText(context, pName, Toast.LENGTH_SHORT).show();
        }
    }

    public void setData(ArrayList<Product> pData){
        this.data = pData;
    }
}