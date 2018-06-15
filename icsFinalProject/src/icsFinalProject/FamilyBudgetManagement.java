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
	private Thread dateManager;
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
		this.dateManager = new Thread(new DateManager(billList,this));
		this.dateManager.start();
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
	
	//Gets the current minimum household balance
	public double getMinHouseHoldBalance() {
		return minHouseHoldBalance;
	}
	
	//Checks if the current household balance is below the min household balance
	public boolean isHouseHoldBalanceLow() {
		return houseHoldBalance >= minHouseHoldBalance ? false : true;
	}
	
	//Adds a recurring monthly bill
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
	
	//Verify if a certain account exists
	private boolean checkIfAccountExisted(RecurringBill bill) {
		try {
			return memberlist.checkAccountBalance(bill.getName(), bill.getAccountID(), Double.MIN_VALUE);
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	// FIX LATER *********COMMENT AS WELL*********
	public LinkedList<Account> listAccount(String s){
		return memberlist.listAccount(s);
	}
	
	//Returns a list of all current accounts in a linkedlist
	public LinkedList<Account> listAccount(){
		return memberlist.listAccount();
	}
	
	//Adds a new account to a specific family member
	public int addAccount(Account account, String name) throws AccountException {
		int i = memberlist.addAccount(account, name);
		updateHoldBalance();
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return i;
	}
	
	//Deletes a bank account from a specific family member
	// FIX LATER
	public int delAccount() {
		return Integer.MAX_VALUE;
	}
	
	public void exit() {
		dateManager.interrupt();
		System.exit(0);
	}
	
	//Adds a transaction to the ongoing transaction list
	public int addTransaction(Transaction transaction) throws AccountException {
		updateBalance(transaction);
		updateHoldBalance();
		//Checks if the updated household balance is below the min household balance
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
	
	//Update the household balance following a new/recent transaction
	private void updateBalance(Transaction transaction) throws AccountException {
		memberlist.updateBalance(transaction);
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	//Updates household balance
	private void updateHoldBalance() { 
		houseHoldBalance=memberlist.getTotalBalance();
	}
	
	//Returns all transactions under a certain family member in a linkedlist
	public LinkedList<Transaction> listTransaction(String name){
		return transactionList.listTransaction(name);	
	}
	
	//Returns all transactions under a certain date in a linkedlist
	public LinkedList<Transaction> findTransaction(String date){
		return transactionList.findTransaction(date);	
	} 
	
	//Finds a transaction based on its id
	public Transaction findTransaction(int id){
		return transactionList.findTransaction(id);	
	}
	
	//Finds all transactions larger than the specified amount and returns those transactions...
	//... in a linkedlist
	public LinkedList<Transaction> findTransactionLarger(double amount){
		return transactionList.findTransactionLarger(amount);	
	}
	
	//Finds all transactions equals to a specified amount and returns those transactions...
	//... in a linkedlist
	public LinkedList<Transaction> findTransactionEqual(double amount){
		return transactionList.findTransactionEqual(amount);	
	}
	
	//Finds all transactions smaller than a specified amount and returns those transactions in a linkedlist
	public LinkedList<Transaction> findTransactionSmaller(double amount){
		return transactionList.findTransactionSmaller(amount);	
	}
	
	//Find the member with the lowest balance in the entire family
	public Member findLowestBalance() {
		return memberlist.findLowestBalance();
	}
	
	//Finds the average expense per member for the entire family
	public double avgExpensePerMember() {
		return memberlist.calculateAveExpense();
	}
	
	//Displays all existing monthly bills/recurring bills in a string format
	public String displayMonthlyBills() {
		return billList.displayMonthlyBills();
	}

	public String displayTransaction() {
		return transactionList.displayTransaction();
	}
	//Sorts all existing family members by income and returns the sorted list in a Member array
	public Member[] sortMemberByIncome() {
		return memberlist.sortMemberByIncome();
	}
	
	//Sorts all existing family members by the total sum of their expenses and returns...
	//... the sorted list in a Member array
	public Member[] sortMemberByExpense() {
		return memberlist.sortMemberByExpense();
	}
	
	//Lists all existing family members in an ArrayList
	public ArrayList<Member> listMember() {
		return memberlist.listMember();
	}
	
	//Searches for a specific member given their name
	public Member searchMember(String name) {
		return memberlist.searchMember(name);
	}
	
	//Attempts to change the login password, returns a boolean indicating success or fail
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
	
	//Allocates an indicated percentage of a family member's income to their savings account
	public void allocateIncome(String name,int id, int percentage) throws AccountException{
		memberlist.allocateIncome(name,id,percentage);
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	//Adds a new family member
	public boolean addFamilyMember(Member m) {
		memberlist.addFamilyMember(m);
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return true;
	}
	
	//Tries to pay for a recurring bill expense
	public boolean tryPay(String name, int id, double amount) {
		return memberlist.checkAccountBalance(name, id, amount);
	}
	
	//Deletes a recurring bill
	public boolean delBill(int id) {
		boolean b = billList.delBill(id);
		try {
			writeToFile();
		} catch (IOException e) {
		}
		return b;
	}
	
	//Returns information about the family in a String format
	public String displayFamilyInfo() {
		return memberlist.toString();
	}

	//Returns the current household balance
	public double getHouseHoldBalance() {
		return houseHoldBalance;
	}

	//Sets/updates the current household balance
	public void setHouseHoldBalance(double houseHoldBalance) {
		this.houseHoldBalance = houseHoldBalance;
		try {
			writeToFile();
		} catch (IOException e) {
		}
	}
	
	
	public void displayLastMonthlyReport() {
		File file = new File(FileConstant.MONTHLYREPORT);
		if (file.exists()) {
			System.out.println("Here is the report: ");
			try{
				BufferedReader in = new BufferedReader(new FileReader(FileConstant.MONTHLYREPORT));
				String s;
				while ((s = in.readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException e) {
				
			}
		} else {
			System.out.println("There is no report for last month");
		}
	}
	//Removes an existing bank account given the accout holder's name and the account id
	// fix later
	public boolean removeAccount(String name, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteGoal(Member member) {
		return memberlist.deleteGoal(member);
	}
}
