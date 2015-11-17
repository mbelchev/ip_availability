package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {
	
	private Date from, to;
	private static final String FORMAT = "yyyy-­MM-dd'T'HH'_'mm'_'ss.SSSZ";
	
	public Interval(Date receivedDate) {
		this.from = receivedDate;
	}
	
	public String from() {
		return new SimpleDateFormat(FORMAT).format(from);
	}
	
	public String to() {
		if (this.to != null) return new SimpleDateFormat(FORMAT).format(this.to);
		else return null;
	}
	
	public void setOut(Date receivedDate) {
		this.to = receivedDate;
	}
	
}
