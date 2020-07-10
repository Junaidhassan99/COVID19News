package com.example.covid19news;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class COVID19Loader extends AsyncTaskLoader<ArrayList<COVID19DataType>> {

    String URL;

    public COVID19Loader(@NonNull Context context,String URL) {
        super(context);
        this.URL=URL;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }




    @Nullable
    @Override
    public ArrayList<COVID19DataType> loadInBackground() {

        String jsonResponse=COVID19Utilities.getJsonResponse(URL);
        //Log.e("Message one ",jsonResponse);
        ArrayList<COVID19DataType> arrayListOfJsonResponse=COVID19Utilities.readJsonResponse(jsonResponse);


        return arrayListOfJsonResponse;
    }
}
