package icsFinalProject;

import java.io.Serializable;

//Class description: the class inputs or removes money from the database and give general
//concept of the characteristics of a transaction.
public class Transaction implements Serializable{
	private double amount;
	private String payer, payee;
	private int payerAccountID, payeeAccountID;
	private String description, date;
	private int id;
	
	//creates an Transaction object with the input
	public Transaction(double amount, int payerAccountID, int payeeAccountID, String payer, String payee, String description, String date){
		this.amount = amount;
		this.payerAccountID = payerAccountID;
		this.payeeAccountID = payeeAccountID;
		this.payer = payer;
		this.payee = payee;
		this.description = description;
		this.date = date;
	}
	
	//creates a transaction intaking the Transaction object and the id that is automatically assigned
	public Transaction(Transaction t1, int id){
		this.amount = t1.amount;
		this.payerAccountID = t1.payerAccountID;
		this.payeeAccountID = t1.payeeAccountID;
		this.payer = t1.payer;
		this.payee = t1.payee;
		this.description = t1.description;
		this.date = t1.date;
		this.id = id;
	}
	
	//check whether the two transactions have the same amount
	public boolean equals(Transaction other) {
		if (amount == other.amount)
			return true;
		else 
			return false;
	}
	
	//check whether the transaction have the same id with the id given
	public boolean equals(int id) {
		return this.id == id;
	}
	
	//checks whether the given name is a payer or payee or neither
	public boolean equalName(String name) {
		return this.payee.equals(name) || this.payer.equals(name);
	}
	
	//writes the transaction to file
	public String writeToFile() {
		String s = "";
		s+="<ID>"+id+"</ID>";
		s+="<Date>"+date+"</Date>";
		s+="<Payer>"+payer+"</Payer>";
		s+="<AccountID>"+payerAccountID+"</AccountID>";
		s+="<Payee>"+payee+"</Payee>";
		s+="<AccountID>"+payeeAccountID+"</AccountID>";
		s+="<Description>"+description+"</Description>";
		s+="<Amount>"+amount+"</Amount>";
		return s;
	}
	
	public String toString() {
		String s = "Payer: " + payer;
		s += "\nPayer account ID: " + payerAccountID;
		s += "\nPayee: " + payee;
		s += "\nPayee account ID: " + payeeAccountID;
		s += "\nAmount: $" + amount;
		s += "\nDate: " + date + "\n";
		return s;
	}
	
	//gives the time inteval between the given date and the transaction date
	//and compare whether the date is the same
	public int equalDate(String date) {
		return this.date.compareTo(date);
	}
	
	//gives the difference between the amount of two transactions to compare
	//which one has a greater amount
	public double compareTo(Transaction other) {
		return amount - other.amount;
	}

	//Accessors
	public double getAmount() {
		return amount;
	}

	public String getPayer() {
		return payer;
	}

	public String getPayee() {
		return payee;
	}

	public int getPayerAccountID() {
		return payerAccountID;
	}

	public int getPayeeAccountID() {
		return payeeAccountID;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public int getId() {
		return id;
	}
}
