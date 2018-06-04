package icsFinalProject;

import java.util.*;
import java.io.*;

public abstract class Member {
	protected String name;
	protected double income;
	protected double budget;
	protected double expense;
	protected double balance;
	protected LinkedList<Account> accountList;
	protected double percentage;

	public Member(String name, double income, double budget, double percentage) {
		this.name = name;
		this.income = income;
		this.budget = budget;
		this.percentage = percentage;
	}

	private Account findAccount(int accountId) {
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
	public boolean assignBudget (double newBudget) {
		budget = newBudget;
		return true;
	}
	
	public double checkBudget () {
		return budget;
	}
	
	public void updateBalance (double newBalance) {
		balance = newBalance;
	}
	
	public boolean checkAccountBalance (String name, int accountId, double lowestBalance) {
		Account account = findAccount (accountId);
		if (account != null) {
			if (account.getBalance() >= lowestBalance) {
				return true;
			} else {
				return false;
			}
		}
		// Should throw exception?
		return false;
	}
	
	//NOT DONE (are we supposed to generate the account id in this method?)
//	public int addAccount (Account account) {
//		accountList.add(account);
//		return -1;
//	}
	
	//Why do you need the name of the member as part of the parameters?
	public boolean deleteAccount (String name, int accountId) {
		int index = findAccountIndex (accountId);
		
		if (index != -1) {
			accountList.remove(index);
			return true;
		}
		return false;
	}
	
	//Check, like this?
	public LinkedList<Account> listAccount (){
		return accountList;
	}
	
	public abstract void writeFile ();
}
