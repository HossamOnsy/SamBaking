package com.hossam.sambaking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hossam.sambaking.R;
import com.hossam.sambaking.fragments.RecipiesDetailsFragment;
import com.hossam.sambaking.models.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {


    Recipe recipe;
    public static boolean isTwoPane;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.fragment_container_details);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }
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
            Toast.makeText(this, "No Recipe Details Available", Toast.LENGTH_SHORT).show();
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

}
