package com.example.finalproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    //String [] data;
    ArrayList<Product> data;
    Context context;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView =itemView.findViewById(R.id.textTitle);
            descView = itemView.findViewById(R.id.textDesc);
            itemView.setOnClickListener(this);
        }

        void bindTo(Product currProduct){
            titleView.setText(currProduct.getpName());
            descView.setText(currProduct.getpDesc());
        }

        @Override
        public void onClick(View v) {
            Product currProduct = data.get(getAdapterPosition());
            String pName = currProduct.getpName();
            Toast.makeText(context, pName, Toast.LENGTH_SHORT).show();
        }
    }
}