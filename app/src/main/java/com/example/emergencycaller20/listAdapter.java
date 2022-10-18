package com.example.emergencycaller20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<itemObject> {

    public listAdapter(Context context, ArrayList<itemObject> itemArraylist){
        super(context,R.layout.list_item_others, itemArraylist);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        itemObject item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_others,parent,false);

        }

        TextView namePlace = convertView.findViewById(R.id.namePlace);
        TextView telNum = convertView.findViewById(R.id.telNum);


        namePlace.setText(item.namePlace);
        telNum.setText(item.telNum);


        return convertView;
    }
}
