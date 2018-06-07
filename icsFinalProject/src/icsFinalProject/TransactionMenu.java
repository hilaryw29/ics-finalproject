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
		choice = intakeChoice(5);
		
		if (choice == FINDDATE) {
			findDate();
		} else if (choice == ADDTRANS) {
			addTrans();
		} else if (choice == FINDAMOUNT) {
			
		} else {
			
		}
	}
	
	private void addTrans () {
		
	}
	
	private void findDate () {
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
	
	private String intakeDate () {
		Scanner sc = new Scanner(System.in);
		String input = null;
		boolean valid = false;
		
		System.out.println("Enter the date in the format yyyy/mm/dd");
		while (!valid) {
			try {
				input = sc.nextLine();
				if (input.length() == 10 && input.charAt(4) == '/' && input.charAt(7) == '/') {
					Integer.parseInt(input,3);
					Integer.parseInt(input.substring(5), 1);
					Integer.parseInt(input.substring(8), 1);
					valid = true;
				} else {
					System.out.println("Invalid input. Enter the date in the format yyyy/mm/dd");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Enter the date in the format yyyy/mm/dd");
			}
		}
		return input;
	}
}
