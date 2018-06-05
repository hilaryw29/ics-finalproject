import java.util.*;
public class TransactionList{
	private int numOfTransaction;
	private String PIN;
	private LinkedList<Transaction> sortedTransaction;
	private LinkedList<Transaction> unsortedTransaction;
	
	static class Encryption{
		static 
	}
	public TransactionList(String PIN, String fileName){
		throw new FileModifiedException();
		throw new PINNotMatchException();
	}
	public Transaction(String PIN){
		this.PIN = PIN;
	}
	public void sortTransactionByAmount(){
		Iterator listInterator = unsortedTransaction.iterator();
		while (listInterator.hasNext()) {
			RecurringBill current = (RecurringBill) listInterator.next();
			if (!current.pay()) {
				unpaidList.add(current);
			}
		}
			Transaction temp;
			temp = unsortedTransaction.get(i);
			unsortedTransaction.get(i) = unsortedTransaction.get(index);
			unsortedTransaction.get(index) = temp;	
	}
	public int addTransaction(Transaction t){
		t.setID(unsortedTransaction.getLast().getID()+1);
		int i = numOfTransaction - 1;
		while((unsortedTransaction.get(i).getDate()).compareTo(t.getDate) > 0){
			i --;
		}
		unsortedTransaction.add(i + 1, t);
		return t.getID(); 
	}
	public void writeFile(){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			Iterator listInterator = unsortedTransaction.iterator();
	 		while (listInterator.hasNext()) {
				Transaction current = (Transaction) listInterator.next();
				out.write(
			}
		}			out.close();
		}
	}
	public LinkedList<Transaction> listTransaction(String name){
		LinkedList<Transaction> person = new LinkedList();
		for(int i = 0; i < numOfTransaction; i ++){
			if((unsortedTransaction.get(i).getPayer()).equals(name)){
				person.add(unsortedTransaction.get(i));
			}
		}
		return person;
	}
	public LinkedList<Transaction> findTransaction(String){
		LinkedList<Transaction> date = new LinkedList();
		for(int i = 0; i < numOfTransaction; i ++){
			if(){
				
			}
		}
	}
	public Transaction findTransaction(int){
	
	}
	public LinkedList<Transaction> findTransactionLarger(double amount){
		LinkedList<Transaction> result0 = new LinkedList();
		for(int i = 0; i < numOfTransaction; i ++){
			if(unsortedTransaction.get(i).getAmount() > amount){
				result0.add(unsortedTransaction.get(i));
			}
		}	
		return result0;
	}
	public LinkedList<Transaction> findTransactionSmaller(double amount){
		LinkedList<Transaction> result1 = new LinkedList();
		for(int i = 0; i < numOfTransaction; i ++){
			if(unsortedTransaction.get(i).getAmount() < amount){
				result1.add(unsortedTransaction.get(i));
			}
		}
		return result1;
	}
	public LinkedList<Transaction> findTransactionEqual(double){
		LinkedList<Transaction> result2 = new LinkedList();
		for(int i = 0; i < numOfTransaction; i ++){
			if(unsortedTransaction.get(i).getAmount() == amount){
				result2.add(unsortedTransaction.get(i));			
			}
		}
		return result2;
	}
	public void readFile(String, String){
		
	}
	public boolean changePassword(String, String){
		
	}
}