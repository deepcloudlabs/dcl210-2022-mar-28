package com.example;

import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.service.rest.LotteryService;

public class App {
	public static void main(String[] args) {
		var resourceConfig = new ResourceConfig();
		resourceConfig.register(LotteryService.class);

		var handler = RuntimeDelegate.getInstance().createEndpoint(resourceConfig, HttpHandler.class);

		var server = HttpServer.createSimpleServer(null, 8080);
		server.getServerConfiguration().addHttpHandler(handler);

		try {
			server.start();
			System.out.println("Press any key to stop the server...");
			System.in.read();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
