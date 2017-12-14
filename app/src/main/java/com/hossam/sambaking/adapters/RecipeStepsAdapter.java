package com.hossam.sambaking.adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hossam.sambaking.BR;
import com.hossam.sambaking.R;
import com.hossam.sambaking.databinding.RecipeStepCardListItemBinding;
import com.hossam.sambaking.fragments.RecipeStepsFragment;
import com.hossam.sambaking.models.Step;

import java.util.List;

import static com.hossam.sambaking.activities.MainActivity.isTwoPane;

/**
 * Created by hossam on 24/11/17.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    List<Step> steps;
    Activity activity;

    public RecipeStepsAdapter(Activity activity, List<Step> steps) {
        this.steps = steps;
        this.activity = activity;
    }

    public RecipeStepsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecipeStepCardListItemBinding recipeStepCardListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext())
                        , R.layout.recipe_step_card_list_item
                        , parent, false);

        return new ViewHolder(recipeStepCardListItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Step item = steps.get(position);
        holder.bind(item);
        ImageView imageView = holder.binding.getRoot().findViewById(R.id.step_iv);
        if(!(item.getThumbnailURL().equals("")&&!item.getThumbnailURL().contains(".mp4")&&item.getThumbnailURL()!=null))
            Glide.with(activity).load(item.getThumbnailURL()).into(imageView);
        else
            imageView.setVisibility(View.GONE);

        holder.binding.getRoot().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("RecipeStepDetails",item);
                        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
                        RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
                        recipeStepsFragment.setArguments(bundle);
                        if(isTwoPane)
                            fragmentTransaction.replace(R.id.fragment_container_details, recipeStepsFragment,"recipeStepsFragment").addToBackStack(null);
                        else
                            fragmentTransaction.replace(R.id.fragment_container, recipeStepsFragment,"recipeStepsFragment").addToBackStack(null);

                        fragmentTransaction.commit();
//                        Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return (steps != null) ?steps.size(): 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RecipeStepCardListItemBinding binding;

        ViewHolder(RecipeStepCardListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.step, obj);
            binding.executePendingBindings();
        }
    }
}
