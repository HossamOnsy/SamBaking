package com.hossam.sambaking.activities;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by hossam on 06/12/17.
 */

public class MyWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}