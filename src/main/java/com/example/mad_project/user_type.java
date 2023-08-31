package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_type extends AppCompatActivity {
    public Button button,staff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);
        staff = (Button)findViewById(R.id.staff);
        button = (Button) findViewById(R.id.citizen);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_type.this,citizen_signup.class);
                startActivity(intent);
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(user_type.this,staff_login.class);
                startActivity(i);
            }
        });
    }
}