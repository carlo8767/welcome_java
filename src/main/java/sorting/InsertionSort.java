package sorting;

import java.util.Arrays;

public class InsertionSort {



    public static void main(String[] args){


        // INSERTION SORT
        int [] order = {49,55,6,5};
        for(int i=1; i<order.length;i++){
            int j = i;
                    while(j>0 && order[j-1]> order[j] ){
                        var tmp = order[j-1];
                        order [j-1] = order[j];
                        order [j] = tmp;
                        j = j-1;
                    }
        }
        Arrays.stream(order).forEach(x-> System.out.println(x));
        // Try to do from the beginning
        int [] orderTwo = {49,55,6,5};
        for(int i=orderTwo.length-1; i>0;i--){
            int j = i;
            while(j<orderTwo.length && orderTwo[j-1]> orderTwo[j] ){
                var tmp = orderTwo[j-1];
                orderTwo [j-1] = orderTwo[j];
                orderTwo [j] = tmp;
                j= j+1;
            }
        }

        Arrays.stream(orderTwo).forEach(x-> System.out.println(x));


    }



}
