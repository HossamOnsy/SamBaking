package com.hossam.sambaking.activities;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by hossam on 06/12/17.
 */

public class ListViewRemoveViewsService extends RemoteViewsService {

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);

    }

}