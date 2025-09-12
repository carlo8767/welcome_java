package functional_interface;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExtendInheritance extends  Inheritance {

    public boolean check(String sentence) {
        Optional<String> n = Optional.ofNullable(sentence);
        if (n.isEmpty()) {
          return  false;
        }
            String remove = sentence.toLowerCase().replaceAll("[\\d\\p{Punct}]", "");
            long value = remove.toLowerCase()
                    .chars()
                    .filter(Character::isLetter)
                    .filter(x -> x >='a' && x <='z')
                    .distinct()
                    .count();
            return value == 26;
        }




    public  static  void main (String [] args){


        List<String> arr = new ArrayList<>();
        List<String>   pp = arr;
        pp.add("f");
        ExtendInheritance s = new ExtendInheritance();
        ExtendInheritance p = new ExtendInheritance();
        boolean verify = arr == pp ? true:false;
        boolean fsa = arr.size() == pp.size() ? true:false;
        System.out.println(verify);
        System.out.println(fsa);



        boolean correct = s.check("fasdfdsafasdf");




        Inheritance st = new Inheritance();
        assertFalse(st instanceof  ExtendInheritance);

        boolean values = st instanceof Inheritance;
    }
}
