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

public class raise_complaint extends AppCompatActivity {
    Button submit;
    Spinner spinner;
    String[] complaint = {"Select Category","Street Lights","Drainage","Garbage","Electricity","Water Supply","Roads"};
    EditText name1,phone1,aadhar1,email1,address1,desc1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    FirebaseAuth mAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raise_complaint);
        submit = (Button) findViewById(R.id.submit);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(raise_complaint.this, android.R.layout.simple_spinner_item,complaint);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        name1 = (EditText) findViewById(R.id.name);
        phone1 = (EditText) findViewById(R.id.phone);
        aadhar1 = (EditText) findViewById(R.id.aadhar);
        email1 = (EditText) findViewById(R.id.email);
        address1 = (EditText) findViewById(R.id.address);
        spinner = (Spinner) findViewById(R.id.spinner);
        desc1 = (EditText) findViewById(R.id.desc);

        mAuth = FirebaseAuth.getInstance();
        reference = database.getReference("complaints");
        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertcomplaintData();
                //Toast.makeText(raise_complaint.this,"COMPLAINT RAISED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertcomplaintData(){
        String name = name1.getText().toString();
        String phone = phone1.getText().toString();
        String aadhar = aadhar1.getText().toString();
        String email = email1.getText().toString();
        String address = address1.getText().toString();
        String complaint = spinner.getSelectedItem().toString();
        String description = desc1.getText().toString();

        if(name.isEmpty()){
            name1.setError("Enter the name..");
            name1.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phone1.setError("Enter the phone number..");
            phone1.requestFocus();
            return;
        }
        if(phone.length()<10){
            phone1.setError("Phone number should have 10 numbers..");
            phone1.requestFocus();
            return;
        }
        if(aadhar.isEmpty()){
            aadhar1.setError("Enter the aadhaar number..");
            aadhar1.requestFocus();
            return;
        }
        if(aadhar.length()<12){
            aadhar1.setError("Aashaar number should have 12 numbers..");
            aadhar1.requestFocus();
            return;
        }
        if(email.isEmpty()){
            email1.setError("Enter the email address..");
            email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Enter valid email address..");
            email1.requestFocus();
            return;
        }
        if(description.isEmpty()){
            desc1.setError("Enter the description..");
            desc1.requestFocus();
            return;
        }
        Complaint_details details = new Complaint_details(name,phone,aadhar,email,address,complaint,description);
        reference.push().setValue(details);
        Intent i = new Intent(raise_complaint.this,citizen_dashboard.class);
        startActivity(i);
        finish();
        Toast.makeText(raise_complaint.this, "Compaint Raised Successfully..!!", Toast.LENGTH_SHORT).show();
        name1.setText("");
        phone1.setText("");
        aadhar1.setText("");
        email1.setText("");
        address1.setText("");
        desc1.setText("");
    }
}