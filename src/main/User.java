package main;

import java.net.Socket;

public class User {
	
	private String username;
	private boolean isLogged;
	private Integer loginCounter;
	private Socket usingSocket;
	//ToDo: add List<Interval> ...
	
	public User(String username, Socket socket) {
		this.username 		= username;
		this.isLogged 		= false;
		this.loginCounter 	= 0;
		this.usingSocket 	= socket;
	}
	
	// Getters
	public String getUsername() {
		return this.username;
	}
	
	public boolean getIsLogged() {
		return this.isLogged;
	}
	
	public Integer getLoginCounter() {
		return this.loginCounter;
	}
	
	public Socket getUsingSocket() {
		return this.usingSocket;
	}
	
	
	// Login and Logout
	public void login() {
		// ToDo
	}
	
	public void logout() {
		// ToDo
	}
}
