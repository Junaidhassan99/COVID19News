package com.example.covid19news;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class COVID19Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<COVID19DataType>> {

    String URL="https://content.guardianapis.com/search?q=covid&order-by=newest&order-date=published&page-size=200&api-key=test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid19_activity_main);

        if(!isOnline()){
            Intent NoNetworkIntent=new Intent(COVID19Activity.this,NoNetworkActivity.class);
            startActivity(NoNetworkIntent);
        }

        startProgressBar();

        getSupportLoaderManager().initLoader(0,null,  this);





    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new COVID19Loader(this,URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<COVID19DataType>> loader, ArrayList<COVID19DataType> arrayListOfJsonResponse) {

        stopProgressBar();
        updateUI(arrayListOfJsonResponse);

    }



    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    public void updateUI(final ArrayList<COVID19DataType> arrayListOfJsonResponse){
        COVID19Adapter covid19Adapter=new COVID19Adapter(this,R.layout.covid19_news_items,arrayListOfJsonResponse);
        ListView listView=findViewById(R.id.covid19_list_view);

        TextView emptyView=findViewById(R.id.empty_listview);
        listView.setEmptyView(emptyView);

        listView.setAdapter(covid19Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                linkToWeb(arrayListOfJsonResponse.get(position).getWebUrl());
            }
        });
    }
    public void linkToWeb(String url){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void stopProgressBar(){
        ProgressBar progressBar=findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
    }

    public void startProgressBar(){
        ProgressBar progressBar=findViewById(R.id.pBar);
        progressBar.setVisibility(View.VISIBLE);
    }



}
