package com.apps.elikem.midtronics.profile;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.elikem.midtronics.R;

import java.util.ArrayList;

/**
 * Created by elikem on 12/6/17.
 */

public class ProfileDetailsFragment extends Fragment {

    RecyclerView mProfileDetailsRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_details, container, false);

        mProfileDetailsRv = rootView.findViewById(R.id.fragment_profile_details_rv_list);

        Bundle args = getArguments();

        if(args !=  null) {
            ArrayList<String> details = args.getStringArrayList("details");

            if(details != null) {
                ProfileDetailsAdapter profileDetailsAdapter = new ProfileDetailsAdapter(getContext(), details);
                mProfileDetailsRv.setAdapter(profileDetailsAdapter);

                mProfileDetailsRv.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            else {
                Toast.makeText(getContext(), "Error displaying profile details. Please try again.", Toast.LENGTH_SHORT).show();
            }

        }


        return rootView;
    }

}
