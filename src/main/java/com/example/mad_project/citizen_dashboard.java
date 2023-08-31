package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class citizen_dashboard extends AppCompatActivity {
    TextView view_service,raise_complaint,my_profile,apply_service;
    Button logout;
    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_dashboard);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("citizens");
        userID = user.getUid();

        logout = (Button) findViewById(R.id.logout);
        view_service = (TextView) findViewById(R.id.view_service);
        raise_complaint = (TextView) findViewById(R.id.raise_complaint);
        my_profile = (TextView) findViewById(R.id.my_profile);
        apply_service = (TextView) findViewById(R.id.apply_service);

        final TextView name = (TextView) findViewById(R.id.name);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Citizens userprofile = snapshot.getValue(Citizens.class);
                String disp_name = userprofile.name;
                name.setText("Welcome, "+disp_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(citizen_dashboard.this, "Something Went Wrong..!", Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(citizen_dashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        apply_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(citizen_dashboard.this,Application.class);
                startActivity(i);
            }
        });
        raise_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(citizen_dashboard.this,raise_complaint.class);
                startActivity(intent);
            }
        });
        view_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(citizen_dashboard.this,view_services.class);
                startActivity(intent);
            }
        });
        my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(citizen_dashboard.this,citizen_profile.class);
                startActivity(i);
            }
        });

    }
}