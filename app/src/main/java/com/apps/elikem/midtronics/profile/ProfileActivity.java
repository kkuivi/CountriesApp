package com.apps.elikem.midtronics.profile;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.elikem.midtronics.R;
import com.apps.elikem.midtronics.utility.CircleTransform;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by elikem on 12/6/17.
 */

public class ProfileActivity extends AppCompatActivity {

    ImageView mProfileIv;
    TextView mNameTv;
    TabLayout mTabLayout;

    String EDUCATION_TAB = "Education";
    String EXPERIENCE_TAB = "Experience";
    String PROJECTS_TAB = "Projects";

    String mName;
    String[] mEducationArray;
    String[] mExperienceArray;
    String[] mProjectsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileIv = findViewById(R.id.activity_profile_iv_img);
        mNameTv = findViewById(R.id.activity_profile_tv_name);
        mTabLayout = findViewById(R.id.activity_profile_tl_tabs);

        mName = getResources().getString(R.string.profile_name);
        mNameTv.setText(mName);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEducationArray = getResources().getStringArray(R.array.education_array);
        mExperienceArray = getResources().getStringArray(R.array.experience_array);
        mProjectsArray = getResources().getStringArray(R.array.projects_array);

        Picasso.with(this).load(R.drawable.default_profile_image).resize(250, 250).centerCrop().transform(new CircleTransform()).into(mProfileIv);

        final FragmentManager fm = this.getSupportFragmentManager();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().toString().equalsIgnoreCase(EDUCATION_TAB)) {

                    ProfileDetailsFragment educationDetailsFragment = new ProfileDetailsFragment();
                    Bundle parameters = new Bundle();

                    ArrayList<String> educationItemsAsArrayList = new ArrayList<String>(Arrays.asList(mEducationArray));
                    parameters.putStringArrayList("details", educationItemsAsArrayList);
                    educationDetailsFragment.setArguments(parameters);

                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.activity_profile_fl_container, educationDetailsFragment, "educationDetailsFragment");
                    fragmentTransaction.commit();
                }

                else if (tab.getText().toString().equalsIgnoreCase(EXPERIENCE_TAB)) {

                    ProfileDetailsFragment experienceDetailsFragment = new ProfileDetailsFragment();
                    Bundle parameters = new Bundle();

                    ArrayList<String> experienceItemsAsArrayList = new ArrayList<String>(Arrays.asList(mExperienceArray));
                    parameters.putStringArrayList("details", experienceItemsAsArrayList);
                    experienceDetailsFragment.setArguments(parameters);

                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.activity_profile_fl_container, experienceDetailsFragment, "experienceDetailsFragment");
                    fragmentTransaction.commit();
                }

                else if (tab.getText().toString().equalsIgnoreCase(PROJECTS_TAB)) {

                    ProfileDetailsFragment projectDetailsFragment = new ProfileDetailsFragment();
                    Bundle parameters = new Bundle();

                    ArrayList<String> projectItemsAsArrayList = new ArrayList<String>(Arrays.asList(mProjectsArray));
                    parameters.putStringArrayList("details", projectItemsAsArrayList);
                    projectDetailsFragment.setArguments(parameters);

                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.activity_profile_fl_container, projectDetailsFragment, "projectDetailsFragment");
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.getTabAt(1).select();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
