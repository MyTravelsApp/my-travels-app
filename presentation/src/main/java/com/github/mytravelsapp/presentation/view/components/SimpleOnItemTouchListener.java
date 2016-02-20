package com.github.mytravelsapp.presentation.view.components;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * @author fjtorres
 */
public class SimpleOnItemTouchListener implements RecyclerView.OnItemTouchListener {
    private final GestureDetectorCompat gestureDetector;

    public SimpleOnItemTouchListener(GestureDetectorCompat gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
