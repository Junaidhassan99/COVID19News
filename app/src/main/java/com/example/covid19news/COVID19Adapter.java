package com.example.covid19news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class COVID19Adapter extends ArrayAdapter<COVID19DataType> {

    Context context;
    int resource;


    public COVID19Adapter(@NonNull Context context, int resource, @NonNull List<COVID19DataType> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(resource,parent,false);


        TextView dateAndTime=convertView.findViewById(R.id.date_and_time);

        String setDateAndTimeString=getItem(position).getDateAndTime();

        if(setDateAndTimeString!="-") {

            setDateAndTimeString = setDateAndTimeString.substring(0, 11) + "  " + setDateAndTimeString.substring(11, setDateAndTimeString.length());
        }


        dateAndTime.setText(setDateAndTimeString);


        TextView news=convertView.findViewById(R.id.news);
        news.setText(getItem(position).getNews());





        return convertView;
    }
}
