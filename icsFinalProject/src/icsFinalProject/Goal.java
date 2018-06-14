package icsFinalProject;

import java.io.*;

//Class description: the class helps family members set a “goal” to save money for
//by creating an object and outputs a message when the goal is met.
public class Goal implements Serializable{
	private double amount;
	private String description;
	
	//creates a goal with the given amount and description
	public Goal (double amount, String description) {
		this.amount = amount;
		this.description = description;
	}
	
	//accessors and mutators
	public double getAmount () {
		return amount;
	}
	
	public String getDescription () {
		return description;
	}
	
	//return whether the goal is achieved by intaking the given balance
	public boolean goalAchieved (double balance) {
		if (balance >= amount) {
			return true;
		}
		return false;
	}

}
