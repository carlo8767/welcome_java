package smile;


import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.io.Read;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ArtificialActivities {







        public static  String PATH_FILE =  "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/smile/drink.csv";


        public Optional<DataFrame> readActivities() {

            try {

                CSVFormat csvFormat = CSVFormat.Builder.create()
                        .setDelimiter(",")
                        .setHeader()
                        .setIgnoreEmptyLines(true)
                        .setSkipHeaderRecord(false)
                        .get();

                DataFrame df = Read.csv(
                        PATH_FILE,
                        csvFormat
                        //  schema Optional assign a schema
                );
                Optional<DataFrame> optionalDataFrame = Optional.ofNullable(df);
                if(optionalDataFrame.isPresent()){
                  //  df = optionalDataFrame.get().factorize("species", "island", "sex");
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



    public void conditionalProbabilities(DataFrame df) {

            try {
                if(Objects.requireNonNull(df).isEmpty()){
                    int[] featureLockdown = df.column(0).toIntArray();
                    int[] label = df.column(4).toIntArray();

                    Map<String, Integer> mapNodeTrue = new HashMap<>();
                    Map<String, Integer> mapNodeFalse = new HashMap<>();

                    int count00 = 0;
                    int count01 = 0;
                    int count11 = 0;
                    int count10 = 0;


                    for (int i = 0; i < featureLockdown.length; i++) {
                        if (featureLockdown[i] == 0 && label[i] == 0) {
                            count00 += 1;
                            mapNodeFalse.put("NodeFalseOutcomeFalse", count00);
                        } else if (featureLockdown[i] == 0 && label[i] == 1) {
                            count01 += 1;
                            mapNodeFalse.put("NodeFalseOutcomeTrue", count01);
                        } else if (featureLockdown[i] == 1 && label[i] == 1) {
                            count11 += 1;
                            mapNodeTrue.put("NodeTrueOutcomeTrue", count11);
                        } else if (featureLockdown[i] == 1 && label[i] == 0) {
                            count10 += 1;
                            mapNodeTrue.put("NodeTrueOutcomeFalse", count10);
                        }

                    }

                    mapNodeFalse.put("size", count00 + count01);

                    mapNodeTrue.put("size", count11 + count10);

                }
                else{
                    throw new NullPointerException();
                }

            }

            catch (NullPointerException e) {
                System.out.println("null pointer exception");
            }



    }



    public double inpurity_level(   Map<String, Integer> mapNodeTrue,  Map<String, Integer> mapNodeFalse){

        try {
            double inpurity_index_false = 0.0;
            double inpurity_index_true = 0.0;

            double sizeNodesFalse = mapNodeFalse!=null?  mapNodeFalse.get("size"):0.0;
            double sizeNodesTrue =  mapNodeTrue != null? mapNodeTrue.get("size"):0.0;
            double sizeNodes = sizeNodesFalse+sizeNodesTrue;
            boolean nullMapTrue = false;
            boolean nullMapFalse = false;
            if(mapNodeFalse!=null){

                inpurity_index_false+= mapNodeFalse.entrySet().stream()
                        .filter(x-> !x.getKey().equals("size"))
                        .map(values ->
                            Math.pow((double)values.getValue()/mapNodeFalse.get("size"),2)
                        )
                        .reduce(0.0, Double::sum);

                inpurity_index_false = 1.000 - inpurity_index_false;
                inpurity_index_false = inpurity_index_false * ((double )mapNodeFalse.get("size") / sizeNodes) ;

            }
            else {
                nullMapFalse = true;
            }


            if(mapNodeTrue!=null) {
                inpurity_index_true+= mapNodeTrue.entrySet().stream()
                        .filter(x-> !x.getKey().equals("size"))
                        .map(values ->
                                Math.pow((double)values.getValue()/mapNodeTrue.get("size"),2)
                        )
                        .reduce(0.0, Double::sum);

                inpurity_index_true = 1.000 - inpurity_index_true;
                inpurity_index_true = inpurity_index_true * ((double)mapNodeTrue.get("size") / sizeNodes);

                return inpurity_index_true + inpurity_index_false;
            }
            else {
                nullMapTrue = true;
            }

            if (nullMapFalse && nullMapTrue == true){
                throw  new NullPointerException();

            }

        }

        catch (NullPointerException e){
            System.out.println("Maps Nodes are null");
            return  -1.0;
        }
        return  -1.0;
    }


}








