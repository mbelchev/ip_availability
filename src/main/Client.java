package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import commands.Command;

public class Client implements Runnable {
	private final Socket socket;
	private final Server Server;
	private final Command cmd;
	
	public Client(Socket socket, Server Server) {
		this.socket = socket;
		this.Server = Server;
		this.cmd = new Command(socket);
	}
	
	@Override
	public void run() {
		try {
			final PrintStream out = new PrintStream(socket.getOutputStream());
			final Scanner scanner = new Scanner(socket.getInputStream());
			
			while(scanner.hasNextLine())
				out.println(cmd.execute(cmd.parse(scanner.nextLine()), Server));				
			
			scanner.close();
			out.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			Server.onClientStopped(this);
		}
	}
	
	public void stopClient() throws IOException {
		socket.close();
	}
	
}
