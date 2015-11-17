package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {
	private final Integer port;
	private ServerSocket serverSocket;
	private boolean running;
	private final List<Client> clients = Collections.synchronizedList(new LinkedList<Client>());
	
	public Server(Integer port) {
		this.port = port;
	}
	
	public void startServer() throws IOException {
		setRunning();
		
		this.serverSocket = new ServerSocket(port);
		
		while(isRunning())
			new Thread(new Client(serverSocket.accept(), this)).start();
		
		serverSocket.close();
	}
	
	public synchronized void stopServer() throws IOException {
		if (!running) throw new IllegalStateException("Not running");

		running = false;
		
		serverSocket.close();
		serverSocket = null;
		
		for (Client next : clients)
			next.stopClient();
	}
	
	public synchronized boolean isRunning() {
		return running;
	}
	
	private synchronized void setRunning() {
		if(running) throw new IllegalStateException("Already running");
		running = true;
	}
	
	public synchronized void onClientStopped(Client clientHandler) {
		clients.remove(clientHandler);
	}
}
