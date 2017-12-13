package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hossam.sambaking.R;
import com.hossam.sambaking.activities.SimpleAppWidgetProvider;
import com.hossam.sambaking.adapters.RecipeIngredientsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.hossam.sambaking.activities.MainActivity.recipe;

public class RecipeIngredientsFragment extends Fragment {

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredients_recycler_view;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    Snackbar snackbar;
    Unbinder unbinder;

    public RecipeIngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("check", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view =
//        RecipiesDetailsFragment_ViewBinding recipiesDetailsFragment_viewBinding
//                 = new RecipiesDetailsFragment_ViewBinding(inflater.inflate(R.layout.fragment_recipe_ingredients, container, false));
        View view = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);


        unbinder = ButterKnife.bind(this, view);

            if (getArguments() != null) {
                ingredients_recycler_view.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                ingredients_recycler_view.setLayoutManager(linearLayoutManager);
                Bundle bundle = getArguments();
                recipe = bundle.getParcelable("RecipeDetails");
                RecipeIngredientsAdapter recipeIngredientsAdapter = null;
                recipeIngredientsAdapter = new RecipeIngredientsAdapter(getActivity(), recipe.getIngredients());
                ingredients_recycler_view.setAdapter(recipeIngredientsAdapter);

                Log.v( "onBindViewHolder","savedInstanceState -------> "  );
                SimpleAppWidgetProvider.sendRefreshBroadcast(getActivity());
//             getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                         this will send the broadcast to update the appwidget
//                        SimpleAppWidgetProvider.sendRefreshBroadcast(getActivity());
//                    }
//                });

            } else {
                snackbar = Snackbar.make(coordinator, "No Ingredients Available", Snackbar.LENGTH_LONG)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });

                snackbar.show();
            }


        return view;
    }


}
