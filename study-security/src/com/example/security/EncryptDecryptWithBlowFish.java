package com.example.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
*
* @author Binnur Kurt (binnur.kurt@gmail.com)
*/
public class EncryptDecryptWithBlowFish {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java EncryptDecryptWithBlowFish "
                    + "<Enter text> ");
            System.exit(1);
        }
        String testData = args[0];
        System.out.println("Generating a Blowfish key...");

        // Create a key using "Blowfish"
        KeyGenerator myKeyGenerator = KeyGenerator.getInstance("Blowfish");
        myKeyGenerator.init(128);   // specifying keysize as 128
        Key myKey = myKeyGenerator.generateKey();
        System.out.println("Key generation Done.");

        // Create a cipher using the key
        Cipher myCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        myCipher.init(Cipher.ENCRYPT_MODE, myKey);
        byte[] testBytes = testData.getBytes();

        // Perform  encryption
        byte[] encryptedText = myCipher.doFinal(testBytes);

        // Printing out the encrypted data
        System.out.println("Encryption Done:" + new String(encryptedText));

        // Initialize the cipher for DECRYPTION mode
        myCipher.init(Cipher.DECRYPT_MODE, myKey);

        // Performing decryption
        byte[] decryptedText = myCipher.doFinal(encryptedText);
        // Printing out the decrypted data
        System.out.println("Decryption Done:" + new String(decryptedText));
    }
}
