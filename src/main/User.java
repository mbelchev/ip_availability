package main;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private String username;
	private boolean isLogged;
	private Integer loginCounter;
	private Socket usingSocket;
	private List<Interval> loginInterval = new ArrayList<Interval>();
	
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
	public String login() {
		if(this.getIsLogged()) return "error: Already logged in";
		
		this.isLogged = true;
		this.loginCounter++;
		this.loginInterval.add(new Interval(new Date()));
		
		return "ok";
	}
	
	public String logout() {
		if(!this.getIsLogged()) return "error: Already logged out";
		
		this.isLogged = false;
		this.loginInterval.get(this.loginInterval.size() - 1).setOut(new Date());
		
		return "ok";
	}
	
	// Info
	public String getInfo() {
		StringBuilder info = new StringBuilder();
		
		info.append(
				"ok:" 
				+ this.getUsername()
				+ ":"
				+ this.getIsLogged()
				+ ":"
				+ this.getLoginCounter()
		);
		
		for (Interval temp_list: this.loginInterval) {
			info.append(":" + temp_list.from());
			if (temp_list.to() != null) info.append(":" + temp_list.to());
		}
		
		return info.toString();
	}
}
