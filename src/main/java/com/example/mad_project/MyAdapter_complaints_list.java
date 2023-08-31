package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_complaints_list extends RecyclerView.Adapter<MyAdapter_complaints_list.MyViewHolder>{

    Context context;

    public MyAdapter_complaints_list(Context context, ArrayList<complaints_list> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<complaints_list> list;

    @NonNull
    @Override
    public MyAdapter_complaints_list.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complaints,parent,false);
        return new MyAdapter_complaints_list.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_complaints_list.MyViewHolder holder, int position) {

        complaints_list app = list.get(position);
        holder.name.setText(app.getName());
        holder.phone.setText(app.getPhone());
        holder.email.setText(app.getEmail());
        holder.address.setText(app.getAddress());
        holder.complaint.setText(app.getComplaint());
        holder.description.setText(app.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,phone,email,address,complaint,description;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
            complaint = itemView.findViewById(R.id.complaint);
            description = itemView.findViewById(R.id.description);
        }
    }
}
