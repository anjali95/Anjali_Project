package app.anjali.com.anjaliapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.UrlCreator;

public class RegisterActivity extends AppCompatActivity {

    EditText etemailid,etpassword,etphone;
    Button bregister;

    String semailid,spassword,sphone;

    ProgressDialog pdialog;


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

                semailid=etemailid.getText().toString();
                spassword=etpassword.getText().toString();
                sphone=etphone.getText().toString();


                new RegisterAttempt().execute();
            }
        });
    }

    class RegisterAttempt extends AsyncTask<Void,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdialog=new ProgressDialog(RegisterActivity.this);
            pdialog.setMessage("Please wait...");
            pdialog.setCancelable(true);
            pdialog.show();

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
pdialog.dismiss();;
if(aVoid!=null)
{
    Toast.makeText(RegisterActivity.this,aVoid,Toast.LENGTH_SHORT).show();;
}


        }

        @Override
        protected String doInBackground(Void... params) {


            String resultregister =   Connectivity.makeServiceCall(UrlCreator.geturl(1) + "?emailid=" + semailid + "&password=" + spassword + "&phone=" + sphone);

            try {
             return    parsingresult(resultregister);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String parsingresult(String resultregister) throws JSONException {

            JSONObject jo = new JSONObject(resultregister);

            int x = jo.getInt("success");

            if (x == 1) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                return "registration successfull";
            } else {
                return "registration failed";
            }


        }
    }


}
