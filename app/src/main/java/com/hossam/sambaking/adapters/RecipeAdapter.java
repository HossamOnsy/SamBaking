package com.hossam.sambaking.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hossam.sambaking.R;

/**
 * Created by hossam on 24/11/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {







    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recipe_card_list_item, parent, false);

        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
//        ViewDataBinding viewDataBinding =holder.getViewDataBinding();
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;
         ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
             this.binding = itemView;
        }
    }
}
