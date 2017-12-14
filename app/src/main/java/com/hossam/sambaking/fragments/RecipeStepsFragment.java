package com.hossam.sambaking.fragments;

import android.app.Fragment;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.hossam.sambaking.R;
import com.hossam.sambaking.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeStepsFragment extends Fragment {

    public static boolean activityCreated = false;
    Step step;
    Bundle bundle = null;
    boolean isPlayWhenReady =true;
    //    @BindView(R.id.step_media_player)
//    View step_media_player;
//    @BindView(R.id.iv_step)
//    ImageView iv_step;
    @BindView(R.id.long_description_tv)
    TextView long_description_tv;
    @BindView(R.id.video_view)
    SimpleExoPlayerView video_view;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    SimpleExoPlayer player;

    Unbinder unbinder;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment when activity is re-initialized
        setRetainInstance(true);
    }

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
        video_view = ButterKnife.findById(view, R.id.video_view);

        if (savedInstanceState != null)
            bundle = savedInstanceState;

        if (getArguments() != null) {
            step = getArguments().getParcelable("RecipeStepDetails");
            long_description_tv.setText(step != null ? step.getDescription() : null);
            progress_bar.setVisibility(View.VISIBLE);
//            if(step != null && step.getThumbnailURL().equals(""))
//            Glide.with(this).load(step.getThumbnailURL() ).into(iv_step);
//            else
//                iv_step.setVisibility(View.GONE);
            try {
                Log.v("DataNotRecieved", "DataNotRecieved --- IF of Re ------> " + step.getVideoURL());
                Uri uri = Uri.parse(step != null ? step.getVideoURL() : null);
                Log.v("DataNotRecieved", "DataNotRecieved --- IF of Re ------> " + uri);
                initializePlayer(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activityCreated = true;

        Log.v("StatusOfFragment", "onStart ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.release();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.release();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("StatusOfFragment", "onStart ");
//        if (!isTwoPane) {
//            if (getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE && activityCreated) {
////                getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove title bar
////                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //Remove notification bar
////                video_view.s
//                video_view.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
//
////                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
////                        LinearLayout.LayoutParams.MATCH_PARENT,
////                        LinearLayout.LayoutParams.MATCH_PARENT);
////
////
////                video_view.setLayoutParams(lp);
//            }else
//                video_view.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);


//        }
    }

    private void initializePlayer(Uri uri) {
        if (uri != null) {
//            SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
//                    new DefaultRenderersFactory(getActivity().getApplicationContext()),
//                    new DefaultTrackSelector(), new DefaultLoadControl());

            MediaSource mediaSource = buildMediaSource(uri);
            if (player == null) {
                player = ExoPlayerFactory.newSimpleInstance(
                        new DefaultRenderersFactory(getActivity().getApplicationContext()),
                        new DefaultTrackSelector(), new DefaultLoadControl());

            }
            if(bundle!=null)
                isPlayWhenReady = bundle.getBoolean("playstate");
            player.setPlayWhenReady(isPlayWhenReady);
            player.prepare(mediaSource, true, false);
            player.setVideoDebugListener(new VideoRendererEventListener() {
                @Override
                public void onVideoEnabled(DecoderCounters counters) {
                    progress_bar.setVisibility(View.GONE);
                }

                @Override
                public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

                }

                @Override
                public void onVideoInputFormatChanged(Format format) {

                }

                @Override
                public void onDroppedFrames(int count, long elapsedMs) {

                }

                @Override
                public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

                }

                @Override
                public void onRenderedFirstFrame(Surface surface) {

                }

                @Override
                public void onVideoDisabled(DecoderCounters counters) {

                }
            });

            video_view.setPlayer(player);
            if (bundle != null) {
                player.seekTo(bundle.getLong("timer"));

            }

        }

//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow, playbackPosition);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(player!=null) {
            outState.putLong("timer", player.getCurrentPosition());
            boolean isPlayWhenReady = player.getPlayWhenReady();
            outState.putBoolean("playstate", isPlayWhenReady);
        }
    }


    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    public int getScreenOrientation() {
        Display getOrient = getActivity().getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if (getOrient.getWidth() == getOrient.getHeight()) {
            orientation = Configuration.ORIENTATION_SQUARE;
        } else {
            if (getOrient.getWidth() < getOrient.getHeight()) {
                orientation = Configuration.ORIENTATION_PORTRAIT;
            } else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }
}
