package com.example.android.booksearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by shubham on 6/22/17.
 */

public class BooksActivity extends AppCompatActivity{


    public BookAdapter mAdapter;

    public String mUrl;

    public class BooksAsyncTask extends AsyncTask<String,Void,ArrayList<Books>>
    {
        @Override
        protected ArrayList<Books> doInBackground(String... params) {

            if(params[0]==null||params.length<1)
                return null;

            ArrayList<Books> booksFetched=Utilities.fetchData(params[0]);
            return booksFetched;
        }

        @Override
        protected void onPostExecute(ArrayList<Books> bookses) {
            mAdapter.clear();

            if(bookses!=null && !bookses.isEmpty())
            {
                mAdapter.addAll(bookses);
            }
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUrl=getIntent().getStringExtra("Url");

        new BooksAsyncTask().execute(mUrl);

        ListView listView=(ListView) findViewById(R.id.list);

        mAdapter=new BookAdapter(this,new ArrayList<Books>());
        listView.setAdapter(mAdapter);
    }
}
