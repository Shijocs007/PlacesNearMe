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
import com.shijo.cybrillatest.listeners.ILoginListeners;

public class LoginActivity extends AppCompatActivity implements ILoginListeners {

    TextView emailTxt,passwordTxt,signupLink;
    Button  loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt    =   (TextView) findViewById(R.id.input_email);
        passwordTxt =   (TextView) findViewById(R.id.input_password);
        signupLink  =   (TextView) findViewById(R.id.link_signup);
        loginBtn    =   (Button) findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    =   emailTxt.getText().toString().trim();
                String password =   passwordTxt.getText().toString().trim();

                ServiceManager.getInstance().loginUser(email,password,LoginActivity.this);
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent =   new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signupIntent);
                finish();
            }
        });
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
