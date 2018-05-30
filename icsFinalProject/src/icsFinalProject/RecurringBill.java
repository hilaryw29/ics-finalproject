package icsFinalProject;

public class RecurringBill {
	private double amount;
	private String name;
	private String date;
	private boolean payed;
	private int ID;
	private int accountID;
	public RecurringBill(double amount, String name, String date, int accountID) {
		this.accountID = accountID;
		this.date = date;
		this.name = name;
		this.amount = amount;
	}
	
	public boolean pay() {
		return payed;
	}

	public double getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public boolean isPayed() {
		return payed;
	}

	public int getID() {
		return ID;
	}

	public int getAccountID() {
		return accountID;
	}
	
	
}
