package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {
	
	private Date from, to;
	
	public Interval(Date receivedDate) {
		this.from = receivedDate;
	}
	
	public String from() {
		return this.formatDate(from);
	}
	
	public String to() {
		return this.formatDate(to);
	}

	private String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-­MM-dd'T'HH'_'mm'_'ss.SSSZ").format(date);
	}
}
