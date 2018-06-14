package icsFinalProject;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class FamilyBudgetManagement {
	private double houseHoldBalance;
	private double minHouseHoldBalance;
	private DateManager dateManager;
	private FamilyMemberList memberlist;
	private TransactionList transactionList;
	private RecurringBillsList billList;
	private byte[] PIN;
	
	
	public FamilyBudgetManagement(String PIN, String fileName) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		BufferedReader read = new BufferedReader(new FileReader(FileConstant.ENTRANCE));
		String line;
		while ((line = read.readLine()) != null) {
			String md5 = read.readLine();
			if (!MD5.compareMD5(line, md5)) {
				throw new FileModifiedException(fileName, FileConstant.FILEMODIFED);
			}
			
		}	
		this.PIN = MD5.getMd5(PIN);
		ObjectInputStream memberlistIn = new ObjectInputStream(new FileInputStream(FileConstant.MEMBERINFO));
		memberlist = (FamilyMemberList) memberlistIn.readObject();
		memberlistIn.close();
	//	ObjectInputStream billListIn = new ObjectInputStream(new FileInputStream(FileConstant.MEMBERINFO));
		billList = new RecurringBillsList(FileConstant.BILLS);
	//	billListIn.close();
		InputStream in = new FileInputStream(FileConstant.TRANSACTIONS);
		transactionList = Encryption.decrypt(in, this.PIN);
		startTheard(this, billList);
		read.close();
		read = new BufferedReader(new FileReader(FileConstant.BUDGETMANAGEMENT));
		houseHoldBalance = Double.parseDouble(read.readLine());
		minHouseHoldBalance = Double.parseDouble(read.readLine());
		read.close();
	}
	
	// Starts a new thread to keep track of the new generated recurring bill. It takes...
	//... in the FamilyBudgetManagement object and a RecurringBillList object.
	private void startTheard(FamilyBudgetManagement manager, RecurringBillsList billList) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException {
		dateManager = new DateManager(billList,this);
		new Thread(dateManager).start();
	}
	
	//Write transactions, household information, bills etc. to a file... 
	//... and throws an IOException if there’s problem writing to the file.
	public void writeToFile() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FileConstant.MEMBERINFO));
        out.writeObject(memberlist);
        out.close();
        billList.writeFile();
        OutputStream objectOut = new FileOutputStream(FileConstant.TRANSACTIONS);
        Encryption.encrypt(transactionList, objectOut, PIN);
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileConstant.BUDGETMANAGEMENT));
        writer.write(""+houseHoldBalance);
        writer.newLine();
        writer.write(""+minHouseHoldBalance);
        writer.close();
        writer = new BufferedWriter(new FileWriter(FileConstant.ENTRANCE));
        writer.write(FileConstant.MEMBERINFO);
        writer.newLine();
        writer.write(MD5.getMD5(FileConstant.MEMBERINFO));
        writer.newLine();
        writer.write(FileConstant.BILLS);
        writer.newLine();
        writer.write(MD5.getMD5(FileConstant.BILLS));
        writer.newLine();
        writer.write(FileConstant.TRANSACTIONS);
        writer.newLine();
        writer.write(MD5.getMD5(FileConstant.TRANSACTIONS));
        writer.newLine();
        writer.write(FileConstant.BUDGETMANAGEMENT);
        writer.newLine();
        writer.write(MD5.getMD5(FileConstant.BUDGETMANAGEMENT));
        writer.close();
        
	}
	
	public FamilyBudgetManagement(String PIN) throws PINNotMatchException, FileNotFoundException, FileModifiedException, IOException {
		this.PIN = MD5.getMd5(PIN);
		transactionList = new TransactionList(PIN);
		memberlist = new FamilyMemberList();
		billList = new RecurringBillsList();
		updateHoldBalance();
		startTheard(this, billList) ;
		writeToFile();
	}
	
	//Takes in a name and an amount and assigns a budget for that person for...
	//... the given amount and return a boolean indicating success or not
	public boolean assignBudgets(String name, double amount) {
		//Writes the info to file
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return memberlist.assignBudget(name, amount);
	}
	
	//Takes in a name and an amount and returns the left amount of the budget or... 
	//... -1 if the person is not found
	public double checkBudgets(String name, double amount) {
		return memberlist.checkBudget(name);
	}
	
	// Takes in a name of the adult and a Goal object and sets a goal; 
	//... exceptions are thrown when the goal already exists or the person setting the goal is a child
	public void setGoal(String name, Goal goal) throws GoalException{
		memberlist.setGoal(goal, name);
		
		//Writes the info to file
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	//Takes in an amount as the minimum household balance and sets the...
	//... minimum household balance to the amount given. If the amount given is negative (not valid)...
	//... the min balance is set to the absolute value of the negative amount given
	public void setMinHouseHoldBalance(double minHouseHoldBalance) {
		this.minHouseHoldBalance = Math.abs(minHouseHoldBalance); 
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	
	public double getMinHouseHoldBalance() {
		return minHouseHoldBalance;
	}

	public boolean isHouseHoldBalanceLow() {
		return houseHoldBalance >= minHouseHoldBalance ? false : true;
	}
	
	public boolean addMonthlyBill(RecurringBill bill) {
		if (checkIfAccountExisted(bill) == true) {
			billList.addBill(bill);
			try {
				writeToFile();
			} catch (IOException e) {
			}
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkIfAccountExisted(RecurringBill bill) {
		return memberlist.checkAccountBalance(bill.getName(), bill.getAccountID(), Double.MAX_VALUE);
	}
	
	// FIX LATER
	public LinkedList<Account> listAccount(String s){
		return memberlist.listAccount(s);
	}
	
	public LinkedList<Account> listAccount(){
		return memberlist.listAccount();
	}
	
	public int addAccount(Account account, String name) throws AccountException {
		int i = memberlist.addAccount(account, name);
		updateHoldBalance();
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return i;
	}
	
	// FIX LATER
	public int delAccount() {
		return Integer.MAX_VALUE;
	}

	public int addTransaction(Transaction transaction) throws AccountException {
		updateBalance(transaction);
		updateHoldBalance();
		if (isHouseHoldBalanceLow()) {
			System.out.println("House Hold Balance is low now!");
		}
		int i = transactionList.addTransaction(transaction);
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return i;
		
	}
	
	private void updateBalance(Transaction transaction) throws AccountException {
		memberlist.updateBalance(transaction);
		try {
			writeToFile();
		} catch (IOException e) {
		}
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
	
	public String displayMonthlyBills() {
		return billList.displayMonthlyBills();
	}

	public Member[] sortMemberByIncome() {
		return memberlist.sortMemberByIncome();
	}
	
	public Member[] sortMemberByExpense() {
		return memberlist.sortMemberByExpense();
	}
	
	public ArrayList<Member> listMember() {
		return memberlist.listMember();
	}
	public Member searchMember(String name) {
		return memberlist.searchMember(name);
	}
	
	public boolean changePassword(String old, String newPass) {
		if (new String(MD5.getMd5(old)).equals(new String(PIN))) {
			PIN = MD5.getMd5(newPass);
			try {
				writeToFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}
	
	public void allocateIncome(String name,int id, int percentage) throws AccountException{
		memberlist.allocateIncome(name,id,percentage);
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	
	public boolean addFamilyMember(Member m) {
		memberlist.addFamilyMember(m);
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return true;
	}
	
	public boolean tryPay(String name, int id, double amount) {
		return memberlist.checkAccountBalance(name, id, amount);
	}
	
	public boolean delBill(int id) {
		boolean b = billList.delBill(id);
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return b;
	}
	
	public String displayFamilyInfo() {
		return memberlist.toString();
	}

	public double getHouseHoldBalance() {
		return houseHoldBalance;
	}

	public void setHouseHoldBalance(double houseHoldBalance) {
		this.houseHoldBalance = houseHoldBalance;
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	// fix later
	public boolean removeAccount(String name, int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
