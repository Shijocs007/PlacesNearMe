package com.shijo.cybrillatest.common;

import android.app.ProgressDialog;
import android.content.Context;

import com.shijo.cybrillatest.R;

/**
 * Created by shijo on 24/01/2018.
 */

public class Utils {
    private static final Utils ourInstance = new Utils();

    ProgressDialog progressDialog;

    public static Utils getInstance() {
        return ourInstance;
    }

    public void showProgress(Context context){

        progressDialog = new ProgressDialog(context, R.style.TransparentProgress);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();


    }
    public void dismissProgress(){
        if (progressDialog !=null){
            progressDialog.dismiss();
        }
    }
}
