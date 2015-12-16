package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter that manages a list of {@link TravelModel}.
 *
 * @author fjtorres
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<TravelModel> list;

    private OnItemClickListener onItemClickListener;

    public TravelAdapter(final Context context, final List<TravelModel> pList) {
        validateData(pList);
        this.list = pList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Return the number of elements in adapter.
     *
     * @return Number of elements.
     */
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * Load model for specific position in view.
     *
     * @param holder   Row view.
     * @param position Model position to show.
     */
    @Override
    public void onBindViewHolder(TravelViewHolder holder, int position) {
        final TravelModel model = list.get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TravelAdapter.this.onItemClickListener != null) {
                    TravelAdapter.this.onItemClickListener.onTravelItemClicked(model);
                }
            }
        });
        holder.txt_title.setText(model.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (model.getStartDate() != null) {
            holder.txt_start_date.setText(sdf.format(model.getStartDate()));
        } else {
            holder.txt_start_date.setText("");
        }
        if (model.getFinishDate() != null) {
            holder.txt_finish_date.setText(sdf.format(model.getFinishDate()));
        } else {
            holder.txt_finish_date.setText("");
        }
    }

    /**
     * Create row view.
     *
     * @param parent   Parent view.
     * @param viewType View type.
     * @return Row view.
     */
    @Override
    public TravelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_travel, parent, false);
        return new TravelViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(final List<TravelModel> pList) {
        validateData(pList);
        this.list = pList;
        this.notifyDataSetChanged();
    }

    private void validateData (final List<TravelModel> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    /**
     * Row view holder for this adapter.
     */
    static class TravelViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lv_row)
        RelativeLayout lv_row;

        @Bind(R.id.txt_title)
        TextView txt_title;

        @Bind(R.id.txt_start_date)
        TextView txt_start_date;

        @Bind(R.id.txt_finish_date)
        TextView txt_finish_date;

        public TravelViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onTravelItemClicked(TravelModel model);
    }
}
