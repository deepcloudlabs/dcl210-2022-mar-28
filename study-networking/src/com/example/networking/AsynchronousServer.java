package com.example.networking;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AsynchronousServer {
	private static final class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {
		@Override
		public void completed(AsynchronousSocketChannel client, Attachment attachment) {
			System.out.println("New connection!");
			attachment.setClient(client);
			attachment.setConnected(true);
			client.write(attachment.getBuffer(), attachment, attachment.getWriteHandler());
		}

		@Override
		public void failed(Throwable exc, Attachment attachment) {

		}
	}

	private static final class WriteHandler implements CompletionHandler<Integer, Attachment> {
		@Override
		public void completed(Integer result, Attachment attachment) {
			try {
				attachment.getClient().close();
				attachment.getBuffer().flip();
			} catch (Exception e) {
				e.printStackTrace();
			}
			attachment.setConnected(false);
			attachment.getServer().accept(attachment, attachment.getAcceptHandler());

		}

		@Override
		public void failed(Throwable exc, Attachment attachment) {
			System.err.println("An error has occurred: " + exc.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		String hostname = "localhost";
		int port = 2016;
		InetSocketAddress address = new InetSocketAddress(hostname, port);
		server.bind(address);
		Attachment attachment = new Attachment(server);
		CompletionHandler<Integer, Attachment> writeHandler = new WriteHandler();
		CompletionHandler<AsynchronousSocketChannel, Attachment> acceptHandler = new AcceptHandler();
		attachment.setAcceptHandler(acceptHandler);
		attachment.setWriteHandler(writeHandler);
		server.accept(attachment, acceptHandler);
		System.out.println("Server is running at port " + port);
		Thread.currentThread().join();
	}
}
