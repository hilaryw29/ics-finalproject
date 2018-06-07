package icsFinalProject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class FamilyBudgetManagement {
	private int familyBalance;
	private double houseHoldBalance;
	private double minHouseHoldBalance;
	private DateManager dateManager;
	private FamilyMemberList memberlist;
	private TransactionList transactionList;
	private RecurringBillsList billList;

	static class MD5{
		private static final String ALGORITHM ="MD5";
		
		private static String getMD5(String fileName) {
			try {
				byte[] read = Files.readAllBytes(Paths.get(fileName));
				byte[] hash = MessageDigest.getInstance(ALGORITHM).digest(read);
				StringBuffer value = new StringBuffer();
			    for (int i = 0; i < hash.length; i++) {
			        value.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
			    }
				return value.toString();
			}catch (NoSuchAlgorithmException e) {
			}catch (IOException e) {
			}
			return "0";
		}
		
		public static boolean compareMD5(String fileName, String md5) {
			return md5==getMD5(fileName) ? true : false;
		}
	}
	
	
	public FamilyBudgetManagement(String fileName, String PIN) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException {
		BufferedReader read = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = read.readLine()) != null) {
			String md5 = read.readLine();
			if (!MD5.compareMD5(line, md5)) {
				throw new FileModifiedException(fileName, FileConstant.FILEMODIFED);
			}
			
		}	
		transactionList = new TransactionList(FileConstant.TRANSACTIONS, PIN);
		memberlist = new FamilyMemberList(FileConstant.MEMBERINFO,FileConstant.ACCOUNTS);
		billList = new RecurringBillsList(FileConstant.BILLS);
		startTheard(this, billList);
	}
	
	private void startTheard(FamilyBudgetManagement manager, RecurringBillsList billList) {
		dateManager = new DateManager(billList,this);
		dateManager.run();
	}
	
	public int addTransaction(Transaction transaction) throws AccountException {
		updateBalance(transaction);
		updateHoldBalance();
		return transactionList.addTransaction(transaction);
	}
	
	private void updateBalance(Transaction transaction) throws AccountException {
		memberlist.updateBalance(transaction);
	}
	
	private void updateHoldBalance() {
		houseHoldBalance=memberlist.getTotalBalance();
	}
	
	public LinkedList <Transaction> listTransaction (String name){
//		if (name is a real family member) {
//			return 
//		}
				
	}
	
	
	
	
	

}
