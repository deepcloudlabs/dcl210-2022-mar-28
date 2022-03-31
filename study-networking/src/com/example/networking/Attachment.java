package com.example.networking;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class Attachment {
	private AsynchronousServerSocketChannel server;
	private AsynchronousSocketChannel client;
	private ByteBuffer buffer;
	private boolean connected;
	private CompletionHandler<AsynchronousSocketChannel, Attachment> acceptHandler;
	private CompletionHandler<Integer, Attachment> writeHandler;
	
	public Attachment(AsynchronousServerSocketChannel server) {
		this.server = server;
		byte[] bytes = "Hello".getBytes();
		this.buffer= ByteBuffer.allocate(bytes.length);
		this.buffer.put(bytes);
		buffer.flip();
	}
	public AsynchronousServerSocketChannel getServer() {
		return server;
	}
	public void setServer(AsynchronousServerSocketChannel server) {
		this.server = server;
	}
	public AsynchronousSocketChannel getClient() {
		return client;
	}
	public void setClient(AsynchronousSocketChannel client) {
		this.client = client;
	}
	public ByteBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public CompletionHandler<AsynchronousSocketChannel, Attachment> getAcceptHandler() {
		return acceptHandler;
	}
	public void setAcceptHandler(CompletionHandler<AsynchronousSocketChannel, Attachment> acceptHandler) {
		this.acceptHandler = acceptHandler;
	}
	public CompletionHandler<Integer, Attachment> getWriteHandler() {
		return writeHandler;
	}
	public void setWriteHandler(CompletionHandler<Integer, Attachment> writeHandler) {
		this.writeHandler = writeHandler;
	}
	
}
