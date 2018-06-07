
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class TransactionList{
	private int numOfTransaction;
	private String PIN;
	private ArrayList<Transaction> sortedTransaction;
	private ArrayList<Transaction> unsortedTransaction;
	
	static class Encryption{
		 private static final String ALGORITHM = "AES";
		 
		 static String encrypt(String PIN, String text) {
			 byte[] pinbyte = PIN.getBytes(StandardCharsets.UTF_8);
			 byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
			 SecretKeySpec key = new SecretKeySpec(pinbyte, ALGORITHM);
		     Cipher cipher = Cipher.getInstance(ALGORITHM);
		     cipher.init(Cipher.ENCRYPT_MODE, key);
		     byte[] cipherText = cipher.doFinal(byteText);
		     return new String(cipherText);
		 }
		 
		 static String decrypt(String PIN, String text) {
			 byte[] key = PIN.getBytes(StandardCharsets.UTF_8);
			 byte[] cipherText = text.getBytes(StandardCharsets.UTF_8);
			 SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		     Cipher cipher = Cipher.getInstance(ALGORITHM);
		     cipher.init(Cipher.DECRYPT_MODE, secretKey);
		     return new String(cipher.doFinal(cipherText));
		 }
	}
	
	public TransactionList(String PIN, String fileName) throws FileModifiedException, PINNotMatchException, IOException{
		throw new FileModifiedException();
		throw new PINNotMatchException();
	}
	public TransactionList(String PIN){
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
		return t.getId(); 
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
	public LinkedList<Transaction> findTransaction(String date){
		//LinkedList<Transaction> date = new LinkedList();
		int l = 0, r = sortedTransaction.size() - 1;
        while (l <= r)
        {
            int m = l + (r-l)/2;
            if (sortedTransaction.get(m).equalDate(date) == 0)
                return m;
            if (sortedTransaction.get(m).equalDate(date) < 0)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
	}
	public Transaction findTransaction(int id){
	
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
	public LinkedList<Transaction> findTransactionEqual(double amount){
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