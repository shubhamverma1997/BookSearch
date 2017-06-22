package com.example.android.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public String URL_PREFIX="https://www.googleapis.com/books/v1/volumes?q=";

    private String mUrlString;

    private String mSearchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText editText=(EditText) findViewById(R.id.entryField);
        //editText.setOn
        Button find=(Button) findViewById(R.id.find);

        find.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input=(EditText) findViewById(R.id.entryField);
                mSearchQuery=input.getText().toString();
                mUrlString=URL_PREFIX+mSearchQuery+"&maxResults=40";

                Intent result=new Intent(v.getContext(),BooksActivity.class);
                result.putExtra("Url",mUrlString);
                startActivity(result);
            }
        });


    }


}
