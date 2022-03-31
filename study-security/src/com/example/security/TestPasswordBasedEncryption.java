package com.example.security;

import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class TestPasswordBasedEncryption {

	public static void main(String[] args) throws Exception {
		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;

		// Encryption password is obtained
		String passwd;
		try (Scanner scanner = new Scanner(System.in);) {
			passwd = scanner.next();
			char[] password = passwd.toCharArray();

			// Salt
			byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee,
					(byte) 0x99 };

			// Iteration count
			int count = 20;

			// Create PBE parameter set
			pbeParamSpec = new PBEParameterSpec(salt, count);

			// Collect user password as char array, and convert
			// it into a SecretKey object, using a PBE key
			// factory.

			pbeKeySpec = new PBEKeySpec(password);
			keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			// Create PBE Cipher
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			// Initialize PBE Cipher with key and parameters
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

			// Text to encrypt
			String textForEncryption = "This is a test message";

			byte[] myText = textForEncryption.getBytes();

			// Encrypt the text using PBE Cipher
			byte[] ciphertext = pbeCipher.doFinal(myText);
			System.out.println("Ciphered Text: " + new String(ciphertext));

			// Decrypt the text using PBE Cipher
			pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
			byte[] orgMessageBytes = pbeCipher.doFinal(ciphertext);
			System.out.println("Ciphered Text: " + new String(orgMessageBytes));
		}
	}
}
