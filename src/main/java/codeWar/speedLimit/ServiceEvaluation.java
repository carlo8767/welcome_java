package codeWar.speedLimit;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceEvaluation {

    public  Map<Integer, String>  extractIndicator (String indication){
        // DOES NOT MATTER T
        Map<Integer, String> mapCorrectValue = new HashMap<>();
        // CAPTURING GROUP REGEST
        Pattern pattern = Pattern.compile("(\\d+)\\s+(\\S+)");
        Matcher matcher = pattern.matcher(indication);
        while (matcher.find()) {
            int time = Integer.parseInt(matcher.group(1));
            String value = matcher.group(2);
            mapCorrectValue.put(time, value);
        }
        return  mapCorrectValue;
    }

    public void verifyAction (String [] action, Map<Integer, String> mapCorrectValue) {

        // CREATE LIMIT ARRAY FROM FIRST CORRECT VALUE UNTIL TO THE NEXT
        // NESTED LOOP



    }



    public static void main(String[]args){


        int x = 5;
        System.out.println(x+1 + ++x);
        System.out.println(x);

        SpeedLimit speedLimit = SpeedLimit.STOP;
        ServiceEvaluation ns = new ServiceEvaluation();
        String indication = "t=1 SL30 ; t=7 YIELD2 ; t=13 SL90 ; t=15 TURNR; t=21 REDLIGHT8 ; t=30 STOP;";
        String actions = "29 28 28 27 29 27 0 0 0 0 23 24 67 72 78> 85 87 86 84 89 0 0 0 0 0 0 0 0 25 0 0 0 34 56";
        String [] values   = actions.split(" ");
        Map<Integer, String> mapCorrectValue =  ns.extractIndicator(indication);
    }
}
