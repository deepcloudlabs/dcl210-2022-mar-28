package com.example.networking;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static final int POOL_SIZE = 256;
	private ServerSocket serverSocket;
	private int port = 2016;
	private ExecutorService threadPool;
	
	public Server() {
		try {
			serverSocket = new ServerSocket(port);
			threadPool= Executors.newFixedThreadPool(POOL_SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static class RequestHandler implements Runnable {
		private Socket socket;
		
		public RequestHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("Received a connection!");
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write("Hello".getBytes());
				outputStream.flush();
				Thread.sleep(1000);
				outputStream.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void service() {
		while (true) {
			try {
				Socket socket = serverSocket.accept(); 
				threadPool.submit(new RequestHandler(socket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server().service();
	}

}
