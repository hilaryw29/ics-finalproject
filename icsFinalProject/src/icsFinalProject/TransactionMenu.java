package icsFinalProject;

import java.util.*;

public class TransactionMenu extends Submenu {
	final int FINDDATE = 1;
	final int ADDTRANS = 2;
	final int FINDAMOUNT = 3;
	final int VIEWBYFAM = 4;
	
	public TransactionMenu(String pass) {
		super(pass);
		displayMenu();
	}
	
	public void displayMenu() {
		int choice;
		System.out.println("TRANSACTION MENU");
		System.out.println("Please enter a number 1-4 that corresponds to one of the following choices: ");
		choice = intakeChoice(VIEWBYFAM);
		
		if (choice == FINDDATE) {
			
		} else if (choice == ADDTRANS) {
			
		} else if (choice == FINDAMOUNT) {
			
		} else {
			
		}
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
