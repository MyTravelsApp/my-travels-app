package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    }

    @Override
    public TravelPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_travel, parent, false);
        final TravelPlacesViewHolder holder = new TravelPlacesViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(final List<TravelPlacesModel> pList) {
        this.list = pList;
    }

    static class TravelPlacesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView txtTitle;

        public TravelPlacesViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onTravelItemClicked(TravelPlacesModel model);
    }

}
