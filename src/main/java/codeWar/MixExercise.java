package codeWar;

import java.util.*;

public class MixExercise {




    // SMASHED A WORD
    public static String smash(String...  words) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<words.length; i++){
            if(i ==0 ){
               stringBuilder.append(words[i]);
            }
            else if(i == words.length -1){
                stringBuilder.append(" ");
                stringBuilder.append(words[i]);
            }
            else {
                stringBuilder.append(" ");
                stringBuilder.append(words[i]);
            }
        }
        return  stringBuilder.toString();
    }

    public static String smashImprove(String...  words) {
       if(Arrays.stream(words).anyMatch(Objects::isNull)){
           return "0";
       }
       else {
          return String.join(" ", words);
       }
    }





    // VERIFY GALLON LIMIT

    public static boolean zeroFuel(double distanceToPump, double mpg, double fuelLeft) {
        double reserveFuel = mpg * fuelLeft;
        return reserveFuel < distanceToPump;
    }
    /* FIGHT LETTER
    WE CAN USE TO CHAR ARRAY
     Alphabet war
    assertEquals("Right side wins!", Kata.alphabetWar("z"));
    assertEquals("Let's fight again!", Kata.alphabetWar("zdqmwpbs"));
    */
    public static String alphabetWar(String fight){

        int sumLeft = 0;
        int sumRight = 0;
        Map<String,Integer> leftSide = new HashMap<String, Integer>();
        leftSide.put("b",2);
        leftSide.put("s",1);
        leftSide.put("p",3);
        leftSide.put("w",4);

        Map<String,Integer> rightSide = new HashMap<String, Integer>();
        rightSide.put("m",4);
        rightSide.put("q",3);
        rightSide.put("d",2);
        rightSide.put("z",1);

        String array [] = fight.chars()
                .mapToObj(c -> String.valueOf((char)c))
                .sorted()
                .toArray(String[]::new);

        // BEGIN BATTLE
        for (String s : array) {
            if (leftSide.containsKey(s)) {
                sumLeft += leftSide.get(s);
            }
            else if (rightSide.containsKey(s)) {
                sumRight += rightSide.get(s);
            }
        }

        // VERIFY BATTLE
        if(sumLeft > sumRight){
            return "Left side wins!";
        } else if (sumRight > sumLeft) {
            return "Right side wins!";
        }
        else {
            return "Let's fight again!";
        }

    }

public static void main () {

        System.out.println(MixExercise.smashImprove(new String[] { "hello", null}));
        MixExercise.alphabetWar("zdqmwpbs");
}

}


