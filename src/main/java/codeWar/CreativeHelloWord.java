

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;

import java.util.Set;

public class CreativeHelloWord {

    public static int solution(int number) {
        Set<Integer> sum = new HashSet<Integer>();
        for (int i = 1; i < number; i++) {
            int three_multiply = 3 * i;
            int five_multiply  = 5 * i;
            if(three_multiply < number){
                sum.add(three_multiply);
            }
            if(five_multiply < number){
                sum.add(five_multiply);
            }
            if(three_multiply >= number &&  five_multiply >= number ){
                break;
            }
        }
        return sum.stream()
                .reduce(0, Integer::sum);
        }
    }


    public String greet(){
        String negative = "!dlrow olleh";
        StringBuilder positive = new StringBuilder();
        for(int i=negative.length()-1; i>=0; i--){
            positive.append(negative.charAt(i));
        }
        return positive.toString();
    }


    public static void main(String [] args) {
        int value = CreativeHelloWord.solution(10);
        Assertions.assertEquals(23,value);
        for(int i=0; i<5; i++){
            System.out.println("I value is "+ i);
            int count =0;
            for(int j=0; j<5; j++){

                System.out.println("J is "+j);
                for(int n=0; n<5; n++){
                    count += 1;
                    System.out.println("count value is "+ count);
                }
            }
        }


}
