package com.example.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class StudyAES {

	public static void main(String[] args) throws Exception {
		if (args[0].equals("-genkey")){
			KeyGenerator keygen= KeyGenerator.getInstance("AES");
			SecureRandom random= new SecureRandom();
			keygen.init(random);
			SecretKey secret= keygen.generateKey();
			FileOutputStream out = new FileOutputStream(args[1]);
			ObjectOutputStream oos= new ObjectOutputStream(out);
			oos.writeObject(secret);
			out.close();
			return;
		}
		int mode=0; 
		// -encrypt important.pdf important_enc.pdf key.dat
		if (args[0].equals("-encrypt")){
			mode= Cipher.ENCRYPT_MODE;
		} else
		if (args[0].equals("-decrypt")){
			mode= Cipher.DECRYPT_MODE;
		}
		FileInputStream in = new FileInputStream(args[3]);
		ObjectInputStream ois= new ObjectInputStream(in);
		SecretKey secret= (SecretKey) ois.readObject();
		in.close();
		InputStream is = new FileInputStream(args[1]);
		OutputStream os = new FileOutputStream(args[2]);
		Cipher cipher= Cipher.getInstance("AES");
		cipher.init(mode, secret);
		crypt(is,os,cipher);
	}

	private static void crypt(InputStream is, OutputStream os, Cipher cipher) throws Exception {
		int inBlockSize= cipher.getBlockSize();
		System.out.println(inBlockSize);
		int outBlockSize= cipher.getOutputSize(inBlockSize);
		byte[] inBuffer= new byte[inBlockSize];
		byte[] outBuffer= new byte[outBlockSize];
		int read=0;
		boolean more= true;
		while (more){
			read= is.read(inBuffer);
			if (read==inBlockSize){
				int outLength= cipher.update(inBuffer,0,inBlockSize,outBuffer);
				os.write(outBuffer,0,outLength);
			} else {
				more= false;
			}
		};
		if (read>0){
			outBuffer= cipher.doFinal(inBuffer, 0, read);	
		} else {
			outBuffer= cipher.doFinal();				
		}
		os.write(outBuffer);
		is.close();
		os.close();
	}

}
