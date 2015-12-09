package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author fjtorres
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<TravelModel> list;

    private OnItemClickListener onItemClickListener;

    public TravelAdapter(final Context context, final List<TravelModel> pList) {
        this.list = pList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(TravelViewHolder holder, int position) {
        final TravelModel model = list.get(position);
        holder.txtTitle.setText(model.getName());
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TravelAdapter.this.onItemClickListener != null) {
                    TravelAdapter.this.onItemClickListener.onTravelItemClicked(model);
                }
            }
        });
    }

    @Override
    public TravelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_travel, parent, false);
        final TravelViewHolder holder = new TravelViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList (final List<TravelModel> pList) {
        this.list = pList;
    }

    static class TravelViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView txtTitle;

        public TravelViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onTravelItemClicked(TravelModel model);
    }
}
