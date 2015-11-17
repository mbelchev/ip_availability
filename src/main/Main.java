package main;

import java.io.IOException;

public class Main {

	private static final Integer SERVER_PORT = 3111;
	
	public static void main(String[] args) throws IOException {
		final Server server = new Server(SERVER_PORT);
		server.serverStart();
	}

}
