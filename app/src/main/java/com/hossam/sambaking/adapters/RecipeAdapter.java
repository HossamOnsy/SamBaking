package com.hossam.sambaking.adapters;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hossam.sambaking.BR;
import com.hossam.sambaking.R;
import com.hossam.sambaking.activities.RecipeDetailsActivity;
import com.hossam.sambaking.databinding.RecipeCardListItemBinding;
import com.hossam.sambaking.models.Recipe;

import java.util.ArrayList;

/**
 * Created by hossam on 24/11/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    ArrayList<Recipe> recipes;
    Activity activity;

    public RecipeAdapter(Activity activity, ArrayList<Recipe> recipes) {
        this.recipes = recipes;
        this.activity = activity;
    }

    public RecipeAdapter() {
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecipeCardListItemBinding recipeCardListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext())
                        , R.layout.recipe_card_list_item
                        , parent, false);

        return new ViewHolder(recipeCardListItemBinding);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
        final Recipe item = recipes.get(position);

        holder.bind(item);
        ImageView imageView = holder.binding.getRoot().findViewById(R.id.recipe_iv);
        if (!(item.getImage().equals("") && item.getImage() != null))
            Glide.with(activity).load(item.getImage()).into(imageView);
        else
            imageView.setVisibility(View.GONE);
        holder.binding.getRoot().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(activity, RecipeDetailsActivity.class)
                                .putExtra("RecipeDetails", item));
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RecipeCardListItemBinding binding;

        ViewHolder(RecipeCardListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.recipe, obj);
            binding.executePendingBindings();
        }
    }
}
