package com.example.emergencycaller20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.emergencycaller20.databinding.ActivityMainBinding;
import com.example.emergencycaller20.databinding.ActivityOtherNumsBinding;

import java.util.ArrayList;

public class OtherNums extends AppCompatActivity {

    ActivityOtherNumsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherNumsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] namePlace = {"Police Emergency Hotline","Ambulance/Fire & Rescue","Bomb Disposal Unit","Accident Service General Hospital Colombo","Tourist Police","Police Emergency","Government Information Center","Report Crimes","Emergency Police Mobile Squad","Fire & Ambulance Service", "SL Electricity Board"};

        String[] telNum = {"119","110","0112434251","0112691111","0112421052","0112433333","1919","0112691500","0115717171","0112422222","1987"};

        ArrayList<itemObject> itemObjectArrayList = new ArrayList<>();

        for(int i =0; i<namePlace.length;i++){
            itemObject itemObject = new itemObject(namePlace[i],telNum[i]);
            itemObjectArrayList.add(itemObject);
        }

        ListAdapter listAdapter = new listAdapter(OtherNums.this,itemObjectArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+telNum[position]));
                startActivity(intent);
            }
        });
    }
}