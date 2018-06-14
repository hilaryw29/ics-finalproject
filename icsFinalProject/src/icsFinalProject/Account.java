package icsFinalProject;

import java.io.*;

//Class Description: Creates an object of a bank account.
public class Account implements Serializable {
	String accountType;
	int id;
	double balance;
	
	//Takes in the account and and amount to construct an account object
	public Account (String accountType, double balance) {
		this.accountType = accountType;
		this.balance = balance;
		
	}
	//Takes in an account object and an id to construct an account object
	public Account(Account account, int id) {
		this.id = id;
		accountType = account.accountType;
		balance = account.balance;
	}
	//Acessors and mutators
	public int getId() {
		return id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountType () {
		return accountType;
	}
	
	public void setAccountType (String type) {
		accountType = type;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBalance (double balance) {
		this.balance += balance;
	}
	//toString method that modifies the outputting format of the object
	public String toString(){
		return "Account type: " + accountType + "\nID: " + id + "\nBalance: " + balance;
	}
}
