package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.components.RippleForegroundListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter that manages a list of {@link TravelModel}.
 *
 * @author fjtorres
 */
public class TravelAdapter extends AbstractAdapter<TravelModel, TravelAdapter.TravelViewHolder> {

    public TravelAdapter(final Context context, final List<TravelModel> pList) {
        super(context, pList);
    }

    /**
     * Load model for specific position in view.
     *
     * @param holder   Row view.
     * @param position Model position to show.
     */
    @Override
    public void onBindViewHolder(TravelViewHolder holder, int position) {
        final TravelModel model = getList().get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClicked(model);
                }
            }
        });
        holder.txt_title.setText(model.getName());

        if (model.getStartDate() != null) {
            holder.txt_start_date.setText(Utils.formatDate(model.getStartDate(), Utils.DATE_FORMAT));
        } else {
            holder.txt_start_date.setText("");
        }
        if (model.getFinishDate() != null) {
            holder.txt_finish_date.setText(Utils.formatDate(model.getFinishDate(), Utils.DATE_FORMAT));
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
        final View view = getLayoutInflater().inflate(R.layout.row_travel, parent, false);
        return new TravelViewHolder(view);
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

        private final RippleForegroundListener rippleForegroundListener = new RippleForegroundListener(R.id.card_row);

        public TravelViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lv_row.setOnTouchListener(rippleForegroundListener);
        }
    }

}
