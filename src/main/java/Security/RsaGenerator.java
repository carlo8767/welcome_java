package Security;

import javax.naming.ldap.PagedResultsControl;
import java.security.*;

public class RsaGenerator {



    protected void geneartePrivateKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        /*
            GENERATE A PAIR PRIVATE KEY
         */
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();


        /*
            CREATE MOCK OBJECT SIGNATURE
            https://docs.oracle.com/javase/tutorial/security/apisign/step3.html
         */
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(priv);
        // Must provide the data
        byte[] realSig = dsa.sign();
    }

}
