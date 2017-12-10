package com.hossam.sambaking.activities;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.hossam.sambaking.R;
import com.hossam.sambaking.fragments.RecipiesDetailsFragment;
import com.hossam.sambaking.models.Recipe;

import butterknife.BindView;

public class RecipeDetailsActivity extends AppCompatActivity {


    Recipe recipe;
    public static boolean isTwoPane;
    Snackbar snackbar;

    @BindView(R.id.fragment_container)
    FrameLayout fragment_container;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

//        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.fragment_container_details);
//        if (fragmentItemDetail != null) {
//            isTwoPane = true;
//        }
        if(savedInstanceState==null) {
            if (getIntent().hasExtra("RecipeDetails")) {
                recipe = getIntent().getParcelableExtra("RecipeDetails");
                Bundle bundle = new Bundle();
                bundle.putParcelable("RecipeDetails", getIntent().getParcelableExtra("RecipeDetails"));
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                RecipiesDetailsFragment recipiesDetailsFragment = new RecipiesDetailsFragment();
                recipiesDetailsFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_container, recipiesDetailsFragment);
                fragmentTransaction.commit();
            } else {
                snackbar = Snackbar.make(fragment_container, "No Ingredients Available", Snackbar.LENGTH_LONG)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        });

                snackbar.show();
            }
        }
//        ActionBar actionBar = getActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if(getFragmentManager().getBackStackEntryCount()>0)
                    getFragmentManager().popBackStack();
                else
                    finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Fragment fragment = getFragmentManager().findFragmentByTag("MY TAG");
        if (fragment != null) {
            getFragmentManager().putFragment(outState, "KEY", fragment);
        }
    }
}
