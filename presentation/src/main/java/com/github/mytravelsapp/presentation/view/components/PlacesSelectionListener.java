package com.github.mytravelsapp.presentation.view.components;

import android.view.View;
import android.widget.AdapterView;

import com.github.mytravelsapp.presentation.view.adapter.PlacesAutoCompleteAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

/**
 * Listener for {@link Place} selection in autocomplete.
 *
 * @author fjtorres
 */
public class PlacesSelectionListener implements AdapterView.OnItemClickListener {

    private final PlacesAutoCompleteAdapter adapter;

    private final GoogleApiClient googleApiClient;

    private final PlacesSelectionCallback callback;

    public PlacesSelectionListener(final PlacesAutoCompleteAdapter pAdapter, final GoogleApiClient pGoogleApiClient, final PlacesSelectionCallback pCallback) {
        this.adapter = pAdapter;
        this.googleApiClient = pGoogleApiClient;
        this.callback = pCallback;
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        final PlacesAutoCompleteAdapter.PlaceAutocomplete selectedItem = adapter.getItem(position);

        Places.GeoDataApi.getPlaceById(googleApiClient, selectedItem.placeId.toString()).setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (places.getStatus().isSuccess()) {
                    callback.onSelect(places.get(0));
                } else {
                    callback.onError();
                }
            }
        });
    }

    public interface PlacesSelectionCallback {
        void onSelect(Place selectedPlace);

        void onError();
    }
}
