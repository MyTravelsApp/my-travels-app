package com.github.mytravelsapp.presentation.view.components;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.github.mytravelsapp.presentation.view.adapter.TravelAdapter;

/**
 * Created by kisco on 17/12/2015.
 */
public class TravelTouchHelperCallback extends ItemTouchHelper.SimpleCallback {
    private final TravelAdapter adapter;

    public TravelTouchHelperCallback(final TravelAdapter pAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = pAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.remove(viewHolder.getAdapterPosition());
    }
}
