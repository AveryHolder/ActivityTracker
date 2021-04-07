package com.avery.activitytrackerfinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ACTIVITY_ID = "activityID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        if (fragment == null) {
            fragment = new DetailsFragment();
            int activityID = getIntent().getIntExtra(EXTRA_ACTIVITY_ID, 1);
            fragment = DetailsFragment.newInstance(activityID);
            fragmentManager.beginTransaction()
                    .add(R.id.details_fragment_container, fragment)
                    .commit();
        }
    }
}