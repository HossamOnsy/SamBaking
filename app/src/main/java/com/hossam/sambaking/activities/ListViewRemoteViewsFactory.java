package com.hossam.sambaking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.hossam.sambaking.R;
import com.hossam.sambaking.models.Ingredient;

import static com.hossam.sambaking.activities.MainActivity.recipe;


/**
 * Created by hossam on 06/12/17.
 */

public class ListViewRemoteViewsFactory implements RemoteViewsFactory {


    private Context mContext;

//            private ArrayList<String> records;


    public ListViewRemoteViewsFactory(Context context, Intent intent) {

        mContext = context;

    }

    // Initialize the data set.
    @Override
    public void onCreate() {

        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,

        // for example downloading or creating content etc, should be deferred to onDataSetChanged()

        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

//                records=new ArrayList/<String>();

    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in

    // combination with the app widget item XML file to construct a RemoteViews object.

    @Override
    public RemoteViews getViewAt(int position) {

        // position will always range from 0 to getCount() - 1.

        // Construct a RemoteViews item based on the app widget item XML file, and set the

        // text based on the position.
        final RemoteViews remoteView = new RemoteViews(
                mContext.getPackageName(), R.layout.recipe_ingredient_widget_list_item);
        Ingredient listItem = recipe.getIngredients().get(position);
        remoteView.setTextViewText(R.id.quantity_tv, String.valueOf(listItem.getQuantity()));
        remoteView.setTextViewText(R.id.measure_tv, listItem.getMeasure());
        remoteView.setTextViewText(R.id.ingredient_tv, listItem.getIngredient());


        // feed row

        // end feed row

        // Next, set a fill-intent, which will be used to fill in the pending intent template

        // that is set on the collection view in ListViewWidgetProvider.

        Bundle extras = new Bundle();

        extras.putInt(SimpleAppWidgetProvider.EXTRA_ITEM, position);

//                Intent fillInIntent = new Intent();
//
//                fillInIntent.putExtra("homescreen_meeting",listItem);
//
//                fillInIntent.putExtras(extras);

        // Make it possible to distinguish the individual on-click

        // action of a given item

//                rv.setOnClickFillInIntent(R.id.item_layout, fillInIntent);

        // Return the RemoteViews object.

        return remoteView;

    }

    @Override
    public int getCount() {

        Log.e("size=", recipe.getIngredients().size() + "");

        return recipe.getIngredients().size();

    }

    @Override
    public void onDataSetChanged() {

        // Fetching JSON data from server and add them to records arraylist


    }

    @Override
    public int getViewTypeCount() {

        return 1;

    }


    @Override
    public long getItemId(int position) {

        return position;

    }

    @Override
    public void onDestroy() {

//                records.clear();

    }

    @Override
    public boolean hasStableIds() {

        return true;

    }

    @Override
    public RemoteViews getLoadingView() {

        return null;

    }

}