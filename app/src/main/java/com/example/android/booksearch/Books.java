package com.example.android.booksearch;

import android.graphics.Bitmap;

/**
 * Created by shubham on 6/21/17.
 */

public class Books {

    private Bitmap mimage;

    private String mName;

    private String mAuthor;

    private float mRating;

    private String mLanguage;

    private String mCategory;

    public Books(Bitmap img,String name,String author,float rating,String language,String category)
    {
        mimage=img;
        mName=name;
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

    public float getRating()
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
