package com.github.mytravelsapp.presentation.view.components;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.view.adapter.AbstractSelectorAdapter;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesSelectorFragment;

/**
 * @author kisco
 */
public class SelectedOnGestureListener extends GestureDetector.SimpleOnGestureListener {

    private ActionMode actionMode;

    private final RecyclerView recyclerView;

    private final AppCompatActivity activity;

    private OnSelectListener onSelectListener;

    public SelectedOnGestureListener(final RecyclerView pRecyclerView, final AppCompatActivity pActivity) {
        this.recyclerView = pRecyclerView;
        this.activity = pActivity;
    }

    public void onLongPress(MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (actionMode == null) {
            // Start the CAB using the ActionMode.Callback defined above
            actionMode = activity.startSupportActionMode(callback);
        }

        int idx = recyclerView.getChildAdapterPosition(view);
        toggleSelection(idx);
        super.onLongPress(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        int idx = recyclerView.getChildAdapterPosition(view);
        if (actionMode != null) {
            toggleSelection(idx);
        }
        return super.onSingleTapConfirmed(e);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    private void toggleSelection(final int idx) {
        if (recyclerView.getAdapter() instanceof AbstractSelectorAdapter) {
            final AbstractSelectorAdapter<?, ?> lAdapter = ((AbstractSelectorAdapter) recyclerView.getAdapter());
            lAdapter.toggleSelection(idx);
            if (lAdapter.getSelectedItemCount() == 0) {
                actionMode.finish();
            } else {
                actionMode.setTitle(activity.getString(R.string.text_selection, lAdapter.getSelectedItemCount()));
            }
        }
    }

    private final ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_selection, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode mode, final MenuItem item) {
            boolean result = true;
            switch (item.getItemId()) {
                case R.id.action_selection:
                    if (onSelectListener != null) {
                        onSelectListener.onSelect();
                    }
                    actionMode.finish();
                    break;
                default:
                    result = false;
                    break;
            }

            return result;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            if (recyclerView.getAdapter() instanceof AbstractSelectorAdapter) {
                final AbstractSelectorAdapter<?, ?> lAdapter = ((AbstractSelectorAdapter) recyclerView.getAdapter());
                lAdapter.clearSelection();
            }
        }
    };

    public static interface OnSelectListener {
        void onSelect();
    }
}
