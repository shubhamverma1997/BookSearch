package com.example.android.booksearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
            ProgressBar rating=(ProgressBar) findViewById(R.id.spinner);
            rating.setVisibility(View.GONE);
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

        listView.setEmptyView(findViewById(R.id.spinner))
        ;

        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=networkInfo!=null && networkInfo.isConnectedOrConnecting();

        if(!isConnected)
        {
            ProgressBar progressBar=(ProgressBar) findViewById(R.id.spinner);
            progressBar.setVisibility(View.GONE);
            TextView empty=(TextView) findViewById(R.id.empty);
            empty.setText("No Internet Connection");
        }

        mAdapter=new BookAdapter(this,new ArrayList<Books>());
        listView.setAdapter(mAdapter);
    }
}
