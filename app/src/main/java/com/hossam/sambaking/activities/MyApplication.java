package com.hossam.sambaking.activities;

import android.app.Application;

import com.hossam.sambaking.services.ConnectivityReceiver;

/**
 * Created by hossamonsy on 10/12/17.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}