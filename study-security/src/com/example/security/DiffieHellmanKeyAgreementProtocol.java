package com.example.security;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class DiffieHellmanKeyAgreementProtocol {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		// 1. Use the values to generate a key pair
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
		AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
		paramGen.init(1024);

		// Generate the parameters
		AlgorithmParameters params = paramGen.generateParameters();
		DHParameterSpec dhSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);
		keyGen.initialize(dhSpec);
		KeyPair keypair = keyGen.generateKeyPair();

		// 2. Get the generated public and private keys
		PrivateKey privateKey = keypair.getPrivate();
		PublicKey publicKey = keypair.getPublic();

		// 3. Send the public key bytes to the other party...
		byte[] publicKeyBytes = publicKey.getEncoded();

		// 4. Retrieve the public key bytes
		// of the other party
		publicKeyBytes = null;

		// 5. Convert the public key bytes into a X.509 PublicKey object
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory keyFact = KeyFactory.getInstance("DH");
		publicKey = keyFact.generatePublic(x509KeySpec);

		// 6. Prepare to generate the secret key with the private key and
		// public key of the other party
		KeyAgreement ka = KeyAgreement.getInstance("DH");
		ka.init(privateKey);

		ka.doPhase(publicKey, true);

		// 7. Specify the type of key to generate;
		String algorithm = "DES";

		// 8. Generate the secret key
		SecretKey secretKey = ka.generateSecret(algorithm);

		// 9. Use the secret key to encrypt/decrypt data;

	}
}
