package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class services_information extends AppCompatActivity  {

    TextView service_name,service_info;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_information);

        apply = (Button) findViewById(R.id.apply);
        service_name = (TextView) findViewById(R.id.service_name);
        service_info = (TextView) findViewById(R.id.service_info);

        Bundle bundle = getIntent().getExtras();

        String scheme_name = bundle.getString("scheme_name");
        String scheme_info = bundle.getString("scheme_info");

        service_name.setText(scheme_name);
        service_info.setText(scheme_info);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(services_information.this,Application.class);
                startActivity(i);
                finish();
            }
        });
    }
}