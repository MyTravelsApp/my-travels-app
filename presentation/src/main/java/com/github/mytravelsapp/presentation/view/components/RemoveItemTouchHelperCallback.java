package com.github.mytravelsapp.presentation.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.view.adapter.AbstractAdapter;

/**
 * Simple implementation of {@link ItemTouchHelper.SimpleCallback} to remove on swipe.
 * @author fjtorres
 */
public class RemoveItemTouchHelperCallback<M> extends ItemTouchHelper.SimpleCallback {

    //https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.cwae88oel

    private final AbstractAdapter<M, ?> adapter;

    private final boolean activeMove;

    public RemoveItemTouchHelperCallback(final AbstractAdapter<M, ?> pAdapter) {
        this(pAdapter, false);
    }

    public RemoveItemTouchHelperCallback(final AbstractAdapter<M, ?> pAdapter, final boolean pActiveMove) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.adapter = pAdapter;
        this.activeMove = pActiveMove;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (activeMove) {
            adapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        return activeMove;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.remove(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Get RecyclerView item from the ViewHolder
            View itemView = viewHolder.itemView;

            Paint p = new Paint();
            if (dX > 0) {
                p.setColor(recyclerView.getResources().getColor(R.color.colorAccent));

                // Draw Rect with varying right side, equal to displacement dX
                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                        (float) itemView.getBottom(), p);
            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
