
package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Base dataset for line and radar DataSets.
 * 
 * @author Philipp Jahoda
 */
public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> {

    /** the color that is used for filling the line surface */
    protected List<Integer> mFillColors = null;

    /** the color that is used for filling the line surface when highlighted */
    protected List<Integer> mHighlightFillColors = null;

    /** the width of the drawn data lines */
    private float mLineWidth = 2.5f;

    /** if true, the data will also be drawn filled */
    private boolean mDrawFilled = false;
    
//    private Shader mShader;
    
    public LineRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
        this.init();
    }

    public LineRadarDataSet(List<T> yVals, String label, List<Integer> colors, List<Integer> highLightColors) {
        super(yVals, label, colors, highLightColors);
        this.init();
    }

    private void init() {
        resetFillColors();
        resetHighlightFillColors();
    }

    /**
     * set the line width of the chart (min = 0.2f, max = 10f); default 1f NOTE:
     * thinner line == better performance, thicker line == worse performance
     * 
     * @param width
     */
    public void setLineWidth(float width) {

        if (width < 0.2f)
            width = 0.2f;
        if (width > 10.0f)
            width = 10.0f;
        mLineWidth = Utils.convertDpToPixel(width);
    }

    /**
     * returns the width of the drawn chart line
     * 
     * @return
     */
    public float getLineWidth() {
        return mLineWidth;
    }

    /**
     * Set to true if the DataSet should be drawn filled (surface), and not just
     * as a line, disabling this will give great performance boost! default:
     * false
     * 
     * @param filled
     */
    public void setDrawFilled(boolean filled) {
        mDrawFilled = filled;
    }

    /**
     * returns true if filled drawing is enabled, false if not
     * 
     * @return
     */
    public boolean isDrawFilledEnabled() {
        return mDrawFilled;
    }

    /** BELOW THIS FILL COLOR HANDLING */

    /**
     * Sets the fill colors that should be used fore this DataSet. Fill colors are reused
     * as soon as the number of Entries the DataSet represents is higher than
     * the size of the colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    public void setFillColors(List<Integer> colors) {
        this.mFillColors = colors;
    }

    /**
     * Sets the fill colors that should be used fore this DataSet. Fill colors are reused
     * as soon as the number of Entries the DataSet represents is higher than
     * the size of the fill colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    public void setFillColors(int[] colors) {
        this.mFillColors = ColorTemplate.createColors(colors);
    }

    /**
     * Sets the fill colors that should be used fore this DataSet. Fill colors are reused
     * as soon as the number of Entries the DataSet represents is higher than
     * the size of the fill colors array. You can use
     * "new int[] { R.color.red, R.color.green, ... }" to provide colors for
     * this method. Internally, the fill colors are resolved using
     * getResources().getColor(...)
     *
     * @param colors
     */
    public void setFillColors(int[] colors, Context c) {

        List<Integer> clrs = new ArrayList<Integer>();

        for (int color : colors) {
            clrs.add(c.getResources().getColor(color));
        }

        mFillColors = clrs;
    }

    /**
     * Adds a new color to the fill colors array of the DataSet.
     *
     * @param color
     */
    public void addFillColor(int color) {
        if (mFillColors == null)
            mFillColors = new ArrayList<Integer>();
        mFillColors.add(color);
    }

    /**
     * Sets the one and ONLY highlight fill color that should be used for this DataSet.
     * Internally, this recreates the fill colors array and adds the specified color.
     *
     * @param color
     */
    public void setFillColor(int color) {
        mFillColors = new ArrayList<Integer>();
        mFillColors.add(color);
    }

    /**
     * returns all the fill colors that are set for this DataSet
     *
     * @return
     */
    public List<Integer> getFillColors() {
        return mFillColors;
    }

    /**
     * Returns the fill color at the given index of the DataSet's fill color array.
     * Performs a IndexOutOfBounds check by modulus.
     *
     * @param index
     * @return
     */
    public int getFillColor(int index) {
        return mFillColors.get(index % mFillColors.size());
    }

    /**
     * Returns the first fill color (index 0) of the fill colors-array this DataSet
     * contains.
     *
     * @return
     */
    public int getFillColor() {
        return mFillColors.get(0);
    }

    /**
     * Resets all fill colors of this DataSet and recreates the fill colors array.
     */
    public void resetFillColors() {
        mFillColors = new ArrayList<Integer>();
        mFillColors.addAll(mColors);
    }

    /**
     * Sets the highlight fill colors that should be used fore this DataSet. Highlight fill colors
     * are reused as soon as the number of Entries the DataSet represents is higher than
     * the size of the highlight fill colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    public void setHighlightFillColors(List<Integer> colors) {
        this.mHighlightFillColors = colors;
    }

    /**
     * Sets the highlight fill colors that should be used fore this DataSet. Highlight fill colors
     * are reused as soon as the number of Entries the DataSet represents is higher than
     * the size of the highlight fill colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    public void setHighlightFillColors(int[] colors) {
        this.mHighlightFillColors = ColorTemplate.createColors(colors);
    }

    /**
     * Sets the highlight fill colors that should be used fore this DataSet. Highlight fill colors
     * are reused as soon as the number of Entries the DataSet represents is higher than
     * the size of the highlight fill colors array. You can use
     * "new int[] { R.color.red, R.color.green, ... }" to provide colors for
     * this method. Internally, the fill colors are resolved using
     * getResources().getColor(...)
     *
     * @param colors
     */
    public void setHighlightFillColors(int[] colors, Context c) {

        List<Integer> clrs = new ArrayList<Integer>();

        for (int color : colors) {
            clrs.add(c.getResources().getColor(color));
        }

        mHighlightFillColors = clrs;
    }

    /**
     * Adds a new color to the highlight fill colors array of the DataSet.
     *
     * @param color
     */
    public void addHighlightFillColor(int color) {
        if (mHighlightFillColors == null)
            mHighlightFillColors = new ArrayList<Integer>();
        mHighlightFillColors.add(color);
    }

    /**
     * Sets the one and ONLY highlight fill color that should be used for this DataSet.
     * Internally, this recreates the highlight fill colors array and adds the specified color.
     *
     * @param color
     */
    public void setHighlightFillColor(int color) {
        mHighlightFillColors = new ArrayList<Integer>();
        mHighlightFillColors.add(color);
    }

    /**
     * returns all the highlight fill colors that are set for this DataSet
     *
     * @return
     */
    public List<Integer> getHighlightFillColors() {
        return mHighlightFillColors;
    }

    /**
     * Returns the highlight fill color at the given index of the DataSet's highlight fill color
     * array. Performs a IndexOutOfBounds check by modulus.
     *
     * @param index
     * @return
     */
    public int getHighlightFillColor(int index) {
        return mHighlightFillColors.get(index % mHighlightFillColors.size());
    }

    /**
     * Returns the first highlight fill color (index 0) of the highlight fill colors-array
     * this DataSet contains.
     *
     * @return
     */
    public int getHighlightFillColor() {
        return mHighlightFillColors.get(0);
    }

    /**
     * Resets all highlight fill colors of this DataSet and recreates the highlight fill colors
     * array.
     */
    public void resetHighlightFillColors() {
        mHighlightFillColors = new ArrayList<Integer>();
        mHighlightFillColors.addAll(mHighLightColors);
    }
}
