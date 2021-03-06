package icsFinalProject;

import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RegularMainMenu {
	Scanner sc = new Scanner(System.in);
	public static FamilyBudgetManagement family; 
	String password;

	//Constructors - creates RegularMainMenu objects *************//
	public RegularMainMenu() {		
		intakePin();
	}
	
	public RegularMainMenu(String pass) {
		password = pass;
		try {
			family = new FamilyBudgetManagement(password, FileConstant.MEMBERINFO);
		} catch (BadPaddingException c) {
		} catch (Exception e) {
		}
		displayMenu();
	}
	//*****************************************//
	
	//Intakes pin for to access info on database
	private void intakePin() {
		boolean success = false;
		System.out.println("~~~Family Budget Management~~~");
		System.out.println("By: Marvin, Carol, Lefu, Hilary");
		System.out.println("Please enter password: ");
		
		while (!success) {
			try {
				password = sc.nextLine();
					try {
						family = new FamilyBudgetManagement(password, FileConstant.ENTRANCE);
					} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
							| NoSuchPaddingException | IllegalBlockSizeException e) {
					}
				success = true;
			//Catches possible errors/exceptions which may have occurred
			//Outputs appropriate responses for possible errors
			} catch (PINNotMatchException pinex) {
				System.out.println("Incorrect password. Enter again.");
			} catch (FileModifiedException modex) {
				System.out.println("The save files were modified. Please correct this and run the program again.");
			} catch (FileNotFoundException foundex) {
				System.out.println("The save files were not found. Please correct this and run the program again.");
			} catch (IOException iox) {
				System.out.println("Incorrect password. Enter again.");
			} catch (BadPaddingException e) {
				
			}
		}
		
		System.out.println("\n\n**********Password verified**********");

		displayMenu();
	}

	//First main menu the user sees upon entering the program
	//Outputs options for the user and prompts for a choice to take the user to another menu
	public void displayMenu() {
		final int GEN_REP = 1;
		final int TRANS_MENU = 2;
		final int GEN_INFO = 3;
		final int BANK_MENU = 4;
		final int FAM_MEMBER = 5;
		final int BILLS_MENU = 6;
		final int EXIT = 7;

		System.out.println("\n\nWelcome. Please enter a number 1-6 that corresponds to the following choices: ");
		System.out.println("1. Generate previous monthly report");
		System.out.println("2. Transactions menu");
		System.out.println("3. General Family Info");
		System.out.println("4. Bank accounts menu");
		System.out.println("5. Family member menu");
		System.out.println("6. Bills menu");
		System.out.println("7. Exit the program\n");
		//Displays current family balance and alerts
		System.out.println("Your current total family balance: + $" + family.getHouseHoldBalance());
		
		if (family.isHouseHoldBalanceLow()) {
			System.out.println("ALERT: your current household balance of $" + family.getHouseHoldBalance() + " is lower than your alert threshold of $" + family.getMinHouseHoldBalance());
		} else {
			System.out.println("You have no current alerts");
		}
		
		//Intakes user choice and directs them to the menu they've chosen
		int choice = UserInput.intakeChoice(7);
		if (choice == GEN_REP) {
			family.displayLastMonthlyReport();
			displayMenu();
		} else if (choice == TRANS_MENU) {
			TransactionMenu transmenu = new TransactionMenu(family);
		} else if (choice == GEN_INFO){
			GeneralFamilyInfo faminfo = new GeneralFamilyInfo(family);
		} else if (choice == BANK_MENU) {
			BankAccountMenu bankmenu = new BankAccountMenu(family);
		} else if (choice == FAM_MEMBER) {
			FamilyMemberMenu fammenu = new FamilyMemberMenu(family);
		} else if (choice == BILLS_MENU) {
			BillsMenu billmenu = new BillsMenu(family);
		} else if (choice == EXIT) {
			family.exit();
		}
	}
}
