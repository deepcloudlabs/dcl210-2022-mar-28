package com.example.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class BlockChiper {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        SecretKey key = keygen.generateKey();
        String words=null;
        //  Encryption using DES, ECB Mode and PKCS5Padding Scheme
        try {
            // 1. Create the cipher using DES algorithm
            //    ECB Mode and PKCS5Padding scheme
            Cipher myCipher =
                    Cipher.getInstance("DES");

            // 2. Initialize the Cipher
            myCipher.init(Cipher.ENCRYPT_MODE, key);

            //  3.  Represent the Plaintext
            byte[] plaintext = args[0].getBytes();

            // 4. Encrypt the Plaintext
            byte[] myciphertext = myCipher.doFinal(plaintext);

            // 5. Print the cipher text as String
            words=new String(myciphertext);
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }


//  Decryption using DES, ECB Mode and PKCS5Padding Scheme

        try {

            // 1. Create the cipher using DES algorithm
            //    ECB Mode and PKCS5Padding scheme
            Cipher myCipher =
                    Cipher.getInstance("DES/ECB/PKCS5Padding");

            // 2. Get the ciphertext
            byte[] ciphertext = words.getBytes();

            // 3. Initialize the cipher for decryption
            myCipher.init(Cipher.DECRYPT_MODE, key);
            myCipher.update(ciphertext,0,ciphertext.length/8*8-1);
            // 4. Decrypt the ciphertext
            byte[] plaintext = myCipher.doFinal();

            // 5. Return the plaintext as string
            System.out.println(new String(plaintext));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
