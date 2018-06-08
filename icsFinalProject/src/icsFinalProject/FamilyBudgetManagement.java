package icsFinalProject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class FamilyBudgetManagement {
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
	
	public FamilyBudgetManagement(String fileName, String PIN) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException, ClassNotFoundException {
		BufferedReader read = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = read.readLine()) != null) {
			String md5 = read.readLine();
			if (!MD5.compareMD5(line, md5)) {
				throw new FileModifiedException(fileName, FileConstant.FILEMODIFED);
			}
			
		}	
		transactionList = new TransactionList(FileConstant.TRANSACTIONS, PIN);
		ObjectInputStream memberlistIn = new ObjectInputStream(new FileInputStream(FileConstant.MEMBERINFO));
		memberlist = (FamilyMemberList) memberlistIn.readObject();
		memberlistIn.close();
		ObjectInputStream billListIn = new ObjectInputStream(new FileInputStream(FileConstant.MEMBERINFO));
		billList = (RecurringBillsList) billListIn.readObject();
		billListIn.close();
		startTheard(this, billList);
		read.close();
	}
	
	private void startTheard(FamilyBudgetManagement manager, RecurringBillsList billList) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException {
		dateManager = new DateManager(billList,this);
		dateManager.run();
	}
	
	public void writeToFile() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FileConstant.MEMBERINFO));
        out.writeObject(memberlist);
        out.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FileConstant.BILLS));
        out.writeObject(billList);
        out.close();
	}
	
	public FamilyBudgetManagement(String PIN) {
		transactionList = new TransactionList(PIN);
		memberlist = new FamilyMemberList();
		billList = new RecurringBillsList();
	}
	
	public boolean assignBudgets(String name, double amount) {
		return memberlist.assignBudget(name, amount);
	}
	
	public double checkDudgets(String name, double amount) {
		return memberlist.checkBudget(name);
	}
	
	public void setGoal(String name, Goal goal) throws GoalException{
		memberlist.setGoal(goal, name);
	}
	
	public void setMinHouseHoldBalance(double minHouseHoldBalance) {
		this.minHouseHoldBalance = Math.abs(minHouseHoldBalance); 
	}
	
	public boolean isHouseHoldBalanceLow() {
		return houseHoldBalance > minHouseHoldBalance ? true : false;
	}
	
	public boolean addMonthlyBill(RecurringBill bill) {
		if (checkIfAccountExisted(bill) == true) {
			billList.addBill(bill);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkIfAccountExisted(RecurringBill bill) {
		return memberlist.checkAccountBalance(bill.getName(), bill.getAccountID(), Double.MAX_VALUE);
	}
	
	public int addTransaction(Transaction transaction) throws AccountException {
		updateBalance(transaction);
		updateHoldBalance();
		if (isHouseHoldBalanceLow()) {
			System.out.println("House Hold Balance is low now!");
		}
		return transactionList.addTransaction(transaction);
	}
	
	private void updateBalance(Transaction transaction) throws AccountException {
		memberlist.updateBalance(transaction);
	}
	
	private void updateHoldBalance() {
		houseHoldBalance=memberlist.getTotalBalance();
	}
	
	public LinkedList<Transaction> listTransaction(String name){
		return transactionList.listTransaction(name);	
	}
	
	public LinkedList<Transaction> findTransaction(String date){
		return transactionList.findTransaction(date);	
	} 
	
	public Transaction findTransaction(int id){
		return transactionList.findTransaction(id);	
	}
	
	public LinkedList<Transaction> findTransactionLarger(double amount){
		return transactionList.findTransactionLarger(amount);	
	}
	
	public LinkedList<Transaction> findTransactionEqual(double amount){
		return transactionList.findTransactionEqual(amount);	
	}
	
	public LinkedList<Transaction> findTransactionSmaller(double amount){
		return transactionList.findTransactionSmaller(amount);	
	}
	
	public Member findLowestBalance() {
		return memberlist.findLowestBalance();
	}
	
	public double avgExpensePerMember() {
		return memberlist.calculateAveExpense();
	}

	public Member[] sortMemberByIncome() {
		return memberlist.sortMemberByIncome();
	}
	
	public Member[] sortMemberByExpense() {
		return memberlist.sortMemberByExpense();
	}
	
	public Member searchMember(String name) {
		return memberlist.searchMember(name);
	}
	
	public boolean changePassword(String old, String newPass) {
		return transactionList.changePassword(old, newPass);
	}
	
	public void allocateIncome(String name,int id, int percentage) throws AccountException{
		memberlist.allocateIncome(name,id,percentage);
	}
	
	public boolean addFamilyMember(Member m) {
		memberlist.addFamilyMember(m);
		return true;
	}
	
	public boolean tryPay(String name, int id, double amount) {
		return memberlist.checkAccountBalance(name, id, amount);
	}
	
	public boolean delBill(int id) {
		return billList.delBill(id);
	}
	
	public String displayFamilyInfo() {
		return memberlist.toString();
	}
}
