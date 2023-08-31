package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_applicants_list extends RecyclerView.Adapter<MyAdapter_applicants_list.MyViewHolder> {

    Context context;

    public MyAdapter_applicants_list(Context context, ArrayList<applicants_list> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<applicants_list> list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.applicants,parent,false);
                return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        applicants_list app = list.get(position);
        holder.name.setText(app.getName());
        holder.phone.setText(app.getPhone());
        holder.email.setText(app.getEmail());
        holder.address.setText(app.getAddress());
        holder.scheme.setText(app.getScheme());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,phone,email,address,scheme;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
            scheme = itemView.findViewById(R.id.scheme);
        }
    }
}
