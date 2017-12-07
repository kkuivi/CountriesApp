package com.apps.elikem.midtronics.country;

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

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    Context mContext;
    ArrayList<String> mCountries;

    CountryAdapter(Context context, ArrayList<String> countries) {
        mContext = context;
        mCountries = countries;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryTv;

        ViewHolder(View itemView) {
            super(itemView);

            countryTv = itemView.findViewById(R.id.item_country_tv_name);
        }
    }


    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View countryView = inflater.inflate(R.layout.item_country, parent, false);

        return  new ViewHolder(countryView);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder viewHolder, int position) {
        String country = mCountries.get(position);

        viewHolder.countryTv.setText(country);

    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }
}
