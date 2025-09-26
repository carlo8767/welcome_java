package smile;

import smile.data.DataFrame;
import smile.data.vector.ValueVector;
import smile.math.MathEx;
import smile.plot.swing.FigurePane;
import smile.plot.swing.ScatterPlot;
import smile.stat.*;
import smile.math.matrix.Matrix;
import smile.stat.distribution.ExponentialDistribution;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static smile.math.MathEx.mean;

public class CovarianceVariance {


    public static void main (String [] args) throws InterruptedException, InvocationTargetException {

        // BUILD A MATRIX  ROW AND COLUMN
        Matrix m = new Matrix(2, 2);
        // AD A VALUE ROW AND COLUMN
        for (int i = 0; i < m.ncol(); i++) {
            for (int n = 0; n < m.nrow(); n++) {
                m.add(n, i, i + 0.5);
            }
        }
        // EXTRACT THE VALUE OF COLUMN AND ROW AND CALCULATE THE MEAN
        var meeanColum = mean(m.colMeans());
        var meansRow = mean(m.rowMeans());
        // CALCULATE VARIANCE
        ExponentialDistribution varianceColum = ExponentialDistribution.fit(m.colMeans());
        System.out.println(varianceColum.variance());

        // CALCULATE COVARIANCE
        Matrix verify = new Matrix(3,3);
        verify.add(0,0, 1);
        verify.add(1,0, 2);
        verify.add(2,0, 3);
        verify.add(0,1, 4);
        verify.add(1,1, 5);
        verify.add(2,1, 6);
        verify.add(0,2, 7);
        verify.add(1,2, 8);
        verify.add(2,2, 99);


        var covariance = MathEx.cov(verify.colMeans(), verify.rowMeans());
        System.out.println("The covariance is " + covariance);
        // DRAW  THE DATA SET

        ScatterPlot column = ScatterPlot.of(verify.toArray(), Color.BLUE);
        var figure = column.figure();
        figure.setAxisLabels("X_values", "Y_value", "Z_VALUE");
        var pane = new FigurePane(figure);
        pane.window();
        // varargs
        // var df = DataFrame.of(verify.toArray(), "AI", "SUPERAI", "HUMAN");






    }





    }



