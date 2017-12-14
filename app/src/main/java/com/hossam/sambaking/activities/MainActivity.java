package com.hossam.sambaking.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.hossam.sambaking.R;
import com.hossam.sambaking.Utils;
import com.hossam.sambaking.adapters.RecipeAdapter;
import com.hossam.sambaking.controller.RecipesController;
import com.hossam.sambaking.models.Recipe;
import com.hossam.sambaking.services.ConnectivityReceiver;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    Recipe recipe=null;
    public static boolean isTwoPane;

    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipes_recycler_view;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    Unbinder unbinder;
    RecipeAdapter recipeAdapter;

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        unbinder = ButterKnife.bind(this);

        isTwoPane = getResources().getBoolean(R.bool.isTablet);

        recipes_recycler_view.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, numberOfColumns());
        recipes_recycler_view.setLayoutManager(gridLayoutManager);

        checkConnection();

    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        onNetworkConnectionChanged(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    private void getRecipes() {
        RecipesController.getRecipe(MainActivity.this, new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                if (response.code() >= 200 && response.code() < 300 && response.isSuccessful()) {
                    if (recipe == null) {
                        if (response.body().size() > 0) {
                            recipe = response.body().get(0);
                        }
                    }

                    Utils.saveObjectInPreference(MainActivity.this, "Recipe", recipe);
                    recipeAdapter = new RecipeAdapter(MainActivity.this, response.body());
                    recipes_recycler_view.setAdapter(recipeAdapter);

                } else
                    prepareSnack("Please Try Again Later", "DISMISS");
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                prepareSnack("Internet not Connected", "RETRY");
            }
        });
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();

    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            prepareSnack("Internet is now connected ... Thank You !!! ", "DONE");
            getRecipes();
        } else {
            prepareSnack("Internet not Connected", "RETRY");
        }

    }

    public void prepareSnack(String Message, String ButtonMessage) {

        snackbar = Snackbar.make(coordinator, Message, Snackbar.LENGTH_LONG)
                .setAction(ButtonMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getRecipes();
                        snackbar.dismiss();
                    }
                });

        snackbar.show();
    }
}
