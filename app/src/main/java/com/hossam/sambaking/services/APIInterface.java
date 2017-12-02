package com.hossam.sambaking.services;

import com.hossam.sambaking.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hossam.Onsy on 6/18/2017.
 */

public interface APIInterface {


    // Products Apis Part
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Recipe>> getRecipe();


}