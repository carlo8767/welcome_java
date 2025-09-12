package codeWar.speedLimit;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DrivingTestEvaluator {
    static Map<String, BiConsumer<Integer, String>> rules = new HashMap();
    static int speedLimit = 0;
    static int indexLastSpeedLimit = 1;
    static int indexIndicationList = 0;
    static int indexActionList = 0;
    static int minorMistakes = 0;
    static boolean isEliminated = false;
    static int currentTime = 0;
    static int secondsInfringement = 0;
    static List<String> indicationList;
    static List<String> actionList;

    public static boolean evaluate(String indications, String actions) {
        clearState();

        indicationList = Arrays.stream(indications.split(";")).toList();

        actionList = Arrays.stream(actions.split(" ")).toList();

        refillRules();
        for (String indication : indicationList) {
            processIndication(indication);
        }
        return 3 > minorMistakes && !isEliminated;
    }

    private static void refillRules() {
        rules.put("STOP", (DrivingTestEvaluator::processStop));
        rules.put("SL", (DrivingTestEvaluator::processSpeedLimit));
        rules.put("YIELD", (DrivingTestEvaluator::processYield));
        rules.put("TURN", (DrivingTestEvaluator::processTurn));
        rules.put("REDLIGHT", (DrivingTestEvaluator::processRedLight));
    }

    private static void processIndication(String indication) {
        Pattern pattern = Pattern.compile("t=(\\d+)\\s+(\\S+)");
        Matcher matcher = pattern.matcher(indication);
        if (matcher.find()) {
            Integer time = Integer.valueOf(matcher.group(1));
            String instruction = matcher.group(2);
            rules.keySet().stream().filter(instruction::contains).findFirst()
                    .ifPresent(key -> rules.get(key).accept(time, indication));
        }
    }

    private static void processTurn(Integer t, String signal) {
        String direction = "";
        Pattern pattern = Pattern.compile("TURN([A-Z])");
        Matcher matcher = pattern.matcher(signal);
        if (matcher.find()) {
            direction = matcher.group(1);
        }
        if (actionList.get(t - 1).endsWith(">") || actionList.get(t - 1).endsWith("<")) {
            if (actionList.get(t - 1).endsWith(">") && direction.equals("L")
                    || actionList.get(t - 1).endsWith("<") && direction.equals("R"))
                minorMistakes++;
        } else {
            minorMistakes++;
        }
    }

    private static void processRedLight(Integer time, String signal) {
        Pattern pattern = Pattern.compile("REDLIGHT(\\d+)");
        Matcher matcher = pattern.matcher(signal);
        if (matcher.find()) {
            processCorrectlyStopped(time, Integer.valueOf(matcher.group(1)));
        }
    }

    private static void processYield(Integer time, String signal) {
        Integer secondsToStop = 0;
        Pattern pattern = Pattern.compile("YIELD(\\d+)");
        Matcher matcher = pattern.matcher(signal);
        if (matcher.find()) {
            secondsToStop += (2 * Integer.valueOf(matcher.group(1)));
            processCorrectlyStopped(time, secondsToStop);
        } else {
            if (Integer.parseInt(actionList.get(time - 1)) == 0)
                isEliminated = true;
        }

    }

    private static void processStop(Integer time, String signal) {
        Integer secondsToStop = 3;
        Pattern pattern = Pattern.compile("STOP(\\d+)");
        Matcher matcher = pattern.matcher(signal);
        if (matcher.find()) {
            secondsToStop += Integer.parseInt(matcher.group(1)) * 2;
        }
        processCorrectlyStopped(time, secondsToStop);

    }

    private static void processCorrectlyStopped(Integer time, Integer secondsToStop) {
        if (Integer.parseInt(actionList.get(time - 1)) > 0) {
            isEliminated = true;
            return;
        }

        while (secondsToStop != 0) {
            if (time > actionList.size() || Integer.parseInt(actionList.get(time - 1)) > 0) {
                isEliminated = true;
                return;
            }
            time++;
            secondsToStop--;
        }
    }

    private static void processSpeedLimit(Integer time, String signal) {
        Integer digits = 0;
        for (int i = indexLastSpeedLimit; i < time; i++) {
            String velocity = actionList.get(i);
            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher matcher = pattern.matcher(velocity);
            if (matcher.find()) {
                digits = Integer.parseInt(matcher.group(1));
            }

            if (digits > speedLimit) {
                secondsInfringement++;
            } else {
                secondsInfringement = 0;
            }
            if (secondsInfringement > 2)
                minorMistakes++;
        }
        Pattern pattern = Pattern.compile("SL(\\d+)");
        Matcher matcher = pattern.matcher(signal);
        if (matcher.find()) {
            speedLimit = Integer.valueOf(matcher.group(1));
        }
        indexLastSpeedLimit = time - 1;
    }

    private static void clearState() {
        speedLimit = 0;
        indexLastSpeedLimit = 1;
        indexIndicationList = 0;
        indexActionList = 0;
        minorMistakes = 0;
        isEliminated = false;
        currentTime = 0;
        secondsInfringement = 0;
    }

}