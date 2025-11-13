package java_21;

import static java.util.FormatProcessor.FMT;

public class StringTemplate {

    // STRING IN JAVA USUALLY IS HARD TO READ

    public static void main (String [] args){
        // BEFORE
        StringBuilder str = new StringBuilder();
        str.append("String building old");
        // NEW
        int id = 0;
        String name = "String Template avoid sql injection";
        var template_string = STR."Product with id \{id} amd the name is \{name}";
        double x = 10.5, y = 20.6;
        String p = STR."\{x} * \{y} = \{x * y}";
        // Format ava.util.Formatter
        var template_formant = FMT."Product with od  \{id} amd the name is \{name}";
        System.out.println(template_string);

    }

}
