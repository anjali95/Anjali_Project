package app.anjali.com.anjaliapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.anjali.com.anjaliapp.MapsActivity;
import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.adapter.InfoAdapter;
import app.anjali.com.anjaliapp.model.Train;
import app.anjali.com.anjaliapp.model.TrainSchedule;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.UrlCreator;

public class ScrollingActivity extends AppCompatActivity {

    TextView tvtrainname,tvfrom,tvtrainno,tvto;


    RecyclerView recyclerView;


    List<TrainSchedule> tslist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvtrainname=(TextView)findViewById(R.id.tvname);
        tvtrainno=(TextView)findViewById(R.id.tvtrainno);
        tvfrom=(TextView)findViewById(R.id.tvfrom);
        tvto=(TextView)findViewById(R.id.tvto);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tslist!=null) {
                    Intent j = new Intent(ScrollingActivity.this, MapsActivity.class);

                    String[] location=new String[tslist.size()];

                    for(int i=0;i<tslist.size();i++)
                    {
                        location[i]=tslist.get(i).getStationname();
                    }

                    j.putExtra("trains",location);

                    startActivity(j);


                }


            }
        });

        new GetTrainInfo().execute();
    }


    class GetTrainInfo extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // ploading.setVisibility(View.VISIBLE);
           // rlcontent.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        ////    ploading.setVisibility(View.INVISIBLE);
         ////   rlcontent.setVisibility(View.VISIBLE);


            tvtrainno.setText(tslist.get(0).getTrainno());
            tvtrainname.setText(tslist.get(0).getTrainname());
            tvfrom.setText(tslist.get(0).getSourcestationname()+" - "+tslist.get(0).getSourcestationcode());
            tvto.setText(tslist.get(0).getDestinationStationname()+" - "+tslist.get(0).getDestinationstationcode());

            InfoAdapter ia=new InfoAdapter(ScrollingActivity.this,tslist);
            recyclerView.setAdapter(ia);

        }

        @Override
        protected Void doInBackground(Void... params) {


            String resulttraininfo =   Connectivity.makeServiceCall(UrlCreator.geturl(4)+"?trainno="+getIntent().getExtras().getString("trainno"));

            try {
                parsingtraininfo(resulttraininfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void parsingtraininfo(String resulttraininfo) throws JSONException {

            JSONObject jo=new JSONObject(resulttraininfo);
            JSONArray jarray = jo.getJSONArray("info");

            tslist=new ArrayList<TrainSchedule>();




            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jobject=  jarray.getJSONObject(i);
                String trainno =      jobject.getString("Train_No");
                String  trainname =     jobject.getString("train_Name");
                String  stationcode =     jobject.getString("station_Code");
                String  stationname =     jobject.getString("Station_Name");
                String  arrivaltime =     jobject.getString("Arrival_time");
                String  departuretime =     jobject.getString("Departure_time");
                String  distance =     jobject.getString("Distance");
                String  sourcestationcode =     jobject.getString("Source_Station_Code");
                String  sourcestationname =     jobject.getString("source_Station_Name");
                String  destinationstationcode =     jobject.getString("Destination_station_Code");
                String  destinationstationname =     jobject.getString("Destination_Station_Name");




            TrainSchedule ts=new TrainSchedule(trainno,trainname,stationcode,stationname,arrivaltime,departuretime,distance,sourcestationcode,sourcestationname,destinationstationcode,destinationstationname);

                tslist.add(ts);

            }


        }
    }
}
