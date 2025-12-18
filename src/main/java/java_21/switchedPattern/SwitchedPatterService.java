package java_21.switchedPattern;

public class SwitchedPatterService {



    public String recordPattern (SwitchedPatternInterface switchedPatternInterface){

        return  switch(switchedPatternInterface) {
            case SwitchedPatternA switchedPatternA ->  switchedPatternA.definePattern();
            case SwitchedPatternB switchedPatternB -> switchedPatternB.definePattern();
            case null -> throw  new NullPointerException();
            default -> "Not Define";
        };

    }
}
