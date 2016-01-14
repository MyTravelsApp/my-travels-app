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
public class TravelPlacesAdapter extends RecyclerView.Adapter<TravelPlacesAdapter.TravelPlacesViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<TravelPlacesModel> list;

    private OnItemClickListener onItemClickListener;

    private OnRemoveListener onRemoveListener;

    public TravelPlacesAdapter(final Context context, final List<TravelPlacesModel> pList) {
        this.list = pList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(TravelPlacesViewHolder holder, int position) {
        final TravelPlacesModel model = list.get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TravelPlacesAdapter.this.onItemClickListener != null) {
                    TravelPlacesAdapter.this.onItemClickListener.onTravelPlacesItemClicked(model);
                }
            }
        });
        holder.txtTitle.setText(model.getName());
        holder.txtCategory.setText(model.getCategory());
    }

    @Override
    public TravelPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_travel_places, parent, false);
        final TravelPlacesViewHolder holder = new TravelPlacesViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void remove(final int position) {
        final TravelPlacesModel removed = list.remove(position);
        notifyItemRemoved(position);

        if (onRemoveListener != null) {
            onRemoveListener.onRemove(removed.getId());
        }
    }

    public void setList(final List<TravelPlacesModel> pList) {
        validateData(pList);
        this.list = pList;
        this.notifyDataSetChanged();
    }

    private void validateData (final List<TravelPlacesModel> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
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

    public interface OnItemClickListener {
        void onTravelPlacesItemClicked(TravelPlacesModel model);
    }
    public interface OnRemoveListener {
        void onRemove(long identifier);
    }

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }
}
