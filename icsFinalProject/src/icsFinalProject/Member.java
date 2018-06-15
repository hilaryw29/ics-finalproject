package icsFinalProject;

import java.util.*;
import java.io.*;

//Class Description: the class gives a general concept of the family members’ characteristic
public abstract class Member implements Serializable {
	protected String name;
	protected double income;
	protected double budget;
	protected double expense;
	protected double balance;
	protected LinkedList<Account> accountList;
	protected double percentage;
	protected Goal goal;	
	protected int lastAccountID;

	// constructor that takes in a name, income, budget and a percentage to construct a member
	public Member(String name, double income, double budget, double percentage) {
		this.name = name;
		this.income = income;
		this.budget = budget;
		this.percentage = percentage;
		accountList = new LinkedList<>();
		goal = null;
		expense = 0;
		balance = 0;
		lastAccountID = 0;
	}
	
	//the method finds the account with the given id
	public Account findAccount(int accountId) {
		int bottom = 0;
		int top = accountList.size()-1;
		boolean found = false;
		int middle;

		while (bottom <= top && found == false) {
			middle = (bottom + top) / 2;
			if ((accountList.get(middle)).getId() == accountId) {
				return accountList.get(middle);
			} else if (accountId > (accountList.get(middle)).getId()) {
				bottom = middle + 1;
			} else {
				top = middle - 1; 
			}

		}
		return null; 		//binary search is used
	}
	
	//the method compares the balances between the two members
	public Member compareTo(Member other) {
		return balance - other.balance > 0 ? this : other;
	}
	
	
	public boolean deleteGoal() {
		if (goal == null) {
			return false;
		} else {
			goal = null;
			return true;
		}
	}
	//the method finds the index of the account given the account id
	private int findAccountIndex (int accountId) {
		int bottom = 0;
		int top = accountList.size()-1;
		boolean found = false;
		int middle;

		while (bottom <= top && found == false) {
			middle = (bottom + top) / 2;
			if ((accountList.get(middle)).getId() == accountId) {
				return middle;
			} else if (accountId > (accountList.get(middle)).getId()) {
				bottom = middle + 1;
			} else {
				top = middle - 1; 
			}

		}
		return -1;			//binary search
	}
	
	//abstract method that takes in a Goal object and return a boolean for 
	//whether success or not or throw a GoalException
	abstract boolean setGoal (Goal goal) throws GoalException;

	//Takes in an amount and an account id to update the balance and 
	//return a boolean for whether success or not
	public boolean updateBalance(int accountId, double newBalance) {
		Account account = findAccount(accountId);
		if (account != null) {
			account.setBalance (newBalance);
			if (newBalance <= 0) {
				expense += Math.abs(newBalance);
			}
			return true;
		} else {
			return false;
		}
	}

	//accessor and mutator for budget
	public void setBudget (double newBudget) {
		budget = newBudget;
	}
	
	public double getBudget () {
		return budget;
	}
	
	//the method updates the total balance of the member
	public void updateBalance (double amount) {
		balance =balance + amount;
	}
	
	//the method compares the income between two members
	public double compareToIncome(Member other) {
		return this.income - other.income;
	}
	
	//the method compares the expenses between the two members
	public double compareToExpense(Member other) {
		return this.expense - other.expense;
	}

	//the method checks whether the name of the member is the same with the given name
	public boolean equalTo(String name) {
		return this.name.equals(name);
	}
	
	//the method takes in the name of the account holder, the account id and an amount to
	//check whether the account have a higher amount than the given amount and return a boolean
	public boolean checkAccountBalance (String name, int accountId, double lowestBalance) throws AccountException {
		Account account = findAccount (accountId);
		if (account != null) {
			if (account.getBalance() >= lowestBalance) {
				return true;
			} else {
				return false;
			}
		}
		throw new AccountException(false, name); 
		// throw exception when the account does not exist or name does not match
	}
	
	//the method takes in an account object and returns an account id
	public int addAccount (Account account) { 
		int id = lastAccountID;
		lastAccountID++;
		accountList.add(new Account(account, id));
		setBalance();
		return id;
	}
	
	//the method list all the accounts
	public LinkedList<Account> listAccount (){
		return accountList;
	}

	//set the balance of the member
	public void setBalance() {
		double balance = 0;
		for (Account i:accountList) {
			balance += i.getBalance();
		}
		this.balance = balance;
	}
	
	//The abstract method writes all the information of the member to a file
	public abstract void writeFile ();

	//*****FOR ALL METHODS BELOW*****//
	//accessors and mutators
	public String getName() {
		return name;
	}

	public double getIncome() {
		return income;
	}

	public double getExpense() {
		return expense;
	}

	public double getBalance() {
		return balance;
	}

	public LinkedList<Account> getAccountList() {
		return accountList;
	}

	public double getPercentage() {
		return percentage;
	}

	public Goal getGoal() {
		return goal;
	}
	
	public void setIncome (double money) {
		income = money;
	}
	//*************************//
	
	//Returns all the important information in member in a single String
	public String toString(){
		return"Name: " + name +"\nIncome: " + income + "\nBudget: " + budget + "\nExpense: "+ expense + "\nBalance: " + balance + "\nAccounts: " + accountList + "\nGoal: " + goal + "\nPercentage of income allocation: " + percentage + "\n";
	}
}
