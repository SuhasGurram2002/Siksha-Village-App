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

import org.w3c.dom.Text;

public class staff_dashboard extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    Button logout;
    TextView view_application, view_complaint,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_dashboard);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("staff");
        userID = user.getUid();

        logout = (Button) findViewById(R.id.logout);

        view_application = (TextView) findViewById(R.id.view_application);
        view_complaint = (TextView) findViewById(R.id.view_complaints);
        name = (TextView) findViewById(R.id.name);

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
                Toast.makeText(staff_dashboard.this, "Something Went Wrong..!", Toast.LENGTH_SHORT).show();
            }
        });

        view_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(staff_dashboard.this,view_application.class);
                startActivity(i);
            }
        });

        view_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(staff_dashboard.this,view_complaint.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(staff_dashboard.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}