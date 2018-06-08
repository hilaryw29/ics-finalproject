package icsFinalProject;
import java.io.Serializable;
import java.util.Calendar;  
import java.util.Date;  

public class RecurringBill implements Comparable<RecurringBill>, Serializable{
	private double amount;
	private String name;
	private String date;
	private boolean isFailed;
	private int ID;
	private int accountID;
	public RecurringBill(double amount, String name, String date, int accountID) {
		this.accountID = accountID;
		this.date = date;
		this.name = name;
		this.amount = amount;
		isFailed = false;
	}
	
	public RecurringBill(RecurringBill bill, int ID) {
		this.accountID = bill.accountID;
		this.date = bill.date;
		this.name = bill.name;
		this.amount = bill.amount;
		isFailed = false;
		this.ID = ID;
	}
	
	public Transaction generateTransaction() {
		Calendar c = Calendar.getInstance();  
	    c.setTime(new Date());  
	    int month = c.get(Calendar.MONTH)+1; 
		return new Transaction(amount,accountID,-1,name,"N/A","RecurringBill",month+"/"+date);
	}
	
	public int compareTo(RecurringBill other) {
		return ID - other.ID;
	}
	
	public boolean isFailed() {
		return isFailed;
	}

	public void setFailed(boolean isFailed) {
		this.isFailed = isFailed;
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

	public int getID() {
		return ID;
	}

	public int getAccountID() {
		return accountID;
	}

	public String toString() {
		return "123";
	}
}
