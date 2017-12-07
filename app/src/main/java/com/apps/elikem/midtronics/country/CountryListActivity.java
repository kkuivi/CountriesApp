package com.apps.elikem.midtronics.country;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.apps.elikem.midtronics.R;
import com.apps.elikem.midtronics.profile.ProfileActivity;
import com.apps.elikem.midtronics.utility.ItemClickSupport;
import com.apps.elikem.midtronics.webservices.WebServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

public class CountryListActivity extends AppCompatActivity {

    RecyclerView mCountriesRv;
    String[] mCountriesArr;
    ArrayList<String> mCountriesAsArrayList;
    CountryAdapter mCountryAdapter;

    WeakReference<CountryListActivity> mWeakActivityRef;

    public static final String COUNTRY = "country";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        mCountriesRv = findViewById(R.id.activity_country_rv_list);
        mCountriesArr = getResources().getStringArray(R.array.countries_array);

        mCountriesAsArrayList = new ArrayList<String>(Arrays.asList(mCountriesArr));
        mCountryAdapter = new CountryAdapter(this, mCountriesAsArrayList);
        mCountriesRv.setAdapter(mCountryAdapter);
        mCountriesRv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mCountriesRv.addItemDecoration(itemDecoration);

        mWeakActivityRef = new WeakReference<CountryListActivity>(this);

        ItemClickSupport.addTo(mCountriesRv).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        ConnectivityManager cm = (ConnectivityManager) v.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                        if(isConnected) {
                            String countryName = mCountriesArr[position];
                            GetCountryData getCountryData = new GetCountryData(mWeakActivityRef);
                            getCountryData.execute(countryName);
                        }
                        else {
                            displayNoNetworkConnectionDialog();
                        }

                    }
                }
        );
    }

    private static class GetCountryData extends AsyncTask<String, Void, Country> {

        ProgressDialog mProgressDialog;
        WeakReference<CountryListActivity> countryListActivityWeakReference;

        GetCountryData(WeakReference<CountryListActivity> countryListActivityWeakReference) {
            this.countryListActivityWeakReference = countryListActivityWeakReference;
        }

        @Override
        protected void onPreExecute() {

            mProgressDialog = new ProgressDialog(countryListActivityWeakReference.get());
            mProgressDialog.setMessage("Getting Country data...");
            mProgressDialog.show();
        }

        @Override
        protected Country doInBackground(String... countryNames) {

            String countryName = countryNames[0];

            try {
                String jsonResponse = WebServices.getCountryDetailWebServiceCall(countryName);
                JSONArray jsonArray = new JSONArray(jsonResponse);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                Gson gson = new GsonBuilder().create();
                Country country = gson.fromJson(jsonObject.toString(), Country.class);

                return country;

            }
            catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onProgressUpdate(Void... param) {
        }

        @Override
        protected void onPostExecute(Country result) {
            mProgressDialog.cancel();
            if(result != null) {
                goToDetails(countryListActivityWeakReference, result);
            }
            else {
                Toast.makeText(countryListActivityWeakReference.get().getApplicationContext(), "Failed to get country data. Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_country_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {

            case R.id.view_profile:
                Intent viewProfileIntent = new Intent(this, ProfileActivity.class);
                startActivity(viewProfileIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public static void goToDetails(WeakReference<CountryListActivity> countryListActivityWeakReference, Country country) {

        Intent intent = new Intent(countryListActivityWeakReference.get(), CountryDetailActivity.class);
        intent.putExtra(COUNTRY, Parcels.wrap(country));
        countryListActivityWeakReference.get().startActivity(intent);
    }

    public void displayNoNetworkConnectionDialog() {

        final Activity activity = this;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("No Network Connection");
        builder.setMessage("Must be connected to internet to view country data");

        builder.setPositiveButton("Connect to a network", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
