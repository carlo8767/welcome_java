package codeWar;

import java.util.Optional;
import java.util.Scanner;

public class VerifyPin {

    // THIS METHOD SHOULD ONLY VALIDATE THE PIN BETWEEN 4 AND 6
    public static boolean validatePin(String pin) {

        String remove = pin.toLowerCase().replaceAll("[^0-9]", "X");
        long value = remove.chars().filter(x -> x == 'X').count();
        if (value > 0) {
            return false;
        } else if (remove.length() == 4) {
            return true;
        } else return remove.length() == 6;
   }
    public static boolean SecondValidatePin(String pin) {
      Optional<String> verify = Optional.ofNullable(pin);
      if(verify.isEmpty()){
         return  false;
      }
      else {
          return pin.matches("\\d{4}|\\d{6}");
      }
    }


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

}
