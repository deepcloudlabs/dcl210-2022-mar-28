package com.example.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.example.domain.Circle;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class TestSealedObject {

	public static void main(String[] args) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/data", "circle.ser")));
			Cipher myCipher = Cipher.getInstance("DES");
			myCipher.init(Cipher.ENCRYPT_MODE, key);
			Circle circle = new Circle(0.0, 0.0, 3.14, "blue", 3);
			SealedObject mySealedObject = new SealedObject(circle, myCipher);
			oos.writeObject(mySealedObject);
			oos.close();
			// To deserialize a Sealed object and retrieve its contents
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/data", "circle.ser")));
			SealedObject mso = (SealedObject) ois.readObject();
			Cipher mc = Cipher.getInstance("DES");
			mc.init(Cipher.DECRYPT_MODE, key);
			circle = (Circle) mso.getObject(mc);
			System.out.println("Sealed object: " + circle);
			ois.close();
		} catch (Exception ex) {
			Logger.getLogger(TestSealedObject.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
