package icsFinalProject;
import java.io.Serializable;
import java.util.Calendar;  
import java.util.Date;  

//Class description: the class keeps track of all the information for the monthly bill.
public class RecurringBill implements Comparable<RecurringBill>, Serializable{
	private double amount;
	private String name;
	private String date;
	private boolean isFailed;
	private int ID;
	private int accountID;
	
	//Creates a RecurringBill object given the amount of the recurring bill, name, 
	//date, and ID of the account where the money for the bill will be deducted from
	public RecurringBill(double amount, String name, String date, int accountID) {
		this.accountID = accountID;
		this.date = date;
		this.name = name;
		this.amount = amount;
		isFailed = false;
	}
	
	//Creates a recurring bill intaking a RecurringBill object and an id that is automatically assigned
	public RecurringBill(RecurringBill bill, int ID) {
		this.accountID = bill.accountID;
		this.date = bill.date;
		this.name = bill.name;
		this.amount = bill.amount;
		isFailed = false;
		this.ID = ID;
	}
	
	//the method generates a transaction for the payment of bills
	public Transaction generateTransaction() {
		Calendar c = Calendar.getInstance();  
	    c.setTime(new Date());  
	    int month = c.get(Calendar.MONTH)+1; 
	    int year = c.get(Calendar.YEAR);
	    if (month < 10) {
	    	return new Transaction(amount,accountID,-1,name,"N/A","RecurringBill",year+"/0"+month+"/"+date);
	    } else {
	    	return new Transaction(amount,accountID,-1,name,"N/A","RecurringBill",year+"/"+month+"/"+date);
	    }
		
	}
	
	//compares the bill IDs between two bills
	public int compareTo(RecurringBill other) {
		return ID - other.ID;
	}
	
	//return whether the paying is failed
	public boolean isFailed() {
		return isFailed;
	}

	//set the whether the payment is failed or not to the given result
	public void setFailed(boolean isFailed) {
		this.isFailed = isFailed;
	}

	//Accessors
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
		return "ID: " + ID + "\nAccount ID: " + accountID + "\nAmount: " + amount + "\nName: " + name + "\nDate: " + date + "\nSuccessful Transaction: " + isFailed;
	}
}
