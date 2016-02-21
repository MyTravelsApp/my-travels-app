package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.components.RippleForegroundListener;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author kisco
 */
public class TravelPlacesSelectorAdapter extends AbstractSelectorAdapter<TravelPlacesModel, TravelPlacesSelectorAdapter.TravelPlacesSelectorViewHolder> {

    public TravelPlacesSelectorAdapter (final Context context, final List<TravelPlacesModel> pList) {
        super(context, pList);
    }

    /**
     * Load model for specific position in view.
     *
     * @param holder   Row view.
     * @param position Model position to show.
     */
    @Override
    public void onBindViewHolder(TravelPlacesSelectorViewHolder holder, int position) {
        final TravelPlacesModel model = getList().get(position);
        holder.txtTitle.setText(model.getName());
        holder.txtCategory.setText(model.getCategoryModel().getName());
        holder.overlay.setVisibility(isSelected(position)?View.VISIBLE:View.GONE);
    }

    /**
     * Create row view.
     *
     * @param parent   Parent view.
     * @param viewType View type.
     * @return Row view.
     */
    @Override
    public TravelPlacesSelectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.row_travel_places_selection, parent, false);
        return new TravelPlacesSelectorViewHolder(view);
    }

    static class TravelPlacesSelectorViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lv_row_places)
        RelativeLayout lv_row;

        @Bind(R.id.txt_title)
        TextView txtTitle;

        @Bind(R.id.txt_category_target)
        TextView txtCategory;

        @Bind(R.id.selected_overlay)
        View overlay;

        public TravelPlacesSelectorViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
