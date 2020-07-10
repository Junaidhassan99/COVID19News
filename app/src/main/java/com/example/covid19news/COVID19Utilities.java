package com.example.covid19news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class COVID19Utilities {

    //gets a url in string for and returns a string of json response
    public static String getJsonResponse(String URL){

        URL url=null;
        StringBuilder stringBuilder=new StringBuilder();

        try {
            url=new URL(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            InputStream inputStream=httpURLConnection.getInputStream();

            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String line=bufferedReader.readLine();
            while (line!=null){
                stringBuilder.append(line);
                line=bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }


    //Reads Json Response and returns an array of relavent info
    public static ArrayList<COVID19DataType> readJsonResponse(String jsonResponse){

        ArrayList<COVID19DataType> covid19news=new ArrayList<COVID19DataType>();

        try {
            JSONObject jsonObject=new JSONObject(jsonResponse);
            JSONObject jsonObjectResponse=jsonObject.getJSONObject("response");
            JSONArray jsonArrayResults=jsonObjectResponse.getJSONArray("results");

            for(int i=0;i<jsonArrayResults.length();i++){
                JSONObject jsonObjectAtPosition=jsonArrayResults.getJSONObject(i);

                String dateAndTime=jsonObjectAtPosition.getString("webPublicationDate");
                String webUrl=jsonObjectAtPosition.getString("webUrl");
                String news=jsonObjectAtPosition.getString("webTitle");

                covid19news.add(new COVID19DataType(dateAndTime,news,webUrl));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        covid19news.add(new COVID19DataType("-","       End of results",""));


        return covid19news;

    }






}
