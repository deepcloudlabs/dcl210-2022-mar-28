package com.example.security;

import java.security.Provider;
import java.security.Security;

public class ListAlgorithms {

	public static void main(String[] args) {
		// String serviceType= "Cipher";
		// String serviceType= "MessageDigest";
		// String serviceType= "KeyGenerator";
	 // String serviceType= "KeyStore";
		// String serviceType= "Signature";
		String serviceType= "SecureRandom";
		
		Provider[] providers= Security.getProviders();
		for (Provider provider: providers){
			for (Object key: provider.keySet()){
				String name= (String) key;
				String serviceName= name.split(" ")[0];
				if (serviceName.startsWith(
						serviceType.concat("."))){
					System.out.println(serviceName+": "+provider.getName());					
				} else if(serviceName.startsWith(
						"Alg.Alias.".concat(serviceType)
						            .concat("."))){
					System.out.println(serviceName+": "+provider.getName());					
				}					
			}
		}

	}

}
