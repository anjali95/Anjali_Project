package app.anjali.com.anjaliapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

             private MenuItem mSearchAction;
             private boolean isSearchOpened = false;
             private AutoCompleteTextView edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

     //   getSupportActionBar().setIcon(R.drawable.splashimage);

actvsource=(AutoCompleteTextView)findViewById(R.id.input_source);
        actvdestination=(AutoCompleteTextView)findViewById(R.id.input_destination);
        bsearch=(Button)findViewById(R.id.bsearch);
        rlcontent=(RelativeLayout)findViewById(R.id.rlcontent);

        ploading=(ProgressBar)findViewById(R.id.ploading);

        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,EnquiryActivity.class);
                i.putExtra("source",actvsource.getText().toString().split("-")[0].trim());
                i.putExtra("destination",actvdestination.getText().toString().split("-")[0].trim());
                i.putExtra("sourcecode",actvsource.getText().toString().split("-")[1].trim());
                i.putExtra("destinationcode",actvdestination.getText().toString().split("-")[1].trim());

                startActivity(i);
            }
        });

      new GetStation().execute();

       new GetTrain().execute();


    }


             @Override
             public boolean onPrepareOptionsMenu(Menu menu) {
                 mSearchAction = menu.findItem(R.id.action_search);
                 return super.onPrepareOptionsMenu(menu);
             }

             @Override
             public boolean onCreateOptionsMenu(Menu menu) {
                 // Inflate the menu; this adds items to the action bar if it is present.
                 getMenuInflater().inflate(R.menu.home, menu);
                 return true;
             }

             @Override
             public boolean onOptionsItemSelected(MenuItem item) {
                 // Handle action bar item clicks here. The action bar will
                 // automatically handle clicks on the Home/Up button, so long
                 // as you specify a parent activity in AndroidManifest.xml.
                 int id = item.getItemId();

                 switch (id) {


                     case R.id.action_search:
                         handleMenuSearch();

                         return true;
                 }

                 return super.onOptionsItemSelected(item);
             }

             protected void handleMenuSearch(){
                 ActionBar action = getSupportActionBar(); //get the actionbar

                 if(isSearchOpened){ //test if the search is open

                     action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
                     action.setDisplayShowTitleEnabled(true); //show the title in the action bar

                     //hides the keyboard
                     InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                     imm.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);

                     //add the search icon in the action bar
                     mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_search));

                     isSearchOpened = false;
                 } else { //open the search entry

                     action.setDisplayShowCustomEnabled(true); //enable it to display a
                     // custom view in the action bar.
                     action.setCustomView(R.layout.search_bar);//add the custom view
                     action.setDisplayShowTitleEnabled(false); //hide the title


                     edtSearch = (AutoCompleteTextView)action.getCustomView().findViewById(R.id.edtSearch); //the text editor
                     edtSearch.setDropDownWidth(getWindow().getWindowManager().getDefaultDisplay().getWidth());


                  ArrayAdapter<String>   adapter=new ArrayAdapter<String>(HomeActivity.this,R.layout.dropdown_layout,R.id.textView,liststringtrain);

                     edtSearch.setAdapter(adapter);


                     edtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         @Override
                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                           Intent j=new Intent(HomeActivity.this,ScrollingActivity.class);



                            j.putExtra("trainno", edtSearch.getText().toString().split("-")[0].trim());
                            startActivity(j);

                          /*   Intent j = new Intent(MainActivity.this, DetailsActivity.class);
                             PinCode product = listcollection.get(position);

                             j.putExtra("taluk", product.getTaluk());
                             j.putExtra("pin", product.getPin());
                             j.putExtra("circle", product.getCircle());
                             j.putExtra("deliverystatus", product.getDeliverystatus());
                             j.putExtra("districtname", product.getDistrictname());
                             j.putExtra("divisionname", product.getDivisionname());
                             j.putExtra("relatedheadoffice", product.getHeadoffice());

                             j.putExtra("officetype", product.getOfficetype());
                             j.putExtra("relatedsuboffice", product.getRelatedsuboffice());
                             j.putExtra("statename", product.getStatename());
                             j.putExtra("telephone", product.getTelephone());
                             j.putExtra("officename", product.getOfficename());
                             j.putExtra("regionname", product.getRegionname());

                             startActivity(j);

                             */
                         }
                     });

                     //this is a listener to do a search when the user clicks on search button
                     edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                         @Override
                         public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                             if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                                 //doSearch();
                                 return true;
                             }
                             return false;
                         }
                     });


                     edtSearch.requestFocus();

                     //open the keyboard focused in the edtSearch
                     InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                     imm.showSoftInput(edtSearch, InputMethodManager.SHOW_IMPLICIT);


                     //add the close icon
                     mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_action));

                     isSearchOpened = true;
                 }


             }


             class GetStation extends AsyncTask<Void,Void,Void>
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
                     ArrayAdapter<String> stationadapter=new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,liststringstation);
                     actvsource.setAdapter(stationadapter);
                     actvdestination.setAdapter(stationadapter);
                 }

                 @Override
                 protected Void doInBackground(Void... params) {


                  String resultstation =   Connectivity.makeServiceCall(UrlCreator.geturl(2));

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


                     String resulttrain =   Connectivity.makeServiceCall(UrlCreator.geturl(3));

                     try {
                         parsingtrain(resulttrain);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                     return null;
                 }

                 private void parsingtrain(String resulttrain) throws JSONException {

                     JSONObject jo=new JSONObject(resulttrain);
                     JSONArray jarray = jo.getJSONArray("trains");

                     liststationname=new ArrayList<Train>();
                     liststringtrain=new ArrayList<String>();

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
