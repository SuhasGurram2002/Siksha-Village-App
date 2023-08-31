package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Application extends AppCompatActivity {
    Button apply;
    Spinner spinner;
    String[] service = {"Employment Guarantee Scheme","Commercial Rural Scheme","Free Fertilizer Scheme","Library service to rural students","Water Pipeline to agriculture land"};
    EditText name1,aadhar1,email1,phone1,address1;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application);



        apply = findViewById(R.id.apply2);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Application.this, android.R.layout.simple_spinner_item,service);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        name1 = (EditText) findViewById(R.id.name);
        spinner = (Spinner) findViewById(R.id.spinner);
        email1 = (EditText) findViewById(R.id.email);
        address1 = (EditText) findViewById(R.id.address);
        aadhar1 = (EditText) findViewById(R.id.aadhar);
        phone1 = (EditText) findViewById(R.id.phone);

        mAuth = FirebaseAuth.getInstance();
        reference = database.getReference("application");
        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertApplicationData();
            }
        });

    }
    private void insertApplicationData(){
        String name = name1.getText().toString();
        String scheme = spinner.getSelectedItem().toString();
        String phone = phone1.getText().toString();
        String email = email1.getText().toString();
        String address = address1.getText().toString();
        String aadhar = aadhar1.getText().toString();

        if(name.isEmpty()){
            name1.setError("Enter the name..");
            name1.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phone1.setError("Enter the name..");
            phone1.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phone1.setError("Enter valid phone number");
            phone1.requestFocus();
            return;
        }
        if(email.isEmpty()){
            email1.setError("Enter the email address..");
            email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            name1.setError("Enter valid email address");
            name1.requestFocus();
            return;
        }
        if(address.isEmpty()){
            address1.setError("Address is required..");
            address1.requestFocus();
            return;
        }
        if(aadhar.isEmpty()){
            aadhar1.setError("Enter the aadhar  number");
            aadhar1.requestFocus();
            return;
        }
        if(aadhar.length()<12){
            name1.setError("Enter valid aadhar number..");
            name1.requestFocus();
            return;
        }

        application_details details = new application_details(name,aadhar,email,phone,address,scheme);
        reference.push().setValue(details);
        Intent i = new Intent(Application.this,citizen_dashboard.class);
        startActivity(i);
        finish();
        Toast.makeText(Application.this, "Scheme Applied Successfully..!!", Toast.LENGTH_SHORT).show();
        name1.setText("");
        phone1.setText("");
        email1.setText("");
        address1.setText("");
        aadhar1.setText("");


    }
}