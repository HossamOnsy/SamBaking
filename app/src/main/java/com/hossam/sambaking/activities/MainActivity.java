package com.hossam.sambaking.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.hossam.sambaking.R;
import com.hossam.sambaking.adapters.RecipeAdapter;
import com.hossam.sambaking.controller.RecipesController;
import com.hossam.sambaking.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipes_recycler_view;

    Unbinder unbinder;
    RecipeAdapter recipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        unbinder = ButterKnife.bind(this);


        recipes_recycler_view.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,numberOfColumns());
        recipes_recycler_view.setLayoutManager(gridLayoutManager);

        getRecipes();
    }

    private void getRecipes() {
        RecipesController.getRecipe(MainActivity.this, new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                if(response.code()>=200&&response.code()<300&&response.isSuccessful()){
                    Log.v("DataNotRecieved","DataNotRecieved --- IF of Response");
                    for(Recipe recipe : response.body())
                        Log.v("recipename",recipe.getName());

                    recipeAdapter=new RecipeAdapter(MainActivity.this,response.body());
                    recipes_recycler_view.setAdapter(recipeAdapter);

                    Log.v("DataNotRecieved","DataNotRecieved --- IF of Response + Count " +    recipes_recycler_view.getChildCount());
                }else
                    Log.v("DataNotRecieved","DataNotRecieved --- Else of Response");
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.v("DataNotRecieved","DataNotRecieved --- Failure ------> " + t.getLocalizedMessage());
                Log.v("DataNotRecieved","DataNotRecieved --- Failure ------> " + t.getMessage());
                Log.v("DataNotRecieved","DataNotRecieved --- Failure ------> " + t.getCause());
            }
        });
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 1;
        return nColumns;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null)
            unbinder.unbind();

    }


}
