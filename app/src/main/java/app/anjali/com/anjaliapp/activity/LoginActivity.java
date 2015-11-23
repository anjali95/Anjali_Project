package app.anjali.com.anjaliapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.model.Train;
import app.anjali.com.anjaliapp.network.Connectivity;
import app.anjali.com.anjaliapp.util.UrlCreator;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{


    EditText etemail,etpassword;
    Button blogin;
    TextView tvlinkregister;

    ProgressDialog pdialog;


    String semailid,spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etemail = (EditText) findViewById(R.id.emailid);
        etpassword = (EditText) findViewById(R.id.epassword);
        blogin = (Button) findViewById(R.id.blogin);
        tvlinkregister = (TextView) findViewById(R.id.tvregisterlink);

        blogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                semailid=etemail.getText().toString();
                spassword=etpassword.getText().toString();
                new LoginAttempt().execute();
            }
        });


        tvlinkregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {



               Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    class LoginAttempt extends AsyncTask<Void,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdialog=new ProgressDialog(LoginActivity.this);
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
                Toast.makeText(LoginActivity.this, aVoid, Toast.LENGTH_SHORT).show();;
            }


        }

        @Override
        protected String doInBackground(Void... params) {


            String resultlogin =   Connectivity.makeServiceCall(UrlCreator.geturl(0) + "?emailid=" + semailid + "&password=" + spassword);

            try {
                return    parsingresult(resultlogin);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String parsingresult(String resultlogin) throws JSONException {

            JSONObject jo = new JSONObject(resultlogin);

            int x = jo.getInt("success");

            if (x == 1) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
                return "login successfull";
            } else {
                return "login failed";
            }


        }
    }



}




