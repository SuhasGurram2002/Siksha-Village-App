package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class citizen_signup extends AppCompatActivity {
    TextView login;
    EditText name1,username1,email1,address1,aadhar1,phone1,password1,confirm_password1;
    RadioGroup radiogrp;
    RadioButton gender1;
    public Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_signup);
        mAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.sign_up);
        login = (TextView) findViewById(R.id.login);

        name1 = (EditText) findViewById(R.id.name);
        username1 = (EditText) findViewById(R.id.username);
        email1 = (EditText) findViewById(R.id.email);
        address1 = (EditText) findViewById(R.id.address);
        aadhar1 = (EditText) findViewById(R.id.aadhar);
        phone1 = (EditText) findViewById(R.id.phone);
        password1 = (EditText) findViewById(R.id.password);
        radiogrp = (RadioGroup) findViewById(R.id.gender);
        confirm_password1 = (EditText) findViewById(R.id.confirm_password);

        reference = database.getReference("citizens");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radiogrp.getCheckedRadioButtonId();
                gender1 = findViewById(selectedId);
                insertCitizenData();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(citizen_signup.this,citizen_login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void insertCitizenData(){
        String name = name1.getText().toString();
        String username = username1.getText().toString();
        String gender = gender1.getText().toString();
        String email = email1.getText().toString();
        String address = address1.getText().toString();
        String aadhar = aadhar1.getText().toString();
        String phone = phone1.getText().toString();
        String password = password1.getText().toString();
        String confirm_password = confirm_password1.getText().toString();

        if(name.isEmpty()){
            name1.setError("Enter the name..");
            name1.requestFocus();
            return;
        }
        if(username.isEmpty()){
            username1.setError("Enter a username..");
            username1.requestFocus();
            return;
        }
        if(email.isEmpty()){
            email1.setError("Email is required..");
            email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Provide valid email address..");
            email1.requestFocus();
            return;
        }
        if(address.isEmpty()){
            address1.setError("Enter the address..");
            address1.requestFocus();
            return;
        }
        if(aadhar.isEmpty()){
            aadhar1.setError("Enter the aadhar number..");
            aadhar1.requestFocus();
            return;
        }
        if(aadhar.length()<12){
            aadhar1.setError("Aadhar should have 12 numbers..");
            aadhar1.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phone1.setError("Phone number should have 10 numbers..");
            phone1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            password1.setError("Password is required");
            password1.requestFocus();
            return;
        }
        if(password.length()<6){
            password1.setError("Password should contain min. 8 characters");
            password1.requestFocus();
            return;
        }
        /*if(password != confirm_password){
            confirm_password1.setError("Password does not match..!");
            confirm_password1.requestFocus();
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Citizens citizens = new Citizens(name,username,gender,email,address,aadhar,phone,password);

                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(citizens).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Intent intent = new Intent( citizen_signup.this,citizen_login.class);
                                                startActivity(intent);
                                                finish();
                                                Toast.makeText(citizen_signup.this, "SIGN UP SUCCESSFUL "+name, Toast.LENGTH_SHORT).show();
                                                name1.setText("");
                                                username1.setText("");
                                                email1.setText("");
                                                address1.setText("");
                                                aadhar1.setText("");
                                                phone1.setText("");
                                                password1.setText("");
                                                confirm_password1.setText("");
                                            }
                                            else{
                                                Toast.makeText(citizen_signup.this, "FAILED TO REGISTER..!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(citizen_signup.this, "FAILED TO REGISTER..!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}