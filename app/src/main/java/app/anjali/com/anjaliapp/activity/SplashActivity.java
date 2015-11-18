package app.anjali.com.anjaliapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.anjali.com.anjaliapp.R;

public class SplashActivity extends AppCompatActivity {

    Handler handle=new Handler();

    Runnable r=new Runnable()
    {
        public void run()
        {
            Intent i=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


switchtonext(2000);

    }



    void switchtonext(int interval)
    {

        handle.postDelayed(r,interval);





    }

}
