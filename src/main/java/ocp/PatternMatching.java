package ocp;

public class PatternMatching {





    // EXCLUDE FOR ENUM
    public String  exhaustiveSwitch(Status status) {
        String o = "";
        switch (status){
            case LOW -> o = "low";
            case HIGH -> o = "high";
        }
        return  o;
    }




    public static  void main (String[] args){

        PatternMatching patternMatching = new ExtendPattern();
        ExtendPattern superType = new ExtendPattern();

        if(patternMatching instanceof ExtendPattern){
            System.out.println("Is an instance");
        }
        if(null instanceof PatternMatching pat){
            System.out.println("Is an instance");
        }

        Object obj = "Hello";
        String result = "";
        // STATEMENT REQUIRED DEFAULT
        switch (obj) {
            case String  str -> result = "Is a string";
            case Integer inte -> result = "Is an integer";
            default -> result = "It s a default";
        }

        // EXPRESSION
        // STATEMENT REQUIRED DEFAULT
        String betterSwitch = switch (obj){
            case null -> "is null";
            case String str -> "is a string" ;
            default -> "is default";
        };


        Number num = 1949;             // Superclass reference denotes a subtype object.
        String result4 = switch (num) {
            case Integer ii when ii % 2 == 0 -> "Even number: " + ii; // (1)
            case Integer ii                  -> "Odd number: " + ii;  // (2)
            default              -> "Not an Integer"; // (3) Compile-time error if omitted.
        };








    }

}


