package com.example.emergencycaller20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {
    Button loginbtn;
    Button signUpbtn;
    EditText etLoginEmail;
    EditText etLoginPassword;
    EditText etUserName;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);


        loginbtn = findViewById(R.id.loginBtn);
        signUpbtn = findViewById(R.id.signUpLoginBtn);
        etLoginEmail = findViewById(R.id.editTextLoginEmail);
        etLoginPassword = findViewById(R.id.editTextLoginPassword);
        etUserName = findViewById(R.id.editTextUserName);
        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(view ->{
            loginUser();
        });
        signUpbtn.setOnClickListener(view ->{
            startActivity(new Intent(ActivityLogin.this, RegActivity.class));
        });

    }
    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        String nameUser = etUserName.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ActivityLogin.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        LocationClass.UserName =nameUser;
                        startActivity(new Intent(ActivityLogin.this, MainActivity.class));


                    }else{
                        Toast.makeText(ActivityLogin.this, "Log in Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(ActivityLogin.this)
                .setTitle("Exit?")
                .setMessage("Exiting the App?")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();


    }


}