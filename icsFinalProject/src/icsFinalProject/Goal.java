package icsFinalProject;

import java.io.*;

public class Goal implements Serializable{
	private double amount;
	private String description;
	
	public Goal (double amount, String description) {
		this.amount = amount;
		this.description = description;
	}
	
	public double getAmount () {
		return amount;
	}
	
	public String getDescription () {
		return description;
	}
	
	public boolean goalAchieved (double balance) {
		if (balance >= amount) {
			return true;
		}
		return false;
	}

}
