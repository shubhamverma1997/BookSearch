package com.example.android.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    public String URL_PREFIX="https://www.googleapis.com/books/v1/volumes?q=";

    private String mUrlString;

    private String mSearchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button find=(Button) findViewById(R.id.find);
        final EditText input=(EditText) findViewById(R.id.entryField);
        input.setRawInputType(InputType.TYPE_CLASS_TEXT);
        input.setImeOptions(EditorInfo.IME_ACTION_GO);

        find.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mSearchQuery=input.getText().toString();
                mUrlString=URL_PREFIX+mSearchQuery+"&maxResults=40";

                if(mSearchQuery!=null||mSearchQuery!="") {
                    Intent result = new Intent(v.getContext(), BooksActivity.class);
                    result.putExtra("Url", mUrlString);
                    startActivity(result);
                }
            }
        });


    }


}
