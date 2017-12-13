package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hossam.sambaking.R;
import com.hossam.sambaking.adapters.RecipeStepsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.hossam.sambaking.activities.MainActivity.isTwoPane;
import static com.hossam.sambaking.activities.MainActivity.recipe;


public class RecipiesDetailsFragment extends Fragment {
    @BindView(R.id.steps_recycle_view)
    RecyclerView steps_recycle_view;
    @BindView(R.id.ingredient_cv)
    CardView ingredient_cv;
    Bundle bundle;
    Unbinder unbinder;
    RecipeStepsAdapter recipeStepsAdapter;

    public RecipiesDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment when activity is re-initialized
        setRetainInstance(true);

    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

    @OnClick({R.id.ingredient_cv})
    void clic(View view) {
        if (view.getId() == R.id.ingredient_cv) {
//            recipe = bundle.getParcelable("RecipeDetails");
            bundle.putParcelable("RecipeDetails", recipe);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            RecipeIngredientsFragment recipeIngredientsFragment = new RecipeIngredientsFragment();
            recipeIngredientsFragment.setArguments(bundle);
            if (isTwoPane)
                fragmentTransaction.replace(R.id.fragment_container_details, recipeIngredientsFragment).addToBackStack(null);
            else
                fragmentTransaction.replace(R.id.fragment_container, recipeIngredientsFragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipies_details, container, false);

        unbinder = ButterKnife.bind(this, view);
        steps_recycle_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        steps_recycle_view.setLayoutManager(linearLayoutManager);

        if (getArguments() != null) {
            bundle = getArguments();
            recipe = bundle.getParcelable("RecipeDetails");
            recipeStepsAdapter = new RecipeStepsAdapter(getActivity(), recipe.getSteps());
            steps_recycle_view.setAdapter(recipeStepsAdapter);
        }

        return view;
    }

}
