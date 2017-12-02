package com.hossam.sambaking.controller;

import android.content.Context;

import com.hossam.sambaking.models.Recipe;
import com.hossam.sambaking.services.APIClient;
import com.hossam.sambaking.services.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hossam on 02/12/17.
 */

public class RecipesController {

    public static void getRecipe(Context context, Callback<ArrayList<Recipe>> recipeCallback) {
        APIInterface apiInterface = APIClient.getClient(context).create(APIInterface.class);
        Call<ArrayList<Recipe>> recipeCall = apiInterface.getRecipe();
        recipeCall.enqueue(recipeCallback);

    }
}
