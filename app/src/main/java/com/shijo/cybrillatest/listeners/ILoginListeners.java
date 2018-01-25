package com.shijo.cybrillatest.listeners;

/**
 * Created by shijo on 24/01/2018.
 */

public interface ILoginListeners {
    public void onSuccess();
    public void onError(String message);
    public void onShowProgressDialog();
    public void onRemoveProgressDialog();
}
