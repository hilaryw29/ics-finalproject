package icsFinalProject;
import java.util.*;

public class Member {
	String name;
	double income;
	double budget;
	double expense;
	double balance;
	LinkedList<Account> accountlist;
	double percentage;
	
	public Member (String name, double income, double budget, double percentage) {
		this.name = name;
		this.income = income;
		this.budget = budget;
		this.percentage = percentage; 
	}
	
	public boolean updateBalance (int accountId, double newBalance) {
		
	}
	
	
}
