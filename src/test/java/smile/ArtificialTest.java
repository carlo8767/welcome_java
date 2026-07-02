package smile;

import org.junit.jupiter.api.Test;
import smile.base.cart.DecisionNode;
import smile.base.cart.Node;
import smile.data.DataFrame;
import smile.data.vector.ValueVector;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ArtificialTest {



    @Test
    public void testLoadFile() {


        List<String> fa = new ArrayList<>();
        if(Objects.requireNonNull(fa).isEmpty()){
            System.out.println("is  empty");
        }
        fa.add("fas");
        if(Objects.requireNonNull(fa).isEmpty()){
            System.out.println("is not empty");
        }




        ArtificialActivities art = new ArtificialActivities();
        Optional<DataFrame> pt = art.readActivities();
        assertTrue(pt.isPresent());
        DataFrame df = pt.get();
        art.conditionalProbabilities(df);
        // HAVE A VECTOR OF VALUE
        ValueVector featureLockdown = df.column(0);
        ValueVector label = df.column(4);

        System.out.println(featureLockdown.dtype());
        // count number of value 0
        double values_0 = Arrays.stream(featureLockdown.toIntArray()).filter(x-> x == 0).count();
        double values_1 = Arrays.stream(featureLockdown.toIntArray()).filter(x-> x == 1).count();

        String rootName = df.schema().field(0).name();

        // DecisionNode sd leaf node

        }

    public void conditionalProbabilities(DataFrame df){

        int [] featureLockdown = df.column(0).toIntArray();
        int [] label = df.column(4).toIntArray();

        Map <String, Integer> mapNodeTrue = new HashMap<>();
        Map <String, Integer> mapNodeFalse = new HashMap<>();

        int count00 = 0;
        int count01 = 0;
        int count11 = 0;
        int count10 = 0;

        /*PREPARE VALUES GINI INPURITY  */
        for (int i = 0; i < featureLockdown.length; i++) {
            if (featureLockdown[i] == 0 && label[i] == 0) {
                count00+=1;
                mapNodeFalse.put("NodeFalseOutcomeFalse",count00);
            }
            else if (featureLockdown[i] == 0 && label[i] == 1) {
                count01+=1;
                mapNodeFalse.put("NodeFalseOutcomeTrue",count01);
            }
            else if (featureLockdown[i] == 1 && label[i] == 1) {
                count11+=1;
                mapNodeTrue.put("NodeTrueOutcomeTrue",count11);
            }
            else if (featureLockdown[i] == 1 && label[i] == 0) {
                count10+=1;
                mapNodeTrue.put("NodeTrueOutcomeFalse",count10);
            }

        }

        mapNodeFalse.put("size", count00+count01);

        mapNodeTrue.put("size", count11+count10);

        double inpurity_index_false = 0.0;
        double inpurity_index_true = 0.0;

        double sizeNodesFalse = mapNodeFalse!=null?  mapNodeFalse.get("size"):0.0;
        double sizeNodesTrue =  mapNodeTrue != null? mapNodeTrue.get("size"):0.0;
        double sizeNodes = sizeNodesFalse+sizeNodesTrue;


        sizeNodes = mapNodeFalse.get("size") + mapNodeTrue.get("size");
        double value = Math.pow(2,3);



        /*CALCULATION CONDITIONAL GINI INDEX */
        inpurity_index_false+= mapNodeFalse.entrySet().stream()
                .filter(x-> !x.getKey().equals("size"))
                .map(values ->
                        Math.pow((double)values.getValue()/mapNodeFalse.get("size"),2)
                )
                .reduce(0.0, Double::sum);

        double previous = inpurity_index_false;
        inpurity_index_false =  0.0;

        for (Map.Entry<String,Integer> m : mapNodeFalse.entrySet()){
            if(m.getKey().equals("size")){
                continue;
            }
            inpurity_index_false+= Math.pow((double) m.getValue() /mapNodeFalse.get("size"),2);

        }
        assertEquals(previous, inpurity_index_false);

        inpurity_index_false =1.000 - inpurity_index_false;


        /*CALCULATION GINI INPURITY */
        double class_prob  = mapNodeFalse.get("size")/sizeNodes;
        inpurity_index_false = class_prob * inpurity_index_false;

        for (Map.Entry<String,Integer> m : mapNodeTrue.entrySet()){
            if(m.getKey().equals("size")){
                continue;
            }
            inpurity_index_true += Math.pow((double) m.getValue() /mapNodeTrue.get("size"),2);
        }

        inpurity_index_true =1.000 - inpurity_index_true;
        double class_prob_true  = mapNodeTrue.get("size")/sizeNodes;
        inpurity_index_true += inpurity_index_true * class_prob;

        double inpurity_level = inpurity_index_true + inpurity_index_false;


    }


    }

