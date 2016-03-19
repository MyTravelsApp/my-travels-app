package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Base class for application adapters.
 *
 * @param <M>  Model class
 * @param <VH> VievHolder class.
 * @author fjtorres
 */
public abstract class AbstractAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final LayoutInflater layoutInflater;

    private List<M> list;

    private OnItemClickListener onItemClickListener;

    private OnRemoveListener onRemoveListener;

    public AbstractAdapter(final Context context, final List<M> pList) {
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

    public void undoRemove (final int position, final M model) {
        this.list.add(position, model);
        notifyItemInserted(position);
    }

    public void remove(final int position) {
        final M removed = list.remove(position);
        notifyItemRemoved(position);

        if (onRemoveListener != null) {
            onRemoveListener.onRemove(position, removed);
        }
    }

    public void addItemList(final M model) {
       list.add(model);
       notifyItemChanged(list.size() - 1);
    }

    private void validateData(final List<M> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnRemoveListener getOnRemoveListener() {
        return onRemoveListener;
    }

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    public List<M> getList() {
        return list;
    }

    public void setList(final List<M> pList) {
            validateData(pList);
        this.list = pList;
        this.notifyDataSetChanged();
    }

    public interface OnItemClickListener<M> {
        void onItemClicked(M model);
    }

    public interface OnRemoveListener<M> {
        void onRemove(int position, M model);
    }
}
