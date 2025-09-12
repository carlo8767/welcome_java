package codeWar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerifyPinTest {




    @Test
    public void validPins() {
        assertEquals(true, VerifyPin.SecondValidatePin("1234"));
        assertEquals(true, VerifyPin.validatePin("0000"));
        assertEquals(true, VerifyPin.validatePin("1111"));
        assertEquals(true, VerifyPin.validatePin("123456"));
        assertEquals(true, VerifyPin.validatePin("098765"));
        assertEquals(true, VerifyPin.validatePin("000000"));
        assertEquals(true, VerifyPin.validatePin("090909"));
    }

    @Test
    public void nonDigitCharacters() {
        assertEquals(false, VerifyPin.SecondValidatePin("a234"));
        assertEquals(false, VerifyPin.validatePin(".234"));
    }

    @Test
    public void invalidLengths() {
        assertEquals(false, VerifyPin.validatePin("1"));
        assertEquals(false, VerifyPin.validatePin("12"));
        assertEquals(false, VerifyPin.validatePin("123"));
        assertEquals(false, VerifyPin.validatePin("12345"));
        assertEquals(false, VerifyPin.validatePin("1234567"));
        assertEquals(false, VerifyPin.validatePin("-1234"));
        assertEquals(false, VerifyPin.validatePin("1.234"));
        assertEquals(false, VerifyPin.validatePin("00000000"));
    }

}