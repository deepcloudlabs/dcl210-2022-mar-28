package com.example.networking;

import java.net.Socket;
import java.util.stream.IntStream;

public class SimpleClient {

	public static void main(String[] args) throws Exception {
		//Stream.of(1,2,3,4,5,6,7,8)
		IntStream.range(0, 1024)
		      .parallel()
		      .forEach( SimpleClient::createConnection );
	}

	private static void createConnection(int i)  {
		String host= "localhost";
		int port= 2016;
		Socket socket;
		try {
			socket = new Socket(host,port);
			byte[] buffer= new byte[1024];
			int read= socket.getInputStream().read(buffer);
			if (read>0)
			   System.out.println(new String(buffer, 0, read));
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
