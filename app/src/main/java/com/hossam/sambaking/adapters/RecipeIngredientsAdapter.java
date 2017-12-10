package com.hossam.sambaking.adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hossam.sambaking.BR;
import com.hossam.sambaking.R;
import com.hossam.sambaking.databinding.RecipeIngredientCardListItemBinding;
import com.hossam.sambaking.models.Ingredient;

import java.util.List;

/**
 * Created by hossam on 24/11/17.
 */

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.ViewHolder> {

    List<Ingredient> ingredients;
    Activity activity;

    public RecipeIngredientsAdapter(Activity activity, List<Ingredient> Ingredient) {
        this.ingredients = Ingredient;
        this.activity = activity;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecipeIngredientCardListItemBinding recipeIngredientCardListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                        , R.layout.recipe_ingredient_card_list_item
                        , parent, false);

        return new ViewHolder(recipeIngredientCardListItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Ingredient item = ingredients.get(position);
        holder.bind(item);
        Log.v( "onBindViewHolder","position -------> " + position );
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RecipeIngredientCardListItemBinding binding;

        ViewHolder(RecipeIngredientCardListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.ingredient, obj);
            binding.executePendingBindings();
        }
    }
}
