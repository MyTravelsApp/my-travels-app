package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fjtorres
 */
public class PlacesAutoCompleteAdapter extends ArrayAdapter<PlacesAutoCompleteAdapter.PlaceAutocomplete> {

    private List<PlaceAutocomplete> data;

    private final GoogleApiClient googleApiClient;

    private AutocompleteFilter placeFilter;
    private LatLngBounds bounds;

    public PlacesAutoCompleteAdapter(Context context, int resource, final GoogleApiClient pGoogleApiClient, LatLngBounds bounds,
                                     AutocompleteFilter filter) {
        super(context, resource);
        this.googleApiClient = pGoogleApiClient;
        this.bounds = bounds;
        this.placeFilter = filter;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PlaceAutocomplete getItem(int position) {
        return data.get(position);
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null) {
                    // Query the autocomplete API for the entered constraint
                    data = getPredictions(constraint);
                    if (data != null) {
                        // Results
                        results.values = data;
                        results.count = data.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private ArrayList<PlaceAutocomplete> getPredictions(CharSequence constraint) {
        if (googleApiClient != null) {
            PendingResult<AutocompletePredictionBuffer> results =
                    Places.GeoDataApi
                            .getAutocompletePredictions(googleApiClient, constraint.toString(),
                                    bounds, placeFilter);
            // Wait for predictions, set the timeout.
            AutocompletePredictionBuffer autocompletePredictions = results
                    .await(60, TimeUnit.SECONDS);
            final Status status = autocompletePredictions.getStatus();
            if (!status.isSuccess()) {
                Toast.makeText(getContext(), "Error: " + status.toString(),
                        Toast.LENGTH_SHORT).show();
                autocompletePredictions.release();
                return null;
            }

            Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
            ArrayList<PlaceAutocomplete> resultList = new ArrayList<>(autocompletePredictions.getCount());
            while (iterator.hasNext()) {
                AutocompletePrediction prediction = iterator.next();

                resultList.add(new PlaceAutocomplete(prediction.getPlaceId(),
                        prediction.getPrimaryText(null), prediction.getFullText(null)));
            }
            // Buffer release
            autocompletePredictions.release();
            return resultList;
        }

        return null;
    }

    public class PlaceAutocomplete {

        public CharSequence placeId;
        public CharSequence primaryText;
        public CharSequence fullText;
        public LatLng latLng;

        PlaceAutocomplete(CharSequence placeId, CharSequence primaryText, CharSequence fullText) {
            this.placeId = placeId;
            this.primaryText = primaryText;
            this.fullText = fullText;
        }

        @Override
        public String toString() {
            return fullText.toString();
        }
    }
}
