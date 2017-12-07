package com.apps.elikem.midtronics.country;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.apps.elikem.midtronics.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

/**
 * Created by elikem on 12/6/17.
 */

public class CountryDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView mCapitalTv;
    TextView mPopulationTv;
    TextView mAreaTv;
    TextView mSubRegionTv;

    SupportMapFragment mMapFragment;

    Country mCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        mCapitalTv = findViewById(R.id.activity_country_detail_tv_capital);
        mPopulationTv = findViewById(R.id.activity_country_detail_tv_population);
        mAreaTv = findViewById(R.id.activity_country_detail_tv_area);
        mSubRegionTv = findViewById(R.id.activity_country_detail_tv_subregion);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_country_detail_map);
        mMapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if(intent != null) {
            mCountry = (Country) Parcels.unwrap(getIntent().getParcelableExtra(CountryListActivity.COUNTRY));
            setupTextViews(mCountry);

            if(getSupportActionBar() != null) {
                getSupportActionBar().setTitle(mCountry.getName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.

        LatLng latLng = new LatLng(-33.852, 151.211);
        if(mCountry.latlng != null && mCountry.latlng.length == 2){
            latLng = new LatLng(mCountry.getLatlng()[0], mCountry.getLatlng()[1]);
        }

        googleMap.addMarker(new MarkerOptions().position(latLng).title(""));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public void setupTextViews(Country country) {

        String capitalLabel = String.format("Capital: %s", country.getCapital());
        mCapitalTv.setText(capitalLabel);

        String populationLabel = String.format("Population: %d", country.getPopulation());
        mPopulationTv.setText(populationLabel);

        String areaLabel = String.format("Area: %f", country.getArea());
        mAreaTv.setText(areaLabel);

        String subRegionLabel = String.format("SubRegion: %s", country.getSubregion());
        mSubRegionTv.setText(subRegionLabel);
    }


}
