public class Transaction implements Comparable<Transaction>{
	private int amount;
	private String payer, payee;
	private int payerAccountID, payeeAccountID;
	private String description, date;
	private int id;
	
	public Transaction(int amount, int payerAccountID, int payeeAccountID, String payer, String payee, String description, String date){
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
	
	public int compareTo(Transaction other) {
		return amount - other.amount;
	}

	public int getAmount() {
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