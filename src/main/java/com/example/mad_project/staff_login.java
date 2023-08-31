package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class staff_login extends AppCompatActivity {

    Button btn_login;
    EditText email1,password1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login);

        email1 = (EditText)findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        btn_login = (Button) findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                staffLogin();

            }
        });

    }
    private void staffLogin(){
        String email = email1.getText().toString().trim();
        String password = password1.getText().toString().trim();

        if(email.isEmpty()){
            email1.setError("Enter the e-mail address..");
            email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Enter the valid email address..!");
            email1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            password1.setError("Enter the password..!");
            password1.requestFocus();
            return;
        }
        if(password.length()<6){
            password1.setError("Min. length of password is 6.");
            password1.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(staff_login.this,staff_dashboard.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(staff_login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    email1.setText("");
                    password1.setText("");
                }
                else{
                    Toast.makeText(staff_login.this,"Login Failed..!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}