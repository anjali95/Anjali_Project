package app.anjali.com.anjaliapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.anjali.com.anjaliapp.R;

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

            }
        });
    }
}
