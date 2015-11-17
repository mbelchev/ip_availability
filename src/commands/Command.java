package commands;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.Server;
import main.User;

public class Command {
	
	private Socket socket;
	private Map<String, User> users = new HashMap<String, User>();

	public Command(Socket socket) {
		this.socket = socket;
	}
	
	public String execute(String[] splitted, Server server) throws IOException {
		if (splitted[0].equals("login")) return this.login(splitted);
		else if (splitted[0].equals("logout")) return this.logout(splitted);
		else if (splitted[0].equals("info")) return this.info(splitted);
		else if (splitted[0].equals("listabsent")) return this.listabsent(splitted);
		else if (splitted[0].equals("listavailable")) return this.listavailable(splitted);
		else if (splitted[0].equals("shutdown")) server.stopServer();
		else return "error: unknown command";
		
		return null;
		
	}
	
	public String[] parse(String input) {
		return input.split(":");
	}
	
	private Boolean userExist(String name) {
		return users.containsKey(name);
	}
	
	private String login(String[] commands) {
		if(!this.userExist(commands[1])) {
			users.put(commands[1], new User(commands[1], socket));
		} return users.get(commands[1]).login();
	}
	
	private String logout(String[] commands) {
		return this.userExist(commands[1]) ? users.get(commands[1]).logout() : "error:alreadyloggedout";
	}
	
	private String info(String[] commands) {
		return this.userExist(commands[1]) ? users.get(commands[1]).getInfo() : "error:noinfo";
	}
	
	private String listabsent(String[] commands) {
		if(this.userExist(commands[1])) {
			String string = "ok";
			
			for (Entry<String, User> entry : users.entrySet()) {
			    if(!entry.getValue().getIsLogged()) string += ":" + entry.getKey();
			}
			
			return string;
		} return "error:notloggedin";
	}
	
	private String listavailable(String[] commands) {
		if(this.userExist(commands[1])) {
			String string = "ok";
			for (Entry<String, User> entry : users.entrySet()) {
			    if(entry.getValue().getIsLogged()) 
			    	string += ":" + entry.getKey();
			}
			return string;
		} return "error:notloggedin";
	}
	
}
