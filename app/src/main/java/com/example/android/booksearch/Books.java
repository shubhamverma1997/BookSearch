package com.example.android.booksearch;

import android.graphics.Bitmap;

/**
 * Created by shubham on 6/21/17.
 */

public class Books {

    private Bitmap mimage;

    private String mName;

    private String mAuthor;

    private double mRating;

    private String mLanguage;

    private String mCategory;

    public Books(Bitmap img,String name,String author,double rating,String language,String category)
    {
        mimage=img;
        if(name.length()<30)
            mName=name;
        else
        {
            mName=name.substring(0,27);
            mName=mName+"...";
        }
        mAuthor=author;
        mRating=rating;
        mLanguage=language;
        mCategory=category;

    }

    public Bitmap getBitmap()
    {
        return mimage;
    }

    public String getName()
    {
        return mName;
    }

    public String getAuthor()
    {
        return mAuthor;
    }

    public double getRating()
    {
        return mRating;
    }

    public String getLanguage()
    {
        return mLanguage;
    }

    public String getCategory()
    {
        return mCategory;
    }
}
