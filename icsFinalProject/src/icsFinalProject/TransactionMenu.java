package icsFinalProject;

import java.util.*;

public class TransactionMenu extends Submenu {
	final int FINDDATE = 1;
	final int ADDTRANS = 2;
	final int FINDAMOUNT = 3;
	final int VIEWBYFAM = 4;
	final int GOBACK = 5;
	
	//Constructor - creates new TransactionMenu object
	public TransactionMenu(FamilyBudgetManagement pass) {
		super(pass);
		displayMenu();
	}
	
	//Displays the options for TransactionMenu, intakes choice from user and takes/performs...
	//... the function(s) the user selects
	public void displayMenu() {
		int choice;
		System.out.println("\n\nTRANSACTION MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. Find transaction by date");
		System.out.println("2. Add a new transaction");
		System.out.println("3. Find transaction by amount");
		System.out.println("4. View transactions by family member");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		if (choice == FINDDATE) {
			findByDate();
		} else if (choice == ADDTRANS) {
			addTransaction();
		} else if (choice == FINDAMOUNT) {
			findByAmount();
		} else if (choice == VIEWBYFAM) {
			viewByFamilyMembers();
		} else {
			goBack();
		}
	}
	
	//Displays all transactions made by a specific family member
	private void viewByFamilyMembers() {
		System.out.print("Please enter the desired family member: ");
		String member = UserInput.intakeName();
		LinkedList <Transaction> found = family.listTransaction(member);	
		
		if (found.size() == 0) {
			System.out.println("No transactions were found for this user");
		} else {
			for(Transaction i:found) {
				System.out.println(i);
			}
		}
		displayMenu();
	}
	
	//Searches for transactions by the amount (of money) processed for that transaction
	private void findByAmount () {
		double amount;
		
		System.out.println("Finding transactions by amount");
		System.out.println("Enter the amount you are looking for: ");
		amount = Math.abs(UserInput.intakeDouble());
		
		LinkedList <Transaction> found = family.findTransactionEqual(amount);
		
		if (found.size() == 0) {
			System.out.println("No transactions were found for this amount");
		} else {
			for(Transaction i:found) {
				System.out.println(i);
			}
		}
		displayMenu();
	}
	
	//Adds a new transaction
	private void addTransaction () {
		UserInput.flush();
		//Prompts the user for the necessary info the add a new transaction
		System.out.println("Please enter the following information:");
		System.out.print("The name of the family member who paid the transaction: ");
		String payer = UserInput.intakeString();
		System.out.print("The name of the family member or organization who received the transaction. ");
		String payee = UserInput.intakeString();
		System.out.print("The account ID Of the payer: ");
		int payerID = UserInput.intakeInt();
		System.out.print("The account ID of the receiver, or if it is an organization, enter -1: ");
		int payeeID = UserInput.intakeInt();
		System.out.print("Enter the amount of the transaction in dollars: $");
		double amount = UserInput.intakeDouble();
		System.out.print("Enter the date of the transaction: ");
		UserInput.flush();
		String date = UserInput.intakeDate();
		System.out.print("Enter transaction description (optional):");
		String description = UserInput.intakeString();
		
		//Attempts to add a new transaction using the info given above, if an error...
		//... occurs, the user is prompted to keep re-entering the possible error-causing...
		//... info until a transaction can be created (the error is resolved)
		boolean success = false;
		while (!success) {
			try {
				family.addTransaction(new Transaction(amount, payerID, payeeID, payer, payee, description, date));
				success = true;
			} catch (AccountException e) {
				System.out.println("No account found");
				System.out.println("Enter the payer account ID again: ");
				payerID = UserInput.intakeInt();
				System.out.println("Enter the account ID of the receiver, or if it is an organization, enter -1: ");
				payeeID = UserInput.intakeInt();
			}
		}
		displayMenu();
	}
	
	//Searches transactions by date processed/made
	private void findByDate () {
		LinkedList <Transaction> found;
		System.out.println("Searching transactions by date");
		found = family.findTransaction(UserInput.intakeDate()); 
		
		if (found == null) {
			System.out.println("No transactions were found for the specified date");
		} else {
			for(Transaction i:found) {
				System.out.println(i);
			}
		}
		displayMenu();
	}
	
}
