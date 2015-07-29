
package com.github.mikephil.charting.data;

import java.util.List;

/**
 * Baseclass of all DataSets for Bar-, Line-, Scatter- and CandleStickChart.
 * 
 * @author Philipp Jahoda
 */
public abstract class BarLineScatterCandleDataSet<T extends Entry> extends DataSet<T> {

    public BarLineScatterCandleDataSet(List<T> yVals, String label) {
        super(yVals, label);
    }

    public BarLineScatterCandleDataSet(List<T> yVals, String label, List<Integer> colors, List<Integer> highLightColors) {
        super(yVals, label, colors, highLightColors);
    }

}
