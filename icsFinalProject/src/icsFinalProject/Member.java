package icsFinalProject;

import java.util.*;

public class Member {
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

	public boolean updateBalance(int accountId, double newBalance) {
		Account account = findAccount(accountId);
		if (account != null) {
			account.setBalance (newBalance);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean assignBudget (double newBudget) {
		
	}

}
