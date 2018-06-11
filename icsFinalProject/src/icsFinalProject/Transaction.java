package icsFinalProject;

import java.io.Serializable;

public class Transaction implements Serializable{
	private double amount;
	private String payer, payee;
	private int payerAccountID, payeeAccountID;
	private String description, date;
	private int id;
	
	public Transaction(double amount, int payerAccountID, int payeeAccountID, String payer, String payee, String description, String date){
		this.amount = amount;
		this.payerAccountID = payerAccountID;
		this.payeeAccountID = payeeAccountID;
		this.payer = payer;
		this.payee = payee;
		this.description = description;
		this.date = date;
	}
	
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
	
	public boolean equals(Transaction other) {
		if (amount == other.amount)
			return true;
		else 
			return false;
	}
	
	public boolean equals(int id) {
		return this.id == id;
	}
	
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
	
	public int equalDate(String date) {
		return this.date.compareTo(date);
	}
	
	public double compareTo(Transaction other) {
		return amount - other.amount;
	}

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