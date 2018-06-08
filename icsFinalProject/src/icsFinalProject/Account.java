package icsFinalProject;

public class Account {
	String accountType;
	int id;
	double balance;
	
	public Account (String accountType, double balance) {
		this.accountType = accountType;
		this.balance = balance;
		
	}
	
	public Account(Account account, int id) {
		this.id = id;
		accountType = account.accountType;
		balance = account.balance;
	}
	
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
}
