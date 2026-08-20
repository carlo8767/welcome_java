package crypto;


import java.math.BigInteger;

public class DiffieHellmann {











    public static void main(String[] args) {

        BigInteger secretKey = BigInteger.valueOf(55);
        BigInteger generator = BigInteger.valueOf(4);
        BigInteger modulo = BigInteger.valueOf(6);
        var operations = (secretKey.multiply(generator));
        var second = (operations.mod(modulo));
        System.out.println(operations);







    }

}
