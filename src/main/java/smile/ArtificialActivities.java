package smile;


import org.apache.commons.csv.CSVFormat;
import smile.classification.DecisionTree;
import smile.data.DataFrame;
import smile.io.Read;
import smile.util.Index;

import java.util.*;

public class ArtificialActivities {






        private ArtificialTree artificialTree;


        public ArtificialActivities(){
            this.artificialTree = new ArtificialTree();
        }



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
                Map<Integer, String> mapTree = new TreeMap<>();
                var topDown = df;
                double lowestGiniInpurty = 1;
                int index_lowestGiny = -1;
                if(!Objects.requireNonNull(topDown).isEmpty()) {
                    for (int in = 0; in < topDown.ncol()-1; in++) {
                        int[] featureLockdown = topDown.column(in).toIntArray();
                        int[] label = topDown.column(topDown.ncol() - 1).toIntArray();
                        Map<String, Integer> mapNodeTrue = new HashMap<>();
                        Map<String, Integer> mapNodeFalse = new HashMap<>();
                        int count00 = 0;
                        int count01 = 0;
                        int count11 = 0;
                        int count10 = 0;

                        /*CALCULATION GINI INPURITY */
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

                        /*GINI INPURITY */
                        double giniInpurity = inpurity_level(mapNodeTrue, mapNodeFalse);

                        /*STORE GINI IPURITY WITH THE NAME FIELD */
                        if (giniInpurity < lowestGiniInpurty){
                            lowestGiniInpurty = giniInpurity;
                            index_lowestGiny = in;
                        }
                        mapTree.put(in,topDown.schema().field(in).toString());
                        System.out.println(mapTree.get(index_lowestGiny));

                        // BUILD THE TREE
                    }

                    List<String> rootNode = this.artificialTree.getRootNode();
                    if(Objects.requireNonNull(rootNode).isEmpty()) {

                        this.artificialTree.rootNode.add(mapTree.get(index_lowestGiny));

                       var w  = df.schema().field(0).name();
                        var wa  = df.schema().field(4).name();
                       // NEW BASE
                       var ns = df.apply(w,wa);
                       System.out.println(w);
                       buildTree(ns, df);
                    }

                }
                else{throw new NullPointerException();}
               }
            catch (NullPointerException e) {
                    System.out.println("null pointer exception");
                }

        }


    public double inpurity_level(  Map<String, Integer> mapNodeTrue,  Map<String, Integer> mapNodeFalse){

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


    public void buildTree(DataFrame base, DataFrame root){

        int[] featureLockdown = base.column(0).toIntArray();
        int[] label = base.column(base.ncol() - 1).toIntArray();
            Map<String, Integer> mapNodeTrue = new HashMap<>();
        Map<String, Integer> mapNodeFalse = new HashMap<>();
        int count00 = 0;
        int count01 = 0;
        int count11 = 0;
        int count10 = 0;

        /*CALCULATION GINI INPURITY */
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
           int  [] array_values = {count00, count01,count11, count10};
           if (count00 ==0){
               System.out.println("is pure");
           } else if (count01 ==0) {
               System.out.println("is pure");
           }
           if (count10 ==0){
                System.out.println("is pure");
            } else if (count11 ==0) {
                System.out.println("is pure");
            }

        }




        mapNodeFalse.put("size", count00 + count01);

        mapNodeTrue.put("size", count11 + count10);
    }






}








