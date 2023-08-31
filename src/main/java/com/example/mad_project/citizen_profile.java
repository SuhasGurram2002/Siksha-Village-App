package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class citizen_profile extends AppCompatActivity {
     Button back;
     FirebaseUser user;
     DatabaseReference reference;
     String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_profile);
        back = (Button) findViewById(R.id.back);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("citizens");
        userID = user.getUid();

        final TextView name = (TextView) findViewById(R.id.name);
        final TextView username = (TextView) findViewById(R.id.username);
        final TextView gender = (TextView) findViewById(R.id.gender);
        final TextView email = (TextView) findViewById(R.id.email);
        final TextView address = (TextView) findViewById(R.id.address);
        final TextView aadhar = (TextView) findViewById(R.id.aadhar);
        final TextView phone = (TextView) findViewById(R.id.phone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Citizens userprofile = snapshot.getValue(Citizens.class);
                String name1 = userprofile.name;
                String username1 = userprofile.username;
                String gender1 = userprofile.gender;
                String email1 = userprofile.email;
                String address1 = userprofile.address;
                String aadhar1 = userprofile.aadhar;
                String phone1 = userprofile.phone;
                name.setText(name1);
                username.setText(username1);
                gender.setText(gender1);
                email.setText(email1);
                address.setText(address1);
                aadhar.setText(aadhar1);
                phone.setText(phone1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(citizen_profile.this, "Something Went Wrong..!", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(citizen_profile.this,citizen_dashboard.class);
                startActivity(i);
                finish();
            }
        });

    }
}