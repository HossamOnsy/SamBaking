package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.hossam.sambaking.R;
import com.hossam.sambaking.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeStepsFragment extends Fragment {


    public RecipeStepsFragment() {
        // Required empty public constructor
    }

//    @BindView(R.id.step_media_player)
//    View step_media_player;
    @BindView(R.id.iv_step)
    ImageView iv_step;
    @BindView(R.id.long_description_tv)
    TextView long_description_tv;
    @BindView(R.id.video_view)
    SimpleExoPlayerView video_view;
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
            try {
                Uri uri = Uri.parse(step.getVideoURL());
                initializePlayer(uri);
            }catch (Exception e){

            }
        }


        return view;
    }

    private void initializePlayer(Uri uri) {
       SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
        video_view.setPlayer(player);

//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow, playbackPosition);
    }
    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }
}
