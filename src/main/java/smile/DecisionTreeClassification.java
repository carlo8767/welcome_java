package smile;

import org.apache.commons.csv.CSVFormat;
import smile.data.Collectors;
import smile.data.DataFrame;
import smile.data.type.DataTypes;
import smile.data.type.StructField;
import smile.data.type.StructType;
import smile.io.Read;

import java.util.Optional;

public class DecisionTreeClassification {

    public static  String PATH_FILE = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/smile/penguins.csv";



    public Optional<DataFrame> loadFile(){

        try {
            /*
             We apply with the CSVFormat some parameter to filter the data ( more information: https://haifengl.github.io/data.html)
             https://haifengl.github.io/api/java/smile/classification/package-summary.html
             */
            // 1. Setting parameter OPTIONAL for the loading file
            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setDelimiter(",")
                    .setHeader()
                    .setIgnoreEmptyLines(true)
                    .setSkipHeaderRecord(true)
                    .get();
            //2. Extract Data
            DataFrame df = Read.csv(
                    PATH_FILE,
                    csvFormat
                //  schema Optional assign a schema
            );
            // Convert for Categorical Columns
            // ordinal methods factorize string values and store them as integral values internally, which are more efficient

            // verify conversion
            Optional<DataFrame> optionalDataFrame = removeRowFilter(df, "NA");
            if(optionalDataFrame.isPresent()){
                df = optionalDataFrame.get().factorize("species", "island", "sex");
                return Optional.of(df);
            }
            else {
                return optionalDataFrame;
            }
        }
        catch (Exception e){
            System.out.println("upload file error" + e);
        }
        return  Optional.empty();
    }


    public Optional<DataFrame> removeRowFilter (DataFrame dataFrame, String valueRemove){
        DataFrame ds = null;
        var schema = new StructType(
                new StructField("penguins", DataTypes.IntType),
                new StructField("species", DataTypes.StringType),
                new StructField("island", DataTypes.StringType),
                new StructField("bill_length_mm", DataTypes.DoubleType),
                new StructField("bill_depth_mm", DataTypes.DoubleType),
                new StructField("flipper_length_mm", DataTypes.DoubleType),
                new StructField("body_mass_g", DataTypes.DoubleType),
                new StructField("sex", DataTypes.StringType),
                new StructField("year", DataTypes.DoubleType)
        );

        if (dataFrame == null || dataFrame.isEmpty()) {
            System.out.println("DataFrame is null or empty, returning unchanged.");
            return Optional.empty();
        }
        // Use stream to filter rows with nulls
        ds = dataFrame.stream()
                .filter(row -> {
                    for (int j = 0; j < dataFrame.ncol(); j++) {
                        if (row.isNullAt(j)) {
                            return false;
                        }
                        if(row.get(j).equals(valueRemove)){
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toDataFrame(schema));

        StructType newSchema = ds.schema();
        System.out.println(newSchema);
        return Optional.of(ds);
    }


    static void main (String[] args) {
        DecisionTreeClassification d = new DecisionTreeClassification();
        d.loadFile();
    }



}
