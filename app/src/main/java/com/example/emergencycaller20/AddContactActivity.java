package com.example.emergencycaller20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddContactActivity extends AppCompatActivity {

    EditText etMail;
    EditText etPhone;
    Button addCntct;

    String email,telNum;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        etMail = findViewById(R.id.editTextContactMail);
        etPhone = findViewById(R.id.editTextPhone);
        addCntct = findViewById(R.id.addBtn);



        addCntct.setOnClickListener(view ->{

                senddata();
        });
    }

    public void senddata(){
        email = etMail.getText().toString();
        telNum= etPhone.getText().toString();


        Map<String, Object> user = new HashMap<>();
        user.put("contact", email);
        user.put("tel", telNum);


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        etMail.setText("");
                        etPhone.setText("");
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