package app.anjali.com.anjaliapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.anjali.com.anjaliapp.activity.EnquiryActivity;
import app.anjali.com.anjaliapp.adapter.EnquiryAdapter;
import app.anjali.com.anjaliapp.model.TrainSchedule;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.UrlCreator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Loc> traininfo;
   List<String> trains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        traininfo=new ArrayList<Loc>();
        trains=new ArrayList<String>();


       String[] st = getIntent().getExtras().getStringArray("trains");

        for(int i=0;i<st.length;i++)
        {
            trains.add(st[i]);


        }

        new GetTrainGeocode().execute();
    }


    class Loc
    {
        public String trainstation;
        public double latitude;
        public double longitude;
        public String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Loc(String trainstation,double latitude, double longitude,String address) {
            this.trainstation=trainstation;
            this.latitude = latitude;
            this.longitude = longitude;
            this.address=address;
        }

        public String getTrainstation() {
            return trainstation;
        }

        public void setTrainstation(String trainstation) {
            this.trainstation = trainstation;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }


    class GetTrainGeocode extends AsyncTask<Void,Void,Void>
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


         for(int i=0;i<traininfo.size();i++)
         {
             if(i==0) {
                 mMap.addMarker(new MarkerOptions().position(new LatLng(traininfo.get(i).latitude,traininfo.get(i).longitude)).title(trains.get(i)).snippet(traininfo.get(i).address));
                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(traininfo.get(i).latitude,traininfo.get(i).longitude),15));
             }
             else
             {
                 mMap.addMarker(new MarkerOptions().position(new LatLng(traininfo.get(i).latitude,traininfo.get(i).longitude)).title(trains.get(i)).snippet(traininfo.get(i).address));

             }

         }

            for(int i=0;i<traininfo.size()-1;i++)
            {
                mMap.addPolyline(new PolylineOptions().add(new LatLng(traininfo.get(i).latitude,traininfo.get(i).longitude),new LatLng(traininfo.get(i+1).latitude,traininfo.get(i+1).longitude)).color(Color.BLUE));
            }


        }

        @Override
        protected Void doInBackground(Void... params) {



            for(int i=0;i<trains.size();i++) {


                String resulttraingeo = Connectivity.makeServiceCall(UrlCreator.geturl(6) + trains.get(i).replace(" ", "%20"));


                try {
                    parsingtraingeo(resulttraingeo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        private void parsingtraingeo(String resulttraingeo) throws JSONException {

            JSONObject jo=new JSONObject(resulttraingeo);
            JSONObject j = jo.getJSONArray("results").getJSONObject(0);
          String address=  j.getString("formatted_address");
            JSONObject loc = j.getJSONObject("geometry").getJSONObject("location");
               String lat=loc.getString("lat");
            String lng=loc.getString("lng");




Loc l=new Loc(null,Double.parseDouble(lat),Double.parseDouble(lng),address);
            traininfo.add(l);





        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}
