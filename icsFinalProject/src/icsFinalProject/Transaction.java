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
	
	public int compareTo(Transaction other) {
		return amount - other.amount;
	}
}