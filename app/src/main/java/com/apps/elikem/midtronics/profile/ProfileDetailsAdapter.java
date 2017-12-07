package com.apps.elikem.midtronics.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.elikem.midtronics.R;

import java.util.ArrayList;

/**
 * Created by elikem on 12/6/17.
 */

public class ProfileDetailsAdapter extends RecyclerView.Adapter<ProfileDetailsAdapter.ViewHolder>{

    Context mContext;
    ArrayList<String> mProfileDetails;

    ProfileDetailsAdapter(Context context, ArrayList<String> countries) {
        mContext = context;
        mProfileDetails = countries;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTv;

        ViewHolder(View itemView) {
            super(itemView);

            textTv = itemView.findViewById(R.id.item_profile_details_tv_text);
        }
    }


    @Override
    public ProfileDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View profileDetailsView = inflater.inflate(R.layout.item_profile_details, parent, false);

        return new ProfileDetailsAdapter.ViewHolder(profileDetailsView);
    }

    @Override
    public void onBindViewHolder(ProfileDetailsAdapter.ViewHolder viewHolder, int position) {
        String country = mProfileDetails.get(position);

        viewHolder.textTv.setText(country);

    }

    @Override
    public int getItemCount() {
        return mProfileDetails.size();
    }
}
