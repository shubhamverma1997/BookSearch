package com.example.android.booksearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shubham on 6/21/17.
 */

public class BookAdapter extends ArrayAdapter<Books> {

    public BookAdapter(Context context, ArrayList<Books> data)
    {
        super(context,0,data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listview=convertView;

        if(listview==null)
        {
            listview= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Books variable=getItem(position);

        ImageView bookImageView=(ImageView) listview.findViewById(R.id.image);
        bookImageView.setImageBitmap(variable.getBitmap());

        TextView nameTextView=(TextView) listview.findViewById(R.id.name);
        nameTextView.setText(variable.getName());

        TextView authorTextView=(TextView) listview.findViewById(R.id.author);
        authorTextView.setText(variable.getAuthor());

        RatingBar rating=(RatingBar) listview.findViewById(R.id.rating);
        rating.setRating((float)variable.getRating());

        if(Double.isNaN(variable.getRating()))
        {
            rating.setVisibility(View.GONE);
            TextView noRating=(TextView) listview.findViewById(R.id.noRating);
            noRating.setVisibility(View.VISIBLE);
        }

        TextView languageTextView=(TextView) listview.findViewById(R.id.language);
        languageTextView.setText(variable.getLanguage());

        TextView categoryTextView=(TextView) listview.findViewById(R.id.category);
        categoryTextView.setText(variable.getCategory());

        return listview;
    }
}
