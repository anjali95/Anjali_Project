package app.anjali.com.anjaliapp.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.UrlCreator;

public class RegisterActivity extends AppCompatActivity {

    EditText etemailid,etpassword,etphone;
    Button bregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etemailid=(EditText)findViewById(R.id.email);
        etpassword=(EditText)findViewById(R.id.epassword);
        etphone=(EditText)findViewById(R.id.econtact);

        bregister=(Button)findViewById(R.id.bregister);
        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
new RegisterAttempt().execute();
            }
        });
    }

    class RegisterAttempt extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }

        @Override
        protected Void doInBackground(Void... params) {


            String resultregister =   Connectivity.makeServiceCall(UrlCreator.geturl(1));

            try {
                parsingresult(resultregister);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void parsingresult(String resultregister) throws JSONException {

            JSONObject jo=new JSONObject(resultregister);




        }
    }


}
