package com.oscargreat.cloud.webhm;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.oscargreat.cloud.webhm.resource.HostMonitorInterface;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * Entry point: Started the embedded Web Server
 * 
 */

public class WebServerApp {
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(9998).build();
	}

	public static final URI BASE_URI = getBaseURI();

	protected static HttpServer startWebServer() throws IOException {
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig(
				HostMonitorInterface.class.getPackage().getName());
		
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	protected static void startStorm()throws IOException{
		//do nothing
	}
	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startWebServer();
		System.out
				.println(String
						.format("Jersey app started with WADL available at "
								+ "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
								BASE_URI, BASE_URI));
		System.in.read();
		httpServer.stop();
	}
}
