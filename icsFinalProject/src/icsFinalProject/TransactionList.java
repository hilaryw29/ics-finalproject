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

public class TransactionList{
	private int numOfTransaction;
	private int lastID;
	private final static int DEFAULTNUMOFTRANSACTION = 100;
	private final static byte[] a = "Nfwernxnfuinouie".getBytes(StandardCharsets.UTF_8);
	private String PIN;
	private ArrayList<Transaction> sortedTransaction;
	private ArrayList<Transaction> unsortedTransaction;
	
	static class Encryption{
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
	
	public TransactionList(String PIN, String fileName) throws FileModifiedException, PINNotMatchException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		this.PIN = PIN;
		byte[]  = Files.readAllBytes(Paths.get(FileConstant.TRANSACTIONS));
		byte[]  = Encryption.decrypt(a, m);
		
	}
	
	public TransactionList(String PIN){
		this.PIN = PIN;
		sortedTransaction = new ArrayList<>(DEFAULTNUMOFTRANSACTION);
		unsortedTransaction = new ArrayList<>(DEFAULTNUMOFTRANSACTION);
	}
	
	public void insertTransactionByAmount(Transaction t){
		for (int i = 0;i < numOfTransaction;i++) {
			if (t.compareTo(sortedTransaction.get(i)) >= 0) {
				sortedTransaction.add(i,t);
				return;
			}
		}
	}

	public void insertTransactionByDate(Transaction t) {
		for (int i = 0;i < numOfTransaction;i++) {
			if (t.compareTo(sortedTransaction.get(i)) >= 0) {
				sortedTransaction.add(i,t);
				return;
			}
		}
	}
	
	
	public int addTransaction(Transaction t){
		lastID++;
		Transaction tWithId = new Transaction(t,lastID);
		insertTransactionByAmount(tWithId);
		insertTransactionByDate(tWithId);
		return lastID;

	}
	public void writeFile(){
/*		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			Iterator listInterator = unsortedTransaction.iterator();
	 		while (listInterator.hasNext()) {
				Transaction current = (Transaction) listInterator.next();
				out.write(
			}
		}			out.close();
		}
*/	}
	public LinkedList<Transaction> listTransaction(String name){
		LinkedList<Transaction> person = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if((unsortedTransaction.get(i).getPayer()).equals(name)){
				person.add(unsortedTransaction.get(i));
			}
		}
		return person;
	}
	public LinkedList<Transaction> findTransaction(String date){
		LinkedList<Transaction> transactions = new LinkedList<>();
		int l = 0, r = unsortedTransaction.size() - 1;
        while (l <= r)
        {
            int m = l + (r-l)/2;
            if (unsortedTransaction.get(m).equalDate(date) == 0) {
            	r = m + 1;
            	l = m - 1;
            	while (unsortedTransaction.get(r).equalDate(date) == 0){
            		transactions.add(unsortedTransaction.get(r));
            		r++;
            	}
            	while (unsortedTransaction.get(l).equalDate(date) == 0){
            		transactions.add(unsortedTransaction.get(l));
            		l++;
            	}
            	return transactions;
            } else if (unsortedTransaction.get(m).equalDate(date) < 0)
                l = m + 1;
            else
                r = m - 1;
        }
        return null;
	}
	public Transaction findTransaction(int id){
		for (int i = 0; i < numOfTransaction ; i++) {
			if (unsortedTransaction.get(i).equals(id))
				return unsortedTransaction.get(i);
		}
		return null;
	}
	
	public LinkedList<Transaction> findTransactionLarger(double amount){
		LinkedList<Transaction> result0 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() > amount){
				result0.add(sortedTransaction.get(i));
			}
		}	
		return result0;
	}
	public LinkedList<Transaction> findTransactionSmaller(double amount){
		LinkedList<Transaction> result1 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() < amount){
				result1.add(sortedTransaction.get(i));
			}
		}
		return result1;
	}
	public LinkedList<Transaction> findTransactionEqual(double amount){
		LinkedList<Transaction> result2 = new LinkedList<>();
		for(int i = 0; i < numOfTransaction; i ++){
			if(sortedTransaction.get(i).getAmount() == amount){
				result2.add(sortedTransaction.get(i));			
			}
		}
		return result2;
	}
	public void readFile(){
		
	}
	
	public boolean changePassword(String old, String newPass){
		if (old == PIN) {
			PIN = newPass;
			writeFile();
			return true;
		} else
			return false;
	}
}