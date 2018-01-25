package com.shijo.cybrillatest.dataservice;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shijo.cybrillatest.listeners.ILoginListeners;
import com.shijo.cybrillatest.listeners.ISignupListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shijo on 24/01/2018.
 */

public class ServiceManager {
    private static final ServiceManager ourInstance = new ServiceManager();

    public static ServiceManager getInstance() {
        return ourInstance;
    }

    public void loginUser(String email, String password, final ILoginListeners iLoginListeners) {

        if(email.equals("") || password.equals("") || !email.contains("@")){
            iLoginListeners.onError("Please enter a valid email and password.");
        }else {
            iLoginListeners.onShowProgressDialog();
            FirebaseAuth mAuth  =   FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            iLoginListeners.onRemoveProgressDialog();

                            if (task.isSuccessful()) {
                                iLoginListeners.onSuccess();
                            }else {
                                String error    =   task.getException().getMessage();
                                iLoginListeners.onError(error);
                            }

                        }
                    });
        }

    }

    public void signupUser(String email, String password1, String password2, final ISignupListener iSignupListener) {

        if(email.equals("") || password1.equals("") || !email.contains("@")){
            iSignupListener.onError("Please enter a valid email and password.");
        }else if(!password1.equals(password2)) {
            iSignupListener.onError("Password deosnt match.");
        }else {
            iSignupListener.onShowProgressDialog();
            FirebaseAuth mAuth  =   FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            iSignupListener.onRemoveProgressDialog();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                iSignupListener.onSuccess();

                            } else {
                                // If sign in fails, display a message to the user.
                                String error    =   task.getException().getMessage();
                                iSignupListener.onError(error);
                            }
                        }
                    });

        }
    }

    public String readUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
