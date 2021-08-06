package com.example.profileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterLinear extends RecyclerView.Adapter<AdapterLinear.MyViewHolder> {



    private Context mContext;
    private List<DataClass> users;

    public AdapterLinear(Context mContext, List<DataClass> users) {
        this.mContext = mContext;
        this.users = users;
    }


    @NonNull
    @Override
    public AdapterLinear.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.linear_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLinear.MyViewHolder holder, int position) {

        holder.Name.setText(users.get(position).getPerson().getName());
            holder.DOB.setText("DOB: " + users.get(position).getPerson().getBirthday());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(users.get(position).getPerson().getImage().getOriginal());
            Glide.with(mContext)
                    .load(stringBuilder.toString())
                    .into(holder.Photo);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name, DOB;
        CircleImageView Photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            Name = itemView.findViewById(R.id.NameL);
            DOB = itemView.findViewById(R.id.DOBL);
            Photo = itemView.findViewById(R.id.PhotoL);
        }


    }
}
