package com.shijo.cybrillatest.listeners;

/**
 * Created by shijo on 24/01/2018.
 */

public interface ISignupListener {
    public void onSuccess();
    public void onError(String message);
    public void onShowProgressDialog();
    public void onRemoveProgressDialog();
}
