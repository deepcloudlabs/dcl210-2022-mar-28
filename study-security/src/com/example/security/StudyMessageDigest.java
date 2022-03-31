package com.example.security;

import java.math.BigInteger;
import java.security.MessageDigest;

public class StudyMessageDigest {

	public static void main(String[] args) throws Exception {
		MessageDigest md= MessageDigest.getInstance("SHA1");
		md.reset();
		md.update("e".getBytes());
		byte[] bytes= md.digest();
		BigInteger bi= new BigInteger(1,bytes);
		System.out.println(bi.toString(16));
	}

}
