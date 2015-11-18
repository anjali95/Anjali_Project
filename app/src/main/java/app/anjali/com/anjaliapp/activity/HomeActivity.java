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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.model.Train;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.ConvertToString;
import app.anjali.com.anjaliapp.util.UrlCreator;

public class HomeActivity extends AppCompatActivity
         {

             AutoCompleteTextView actvsource,actvdestination;
             Button bsearch;
             ProgressBar ploading;
             RelativeLayout rlcontent;


             List<Train> liststationname;
             List<String> liststringstation;

             List<Train> listtrain;
             List<String> liststringtrain;

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

        new GetStation().execute();

        new GetTrain().execute();


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

             class GetStation extends AsyncTask<Void,Void,Void>
             {
                 @Override
                 protected void onPreExecute() {
                     super.onPreExecute();

                 }

                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);

                     ArrayAdapter<String> stationadapter=new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,liststringstation);
                     actvsource.setAdapter(stationadapter);
                     actvdestination.setAdapter(stationadapter);
                 }

                 @Override
                 protected Void doInBackground(Void... params) {


                  String resultstation =   Connectivity.makeServiceCall(UrlCreator.geturl(0));

                     try {
                         parsingstation(resultstation);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                     return null;
                 }

                 private void parsingstation(String resultstation) throws JSONException {

                     JSONObject jo=new JSONObject(resultstation);
                 JSONArray jarray = jo.getJSONArray("stations");

                     liststationname=new ArrayList<Train>();
                     liststringstation=new ArrayList<String>();

                     for(int i=0;i<jarray.length();i++)
                     {
                       JSONObject jobject=  jarray.getJSONObject(i);
                   String stationcode =      jobject.getString("station_Code");
                   String  stationname =     jobject.getString("Station_Name");

                         liststringstation.add(stationname+" - "+stationcode);
                     }


                 }
             }


             class GetTrain extends AsyncTask<Void,Void,Void>
             {
                 @Override
                 protected void onPreExecute() {
                     super.onPreExecute();

                 }

                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);

                     ArrayAdapter<String> stationadapter=new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,liststringtrain);
                     actvsource.setAdapter(stationadapter);
                     actvdestination.setAdapter(stationadapter);
                 }

                 @Override
                 protected Void doInBackground(Void... params) {


                     String resulttrain =   Connectivity.makeServiceCall(UrlCreator.geturl(1));

                     try {
                         parsingtrain(resulttrain);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                     return null;
                 }

                 private void parsingtrain(String resultstation) throws JSONException {

                     JSONObject jo=new JSONObject(resultstation);
                     JSONArray jarray = jo.getJSONArray("trains");

                     liststationname=new ArrayList<Train>();
                     liststringstation=new ArrayList<String>();

                     for(int i=0;i<jarray.length();i++)
                     {
                         JSONObject jobject=  jarray.getJSONObject(i);
                         String trainno =      jobject.getString("Train_No.");
                         String  trainname =     jobject.getString("train_Name");

                         liststringtrain.add(trainno+" - "+trainname);
                     }


                 }
             }
}
