package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author stefani
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<CategoryModel> list;

    private OnItemClickListener onItemClickListener;

    private OnRemoveListener onRemoveListener;

    public CategoryAdapter(final Context context, final List<CategoryModel> pList) {
        this.list = pList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final CategoryModel model = list.get(position);
        holder.lv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CategoryAdapter.this.onItemClickListener != null) {
                    CategoryAdapter.this.onItemClickListener.onCategoryItemClicked(model);
                }
            }
        });
        holder.txtTitle.setText(model.getName());

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_category, parent, false);
        final CategoryViewHolder holder = new CategoryViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void remove(final int position) {
        final CategoryModel removed = list.remove(position);
        notifyItemRemoved(position);

        if (onRemoveListener != null) {
            onRemoveListener.onRemove(removed.getId());
        }
    }

    public void setList(final List<CategoryModel> pList) {
        validateData(pList);
        this.list = pList;
        this.notifyDataSetChanged();
    }

    private void validateData (final List<CategoryModel> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lv_row_categories)
        RelativeLayout lv_row;

        @Bind(R.id.txt_title)
        TextView txtTitle;

        public CategoryViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onCategoryItemClicked(CategoryModel model);
    }
    public interface OnRemoveListener {
        void onRemove(long identifier);
    }

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }
}
