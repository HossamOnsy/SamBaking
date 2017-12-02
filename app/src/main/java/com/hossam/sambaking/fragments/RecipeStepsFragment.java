package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hossam.sambaking.R;
import com.hossam.sambaking.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeStepsFragment extends Fragment {


    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.step_media_player)
    View step_media_player;
    @BindView(R.id.iv_step)
    ImageView iv_step;
    @BindView(R.id.long_description_tv)
    TextView long_description_tv;

    Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

//        recipes_recycler_view.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recipes_recycler_view.setLayoutManager(linearLayoutManager);
//
        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Step step = getArguments().getParcelable("RecipeStepDetails");
            long_description_tv.setText(step != null ? step.getDescription() : null);
            if(step != null && step.getThumbnailURL().equals(""))
            Glide.with(this).load(step.getThumbnailURL() ).into(iv_step);
            else
                iv_step.setVisibility(View.GONE);
        }


        return view;
    }


}
