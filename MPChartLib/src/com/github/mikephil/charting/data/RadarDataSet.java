
package com.github.mikephil.charting.data;

import java.util.ArrayList;
import java.util.List;

public class RadarDataSet extends LineRadarDataSet<Entry> {
    
    public RadarDataSet(List<Entry> yVals, String label) {
        super(yVals, label);
    }

    public RadarDataSet(List<Entry> yVals, String label, List<Integer> colors, List<Integer> highLightColors) {
        super(yVals, label, colors, highLightColors);
    }

    @Override
    public DataSet<Entry> copy() {

        List<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < mYVals.size(); i++) {
            yVals.add(mYVals.get(i).copy());
        }

        RadarDataSet copied = new RadarDataSet(yVals, getLabel(), mColors, mHighLightColors);

        return copied;
    }
}
