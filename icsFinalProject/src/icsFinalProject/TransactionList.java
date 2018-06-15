package icsFinalProject;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//Class description: the class stores all the transactions within the database and 
//provides methods to search for, sort, and manipulate past transactions.
public class TransactionList implements Serializable {
	private int numOfTransaction;
//	private int lastID;
	private final static int DEFAULTNUMOFTRANSACTION = 100;
	private byte[] PIN;
	private ArrayList<Transaction> sortedTransaction;
	private ArrayList<Transaction> unsortedTransaction;
	
/*	static class Encryption{
		 private static final String ALGORITHM = "AES";
		 
		 static byte[] encrypt(byte[] PIN, byte[] text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			 SecretKeySpec key = new SecretKeySpec(PIN, ALGORITHM);
		     Cipher cipher = Cipher.getInstance(ALGORITHM);
		     cipher.init(Cipher.ENCRYPT_MODE, key);
		     return cipher.doFinal(text);
		     
		 }
		 
		 static byte[] decrypt(byte[] PIN, byte[] text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			 SecretKeySpec secretKey = new SecretKeySpec(PIN, ALGORITHM);
		     Cipher cipher = Cipher.getInstance(ALGORITHM);
		     cipher.init(Cipher.DECRYPT_MODE, secretKey);
		     return cipher.doFinal(text);
		 }
	}
*/	
	public TransactionList(String PIN, String fileName) throws FileModifiedException, PINNotMatchException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
/*		this.PIN = MD5.getMd5(PIN);
		byte[] text = Files.readAllBytes(Paths.get(FileConstant.TRANSACTIONS));
		byte[] plainText = Encryption.decrypt(this.PIN, text);
		double amount;
		String payer, payee;
		int payerAccountID, payeeAccountID;
		String description, date;
		int id;
		
		
		for (byte i: plainText) {
			line += 
		}
*/
	}
	
	//the constructor creates a transaction list intaking a PIN
	public TransactionList(String PIN){
		this.PIN = MD5.getMd5(PIN);
		sortedTransaction = new ArrayList<>(DEFAULTNUMOFTRANSACTION);
		unsortedTransaction = new ArrayList<>(DEFAULTNUMOFTRANSACTION);
//		lastID = 0;
		numOfTransaction = 0;
	}
	
	//the method inserts the given transaction to the correct place in the order of amount
	public void insertTransactionByAmount(Transaction t){
		for (int i = 0;i < numOfTransaction && numOfTransaction != 1 ;i++) {
			if (t.compareTo(sortedTransaction.get(i)) <= 0) {
				sortedTransaction.add(i,t);
				return;
			}
		}
		sortedTransaction.add(t);
	}

	//the method inserts the given transaction to the correct place in the order of time
	public void insertTransactionByDate(Transaction t) {
		for (int i = 0;i < numOfTransaction && numOfTransaction != 1;i++) {
			if (t.equalDate(unsortedTransaction.get(i).getDate()) <= 0) {
				unsortedTransaction.add(i,t);
				return;
			}
		}
		unsortedTransaction.add(t);
	}
	
	//intakes a transaction and adds to the transaction list
	public int addTransaction(Transaction t){
		Transaction tWithId = new Transaction(t,numOfTransaction);
//		lastID++;
		insertTransactionByAmount(tWithId);
		insertTransactionByDate(tWithId);
		numOfTransaction++;
		return numOfTransaction;

	}
	public void writeToFile(){
/*		try {
			FileOutputStream out = new 	FileOutputStream(FileConstant.TRANSACTIONS);
			String num = ""+numOfTransaction;
			byte[] m = Encryption.encrypt(PIN, num.getBytes(StandardCharsets.UTF_8));
			out.write(m);
			String lastID = ""+this.numOfTransaction;
			byte[] id = Encryption.encrypt(PIN, lastID.getBytes(StandardCharsets.UTF_8));
			out.write(id);
			for (Transaction i:unsortedTransaction) {
				byte[] text = Encryption.encrypt(PIN, i.toString().getBytes(StandardCharsets.UTF_8));
				out.write(text);
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException | IOException e){
			
		}
*/	}
	
	//Return all transactions that involves the entered family member
	public LinkedList<Transaction> listTransaction(String name){
		LinkedList<Transaction> person = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if((unsortedTransaction.get(i).equalName(name))){
				person.add(unsortedTransaction.get(i));
			}
		}
		return person;
	}
	
	public String displayTransaction() {
		String s="";
		for (Transaction i: unsortedTransaction) {
			s+=i.toString();
		}
		return s;
	}
	
	//Return all transaction that occurred on the date given
	public LinkedList<Transaction> findTransaction(String date){
		LinkedList<Transaction> transactions = new LinkedList<>();
		int l = 0, r = unsortedTransaction.size() - 1;
		while (l <= r)
		{
		    int m = l + (r-l)/2;
		    if (unsortedTransaction.get(m).equalDate(date) == 0) {
		    	transactions.add(unsortedTransaction.get(m));
		    	r = m + 1;
				l = m - 1;
				try {
					while (unsortedTransaction.get(r).equalDate(date) == 0){
						transactions.add(unsortedTransaction.get(r));
						r++;
						}
				} catch (IndexOutOfBoundsException e) {
					
				}
				try {
					while (unsortedTransaction.get(l).equalDate(date) == 0){
						transactions.add(unsortedTransaction.get(l));
						l++;
					}
				}catch (IndexOutOfBoundsException e) {
					
				}
				return transactions;
		    } else if (unsortedTransaction.get(m).equalDate(date) < 0)
		    	l = m + 1;
		    else
		    	r = m - 1;
			}
		return null;
	}
	
	//Return the transaction that matches the ID
	public Transaction findTransaction(int id){
		for (int i = 0; i < numOfTransaction ; i++) {
			if (unsortedTransaction.get(i).equals(id))
				return unsortedTransaction.get(i);
		}
		return null;
	}
	
	//Return a list of transactions whose amounts are larger than given
	public LinkedList<Transaction> findTransactionLarger(double amount){
		LinkedList<Transaction> result0 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() > amount){
				result0.add(sortedTransaction.get(i));
			}
		}	
		return result0;
	}
	
	//Return a list of transactions whose amounts are smaller to given
	public LinkedList<Transaction> findTransactionSmaller(double amount){
		LinkedList<Transaction> result1 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() < amount){
				result1.add(sortedTransaction.get(i));
			}
		}
		return result1;
	}
	
	//Return the list of transaction whose amounts are equal to given
	public LinkedList<Transaction> findTransactionEqual(double amount){
		LinkedList<Transaction> result2 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() == amount){
				result2.add(sortedTransaction.get(i));			
			}
		}
		return result2;
	}
	
	//the method do the process of changing password
	public boolean changePassword(String old, String newPass){
		if (MD5.getMd5(old) == PIN) {
			PIN = MD5.getMd5(newPass);
			writeToFile();
			return true;
		} else
			return false;
	}
}
