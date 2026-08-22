package Security;

import org.bouncycastle.jcajce.provider.digest.SHA1;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class HashSalt {



    public static void hashingSalt(String password) throws NoSuchAlgorithmException {

        // SELECTION HASH FAMILY
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        SecureRandom secureRandom = new SecureRandom();
        byte [] salt = new byte [32];
        secureRandom.nextBytes(salt);
        // STORE SOMEWHERE
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        HexFormat hex = HexFormat.of();
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : hashedPassword){
            stringBuilder.append(hex.toHexDigits(b));
        }
        System.out.println(stringBuilder.toString());

    }


















    public static String generateSHA256Hash(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hash computation
            byte[] encodedhash = digest.digest(input.getBytes());
            // salt

            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[32];
            System.out.println(salt.toString());
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // END SALT

            // Convert byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            HexFormat hex = HexFormat.of();
            byte b = 127;
            String byteStr = hex.toHexDigits(b);


            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, DigestException {
        hashingSalt("pass");
        hashingSalt("pass");
        generateSHA256Hash("pipp");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String inputString = "Hello World!";
        byte[] byteArrray = inputString.getBytes();
        md.update(byteArrray);


    }
}
