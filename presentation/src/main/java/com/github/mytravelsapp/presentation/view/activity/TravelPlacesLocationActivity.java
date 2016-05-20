package com.github.mytravelsapp.presentation.view.activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.github.mytravelsapp.R;

import java.io.IOException;
import java.util.List;

public class TravelPlacesLocationActivity extends AbstractActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_places_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final Geocoder geocoder = new Geocoder(this);

        try {
            final List<Address> locations = geocoder.getFromLocationName("Spain", 10);

            if (locations.size() > 0) {
                final Address address = locations.get(0);
                currentMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title(address.getFeatureName()));

            } else {

            }
        } catch (final IOException ioe) {
            // Add a marker in Sydney
            LatLng sydney = new LatLng(-34, 151);
            currentMarker = mMap.addMarker(new MarkerOptions().position(sydney));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentMarker.getPosition()));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                currentMarker.setPosition(latLng);
            }
        });
    }
}
