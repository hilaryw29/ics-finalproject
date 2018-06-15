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
			} catch (PINNotMatchException pinex) {
				System.out.println("Incorrect password. Enter again.");
			} catch (FileModifiedException modex) {
				System.out.println("The save files were modified. PLease correct this and run the program again.");
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

	public void displayMenu() {
		final int GEN_REP = 1;
		final int TRANS_MENU = 2;
		final int GEN_INFO = 3;
		final int BANK_MENU = 4;
		final int FAM_MEMBER = 5;
		final int BILLS_MENU = 6;
		

		System.out.println("\n\nWelcome. Please enter a number 1-6 that corresponds to the following choices: ");
		System.out.println("1. Generate previous monthly report");
		System.out.println("2. Transactions menu");
		System.out.println("3. General Family Info");
		System.out.println("4. Bank accounts menu");
		System.out.println("5. Family member menu");
		System.out.println("6. Bills menu\n");
		
		System.out.println("Your current total family balance: + $" + family.getHouseHoldBalance());
		
		if (family.isHouseHoldBalanceLow()) {
			System.out.println("ALERT: your current household balance of $" + family.getHouseHoldBalance() + " is lower than your alert threshold of $" + family.getMinHouseHoldBalance());
		} else {
			System.out.println("You have no current alerts");
		}
		
		int choice = UserInput.intakeChoice(6);
		if (choice == GEN_REP) {
			System.out.println("fix");
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
		}
	}
}
