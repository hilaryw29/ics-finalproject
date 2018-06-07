package icsFinalProject;

import java.util.*;

public class TransactionMenu extends Submenu {
	final int FINDDATE = 1;
	final int ADDTRANS = 2;
	final int FINDAMOUNT = 3;
	final int VIEWBYFAM = 4;
	final int GOBACK = 5;
	
	public TransactionMenu(String pass) {
		super(pass);
		displayMenu();
	}
	
	public void displayMenu() {
		int choice;
		System.out.println("TRANSACTION MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. Find transaction by date");
		System.out.println("2. Add a new transaction");
		System.out.println("3. FInd transaction by amount");
		System.out.println("4. View transactions by family member");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		if (choice == FINDDATE) {
			findByDate();
		} else if (choice == ADDTRANS) {
			
		} else if (choice == FINDAMOUNT) {
			findByAmount();
		} else if (choice == VIEWBYFAM){
			
		} else {
			
		}
	}
	
	private void findByAmount () {
		double amount;
		
		System.out.println("Finding transactions by amount");
		
	}
	
	private void addTransaction () {
		System.out
		try {
			
		} catch (AccountException e) {
			
		}
		displayMenu();
	}
	
	private void findByDate () {
		LinkedList <Transaction> found;
		System.out.println("Searching transactions by date");
		found = family.findTransaction(intakeDate());
		
		if (found == null) {
			System.out.println("No transactions were found for the specified date");
		} else {
			System.out.println(found);
		}
		displayMenu();
	}
	
}
