package com.example.emergencycaller20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {
    Button SignUpReg;
    EditText etRegEmail;
    EditText etRegPassword;
    EditText etRegPassword2;
    TextView tvLoginHere;
    String email;
    String password;

    FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        SignUpReg = findViewById(R.id.signUpbtn);
        tvLoginHere = findViewById(R.id.alreadyRegisteredText);
        etRegEmail = findViewById(R.id.editTextEmail);
        etRegPassword = findViewById(R.id.editTextPassword);
        etRegPassword2 = findViewById(R.id.editTextCheckPassword);



        mAuth = FirebaseAuth.getInstance();

        SignUpReg.setOnClickListener(view ->{
            createUser();


        });
        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegActivity.this, ActivityLogin.class));
        });

    }

    private void createUser(){
        email = etRegEmail.getText().toString();
        password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        senddata();
                        Toast.makeText(RegActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegActivity.this, ActivityLogin.class));

                    }else{
                        Toast.makeText(RegActivity.this, "Registration Error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
    public void senddata(){
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("token", "123456789");


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // Log.w(TAG, "Error adding document", e);
                    }
                });

    }

}