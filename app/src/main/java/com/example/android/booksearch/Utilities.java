package com.example.android.booksearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by shubham on 6/21/17.
 */

public class Utilities {
    private Utilities()
    {

    }

    public static ArrayList<Books> fetchData(String urlString)
    {
        String JSONresponse="";
        JSONObject json=null;
        URL url=null;

        if(urlString==null||urlString=="")
            return null;
        url=createUrl(urlString);

        try{
            JSONresponse=makeHttpRequest(url);
        }
        catch (IOException e)
        {
            Log.e("Utilities","In makeHttpRequest ",e);
        }

        return extractData(JSONresponse);
    }

    public static URL createUrl(String urlString)
    {
        URL url=null;
        if(urlString==null)
            return null;
        try{
            url=new URL(urlString);
        }
        catch (MalformedURLException e)
        {
            Log.e("Utilities","Malinformed Url");
        }
        return url;

    }
    public static String makeHttpRequest(URL url) throws IOException
    {
        String JSONResponse="";
        InputStream inputStream=null;

        if (url==null)
            return "";

        HttpURLConnection urlConnection=null;

        try{
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK)
            {
                inputStream=urlConnection.getInputStream();
                JSONResponse=convertToJSONString(inputStream);
            }
            else
            {
                Log.v("Utilities","Error in opening Url");
            }
        }
        catch (IOException e)
        {
            Log.e("Utilities","Error in IO");
        }
        finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
            if (inputStream!=null)
                inputStream.close();
        }

        return JSONResponse;
    }

    public static String convertToJSONString(InputStream inputStream) throws IOException
    {
        if (inputStream==null)
            return null;
        StringBuilder output=new StringBuilder();

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader=new BufferedReader(inputStreamReader);

        String line=reader.readLine();
        while (line!=null)
        {
            output.append(line);
            line=reader.readLine();
        }

        return output.toString();
    }

    public static ArrayList<Books> extractData(String JSONResponse)
    {
        Bitmap image;
        String imageurl;
        String name;
        String author;
        float rating;
        String language;
        String category;

        if (JSONResponse==null||JSONResponse=="")
            return null;

        ArrayList<Books> books=new ArrayList<Books>();

        try {

            JSONObject object=new JSONObject(JSONResponse);
            JSONArray array=object.getJSONArray("items");

            for(int i=0;i<array.length() && i<20;i++)
            {
                JSONObject itemno=array.getJSONObject(i);
                JSONObject volumeInfo=itemno.getJSONObject("volumeInfo");
                JSONObject imageLinks=volumeInfo.getJSONObject("imageLinks");
                imageurl=imageLinks.getString("thumbnail");
                image=getBitmapfromUrl(imageurl);
                name=volumeInfo.getString("title");
                author=volumeInfo.getJSONArray("authors").getString(0);
                rating=volumeInfo.getInt("ratingsCount");
                language=volumeInfo.getString("language");
                category=volumeInfo.getJSONArray("categories").getString(0);

                books.add(new Books(image,name,author,rating,language,category));
            }

        }
        catch (JSONException e)
        {
            Log.e("Utilities","Error in extractData ",e);
        }

        return books;
    }

    public static Bitmap getBitmapfromUrl(String urlString)
    {
        Bitmap bmp=null;
        HttpURLConnection urlConnection=null;
        URL url=createUrl(urlString);
        try {
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            bmp= BitmapFactory.decodeStream(inputStream);

        }
        catch (IOException e)
        {
            Log.e("Utilities","HttpUrlConnection problem",e);
        }
        finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return bmp;
    }
}
