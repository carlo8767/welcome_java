package smile;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import smile.classification.DecisionTree;
import smile.data.DataFrame;
import smile.data.formula.Formula;

import smile.data.formula.Term;
import smile.data.formula.Terms;
import smile.data.type.StructField;
import smile.data.type.StructType;
import smile.data.vector.ValueVector;
import smile.datasets.WeatherNominal;
import smile.math.MathEx;
import smile.plot.swing.ScatterPlot;
import smile.util.Index;
import smile.validation.metric.Accuracy;
import smile.validation.metric.Sensitivity;
import smile.validation.metric.Specificity;

import javax.swing.text.View;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static smile.base.cart.SplitRule.ENTROPY;


// https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html
@ExtendWith(MockitoExtension.class)
class DecisionTreeClassificationTest {

    // Ensure you close the resources
    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void testVerifyLoadFile () {
     DecisionTreeClassification decisionTree = new DecisionTreeClassification();
     Optional<DataFrame> optional = decisionTree.loadFile();
     assertTrue(optional.isPresent());
    }

    @Test
    public void readData() throws IOException, ParseException {
        DecisionTreeClassification decisionTree = new DecisionTreeClassification();
        Optional<DataFrame> optional = decisionTree.loadFile();
        /*
        List<ValueVector>  listColumns = optional.get().columns();
        listColumns.forEach(System.out::println); Return the column
        */
        // Specifynamecolumn https://haifengl.github.io/api/java/smile/io/Arff.html ARFF
        // SET THE FORMULA TARGET AND FEATURES
        var verify  = Formula.of("species", "island", "sex", "bill_length_mm", "flipper_length_mm", "body_mass_g" );
        System.out.println("Formula before fit: " + verify);
        // You can set up the option for the decision
        // nodeSize is minimun size
        //  TARGET AND FEATURE Formula.lhs("class").rhs("feature1", "feature2");
        var optionsDecision = new DecisionTree.Options(ENTROPY, 3, 2, 2 );
        if(optional.isPresent()){
            var number_columns = optional.get().columns();
            // m number of row and number of columns
            // I can get the number of row

            var value = optional.get().nrow();
            int[] perm = MathEx.permutate(value);
            int trainSize = value*70/100;
            int testSize = value*30/100;

            int[] trainIdx = Arrays.copyOfRange(perm, 0, trainSize);
            int[] testIdx  = Arrays.copyOfRange(perm, 0, testSize);

            // Select rows by index
            DataFrame train = optional.get().get(Index.of(trainIdx));
            DataFrame testingData  = optional.get().get(Index.of(testIdx));

            System.out.println("Training rows: " + train.nrow());
            System.out.println("Test rows: " + testingData.nrow());

            DecisionTree sd = DecisionTree.fit(verify, testingData, optionsDecision);
            System.out.println(sd); // prints tree structure
            int[] prediction = sd.predict(testingData);
            // Return The value
            int[] truth = testingData.column("species").toIntArray();
            double accuracy = Accuracy.of(truth, prediction);
            //double sensitivity = Sensitivity.of(truth,prediction);
            // double specificity = Specificity.of(truth,prediction);
            System.out.println("Accuracy = " + accuracy);
            //System.out.println("Sensitivity = " + accuracy);
            // System.out.println("Specificity = " + accuracy);
        }

 /*
        TOMORROW HOW TO CONVERT THE DATA DONE SCHEMA
        HOW TO DIVIDE THE DATA TRAINING AND TEST  MathEx.q1 https://haifengl.github.io/api/java/smile/math/MathEx.html
            int n = training.size();
            int[] index = MathEx
.permutate(n);
            and use selectIndex
            var value = optional.get().nrow();
            int training = value*70/100;
            int test = value*30/100;
            var dfTraining = DataFrame.of(MathEx.randn(training, number_columns.size()));


        HOW  TO PRINT THE TREE
        HOW REMOVE COLUMN THAT YOU DO NOT WANT DONE DROP METHOD
        DATA FRAME https://haifengl.github.io/data.html
         */

        //
        //

    }
}



