package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.utils.Highlight;

/**
 * Created by philipp on 12/06/15.
 */
public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

    // states
    protected static final int NONE = 0;
    protected static final int DRAG = 1;
    protected static final int X_ZOOM = 2;
    protected static final int Y_ZOOM = 3;
    protected static final int PINCH_ZOOM = 4;
    protected static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;

    /** integer field that holds the current touch-state */
    protected int mTouchMode = NONE;

    /** the touch highlight mode. true = adding hightlights to graph. false = removing highlights from graph  */
    boolean mHighlightMode;
    boolean mHighlightModeInitialisedOnTouch = false;

    /** the gesturedetector used for detecting taps and longpresses, ... */
    protected GestureDetector mGestureDetector;

    /** the chart the listener represents */
    protected T mChart;

    public ChartTouchListener(T chart) {
        this.mChart = chart;

        mGestureDetector = new GestureDetector(chart.getContext(), this);
    }

    /**
     * Sets the highlight mode that is used via touch.
     * @param highlightMode
     */
    public void setHighlightMode(boolean highlightMode) {
        mHighlightMode = highlightMode;
    }

    public void setHighlightModeOnTouch(Highlight high) {
        if (mChart.isHighlighted(high)) {
            mHighlightMode = false;
        } else {
            mHighlightMode = true;
        }
    }

    public boolean getHighlightMode() {
        return mHighlightMode;
    }

    /**
     * returns the touch mode the listener is currently in
     *
     * @return
     */

    public boolean isHighlightModeInitialisedOnTouch() {
        return mHighlightModeInitialisedOnTouch;
    }

    public int getTouchMode() {
        return mTouchMode;
    }

    /**
     * returns the distance between two points
     *
     * @param eventX
     * @param startX
     * @param eventY
     * @param startY
     * @return
     */
    protected static float distance(float eventX, float startX, float eventY, float startY) {
        float dx = eventX - startX;
        float dy = eventY - startY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public boolean onTouch(MotionEvent event, Highlight high) {

        if(high != null) {
            mHighlightModeInitialisedOnTouch = true;
            this.setHighlightModeOnTouch(high);
        }

        if(mHighlightModeInitialisedOnTouch) {
            if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                mHighlightModeInitialisedOnTouch = false;
            }
        }

        return true;
    }


}
