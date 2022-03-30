package com.example.application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class MarketApiWebsocketClient {

	private static final String URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";

	public static void main(String[] args) throws InterruptedException {
		var listener = new MarketWebSocketListener();
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create(URL ), listener );
		TimeUnit.MINUTES.sleep(1);
	}

}

class MarketWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the websocket server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println(data);
		webSocket.request(1);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the websocket server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occured: "+error.getMessage());
	}
	
}