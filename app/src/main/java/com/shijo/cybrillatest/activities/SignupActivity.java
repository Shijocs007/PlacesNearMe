package com.shijo.cybrillatest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shijo.cybrillatest.MainActivity;
import com.shijo.cybrillatest.R;
import com.shijo.cybrillatest.common.Utils;
import com.shijo.cybrillatest.dataservice.ServiceManager;
import com.shijo.cybrillatest.listeners.ISignupListener;

public class SignupActivity extends AppCompatActivity implements ISignupListener{

    TextView emailTxt,passwordTxt,confirmTxt,signinLink;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailTxt    =   (TextView) findViewById(R.id.input_email);
        passwordTxt =   (TextView) findViewById(R.id.input_password);
        confirmTxt  =   (TextView) findViewById(R.id.confirm_password);
        signinLink  =   (TextView) findViewById(R.id.link_login);
        signupBtn   =   (Button) findViewById(R.id.btn_signup);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  email       =   emailTxt.getText().toString().trim();
                String  password1   =   passwordTxt.getText().toString().trim();
                String  password2   =   confirmTxt.getText().toString().trim();

                ServiceManager.getInstance().signupUser(email,password1,password2,SignupActivity.this);
            }
        });

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent  =   new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSuccess() {
        Intent mainIntent   =   new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowProgressDialog() {
        Utils.getInstance().showProgress(this);
    }

    @Override
    public void onRemoveProgressDialog() {
        Utils.getInstance().dismissProgress();
    }
}
