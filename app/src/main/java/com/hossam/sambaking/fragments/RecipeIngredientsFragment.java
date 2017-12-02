package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hossam.sambaking.R;
import com.hossam.sambaking.adapters.RecipeIngredientsAdapter;
import com.hossam.sambaking.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeIngredientsFragment extends Fragment {

    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipes_recycler_view;

    public RecipeIngredientsFragment() {
        // Required empty public constructor
    }
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view =
//        RecipiesDetailsFragment_ViewBinding recipiesDetailsFragment_viewBinding
//                 = new RecipiesDetailsFragment_ViewBinding(inflater.inflate(R.layout.fragment_recipe_ingredients, container, false));
        View view = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);



        if (getArguments()!=null){
            unbinder= ButterKnife.bind(this,view);
            recipes_recycler_view.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recipes_recycler_view.setLayoutManager(linearLayoutManager);
            Bundle bundle = getArguments();
            Recipe recipe = bundle.getParcelable("RecipeDetails");
            RecipeIngredientsAdapter recipeIngredientsAdapter= null;
            if (recipe != null) {
                recipeIngredientsAdapter = new RecipeIngredientsAdapter(getActivity(),recipe.getIngredients());
                recipes_recycler_view.setAdapter(recipeIngredientsAdapter);
            }else
                Toast.makeText(getActivity(), "Emtpy", Toast.LENGTH_SHORT).show();
        }


        return view;
    }


}
