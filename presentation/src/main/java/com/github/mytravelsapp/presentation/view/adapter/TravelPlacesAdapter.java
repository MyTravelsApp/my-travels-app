package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author stefani
 */
public class TravelPlacesAdapter extends AbstractAdapter<TravelPlacesModel, TravelPlacesAdapter.TravelPlacesViewHolder> {

    public TravelPlacesAdapter(final Context context, final List<TravelPlacesModel> pList) {
        super(context, pList);
    }

    @Override
    public void onBindViewHolder(TravelPlacesViewHolder holder, int position) {
        final TravelPlacesModel model = getList().get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClicked(model);
                }
            }
        });
        holder.txtTitle.setText(model.getName());
        holder.txtCategory.setText(model.getCategoryModel().getName());
    }

    @Override
    public TravelPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.row_travel_places, parent, false);
        final TravelPlacesViewHolder holder = new TravelPlacesViewHolder(view);
        return holder;
    }


    static class TravelPlacesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lv_row_places)
        RelativeLayout lv_row;

        @Bind(R.id.txt_title)
        TextView txtTitle;

        @Bind(R.id.txt_category_target)
        TextView txtCategory;

        public TravelPlacesViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
