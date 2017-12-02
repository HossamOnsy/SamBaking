package com.hossam.sambaking.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by hossam on 02/12/17.
 */

public class RecipesList implements Parcelable {
    public RecipesList(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    ArrayList<Recipe> recipes = new ArrayList<>();

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.recipes);
    }

    protected RecipesList(Parcel in) {
        this.recipes = in.createTypedArrayList(Recipe.CREATOR);
    }

    public static final Parcelable.Creator<RecipesList> CREATOR = new Parcelable.Creator<RecipesList>() {
        @Override
        public RecipesList createFromParcel(Parcel source) {
            return new RecipesList(source);
        }

        @Override
        public RecipesList[] newArray(int size) {
            return new RecipesList[size];
        }
    };
}
