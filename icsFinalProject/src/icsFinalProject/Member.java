package icsFinalProject;

import java.util.*;
import java.io.*;

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
		return null;
	}
	
	public Member compareTo(Member other) {
		return balance - other.balance > 0 ? this : other;
	}
	
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
		return -1;
	}
	
	abstract boolean setGoal (Goal goal) throws GoalException;

	public boolean updateBalance(int accountId, double newBalance) {
		Account account = findAccount(accountId);
		if (account != null) {
			account.setBalance (newBalance);
			return true;
		} else {
			return false;
		}
	}
	
	//Aren't the methods listed in the section below all getters and setters?
	//Isn't this just like a setter? Why do we need to return a boolean? (When would it be false?)
	public void setBudget (double newBudget) {
		budget = newBudget;
	}
	
	public double getBudget () {
		return budget;
	}
	
	public void updateBalance (double amount) {
		balance =balance + amount;
	}
	
	public double compareToIncome(Member other) {
		return this.income - other.income;
	}
	
	public double compareToExpense(Member other) {
		return this.expense - other.expense;
	}

	public boolean equalTo(String name) {
		return this.name.equals(name);
	}
	
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
	}
	
	public int addAccount (Account account) { 
		int id = lastAccountID+1;
		accountList.add(new Account(account, id));
		setBalance();
		return id;
	}
	
	public LinkedList<Account> listAccount (){
		return accountList;
	}

	public void setBalance() {
		double balance = 0;
		for (Account i:accountList) {
			balance += i.getBalance();
		}
		this.balance = balance;
	}
	
	public abstract void writeFile ();

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
}
