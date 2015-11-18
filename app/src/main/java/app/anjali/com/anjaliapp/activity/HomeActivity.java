package app.anjali.com.anjaliapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import app.anjali.com.anjaliapp.R;

public class HomeActivity extends AppCompatActivity
         {

             AutoCompleteTextView actvsource,actvdestination;
             Button bsearch;
             ProgressBar ploading;
             RelativeLayout rlcontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.drawable.splashimage);

actvsource=(AutoCompleteTextView)findViewById(R.id.input_source);
        actvdestination=(AutoCompleteTextView)findViewById(R.id.input_destination);
        bsearch=(Button)findViewById(R.id.bsearch);
        rlcontent=(RelativeLayout)findViewById(R.id.rlcontent);

        ploading=(ProgressBar)findViewById(R.id.ploading);

        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Search().execute();
            }
        });


    }

             class Search extends AsyncTask<Void,Void,Void>
             {
                 @Override
                 protected void onPreExecute() {
                     super.onPreExecute();
                     ploading.setVisibility(View.VISIBLE);
                     rlcontent.setVisibility(View.INVISIBLE);
                 }

                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);
                     ploading.setVisibility(View.INVISIBLE);
                     rlcontent.setVisibility(View.VISIBLE);
                 }

                 @Override
                 protected Void doInBackground(Void... params) {


                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }


                     return null;
                 }
             }


}
