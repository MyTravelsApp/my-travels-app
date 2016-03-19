package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.presentation.view.components.RippleForegroundListener;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter for present dates information.
 *
 * @author fjtorres
 */
public class DateAdapter extends AbstractAdapter<Date, DateAdapter.DateViewHolder> {

    private final String dateFormat;

    public DateAdapter(final Context context, final List<Date> pList) {
        this(context, pList, Utils.LARGE_DATE_FORMAT);
    }

    public DateAdapter(final Context context, final List<Date> pList, final String pDateFormat) {
        super(context, pList);
        this.dateFormat = pDateFormat;
    }

    /**
     * Load model for specific position in view.
     *
     * @param holder   Row view.
     * @param position Model position to show.
     */
    @Override
    public void onBindViewHolder(DateViewHolder holder, int position) {
        final Date date = getList().get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClicked(date);
                }
            }
        });
        holder.txt_label.setText(Utils.formatDate(date, dateFormat));

    }

    /**
     * Create row view.
     *
     * @param parent   Parent view.
     * @param viewType View type.
     * @return Row view.
     */
    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.generic_row, parent, false);
        return new DateViewHolder(view);
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lv_row)
        RelativeLayout lv_row;

        @Bind(R.id.txt_label)
        TextView txt_label;

        private final RippleForegroundListener rippleForegroundListener = new RippleForegroundListener(R.id.card_row);

        public DateViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lv_row.setOnTouchListener(rippleForegroundListener);
        }
    }
}
