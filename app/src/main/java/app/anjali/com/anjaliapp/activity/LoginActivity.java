package app.anjali.com.anjaliapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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

import java.util.ArrayList;
import java.util.List;

import app.anjali.com.anjaliapp.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{


    EditText etemail,etpassword;
    Button blogin;
    TextView tvlinkregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etemail=(EditText)findViewById(R.id.email);
        etpassword=(EditText)findViewById(R.id.epassword);
        blogin=(Button)findViewById(R.id.blogin);
        tvlinkregister=(TextView)findViewById(R.id.tvregisterlink);

        blogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });


        tvlinkregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        



    }


}

