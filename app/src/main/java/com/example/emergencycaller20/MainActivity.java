package com.example.emergencycaller20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //FusedLocationProviderClient fusedLocationProviderClient;
    double latitudeStr,longitudeStr;
    Button btnLogout;
    FirebaseAuth mAuth;
    EditText etToken;
    Button AlertHospital;
    Button AddContacts;
    Button AlertPolice;
    Button AlertFire;
    Button AlertAmbulance;
    Button AlertOther;

    private final static int REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.LogOutBtn);
        mAuth = FirebaseAuth.getInstance();
        //etToken = findViewById(R.id.etDeviceToken);
        AlertHospital = findViewById(R.id.alertHospital);
        AddContacts = findViewById(R.id.addContact);
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        AlertPolice = findViewById(R.id.alertPolice);
        AlertFire = findViewById(R.id.alertFire);
        AlertAmbulance = findViewById(R.id.alertAmbulance);
        AlertOther = findViewById(R.id.alertOther);
        //getLastLocation();


        btnLogout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, ActivityLogin.class));
        });

        //TODO hospital Alert Scenario open the results page and call the Google places API
        AlertHospital.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LocationClass.LongData = longitudeStr;
                LocationClass.LatData = latitudeStr;
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        AlertPolice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LocationClass.LongData = longitudeStr;
                LocationClass.LatData = latitudeStr;
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Police Station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        AlertFire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LocationClass.LongData = longitudeStr;
                LocationClass.LatData = latitudeStr;
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Fire Brigade");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        AlertAmbulance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1990"));
                startActivity(intent);

            }
        });
        AlertOther.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, OtherNums.class));
        });





        AddContacts.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, AddContactActivity.class));
        });



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println( "Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        System.out.println(token);


                        // TODO(developer): for toasting and showing the token


                        //Toast.makeText(MainActivity.this, "Device token : "+ token, Toast.LENGTH_SHORT).show();
                        //etToken.setText(token);

                    }
                });

    }

//    public void getLastLocation(){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
//            fusedLocationProviderClient.getLastLocation()
//                    .addOnSuccessListener(new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if(location != null){
//                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//                                List<Address> addresses = null;
//                                try {
//                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//
////                                    latitudedata.setText(" " + addresses.get(0).getLatitude());
////                                    longitudedata.setText(" " + addresses.get(0).getLongitude());
//
//                                    latitudeStr = addresses.get(0).getLatitude();
//                                    Log.e("latitude string ",String.valueOf(latitudeStr));
//
//                                    longitudeStr = addresses.get(0).getLongitude();
//                                    Log.e("longitude string ",String.valueOf(longitudeStr));
//
//
//                                }catch (IOException e){
//                                    Toast.makeText(MainActivity.this.getApplicationContext(),"Error in List Address",Toast.LENGTH_SHORT).show();
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }
//                    });
//        }
//        else{
//            askPermission();
//
//        }
//
//
//
//    }
//
//    private void askPermission(){
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]
//                {Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode==REQUEST_CODE){
//            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                getLastLocation();
//
//            }else{
//                Toast.makeText(this,"Location Permissions Required",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, ActivityLogin.class));
        }
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(MainActivity.this)
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