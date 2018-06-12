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
	public static FamilyBudgetManagement family; // IS THIS A BAD IDEAAAA
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
		String input, answer;
		boolean success = false;
		
		System.out.println("Please enter password: ");
		
		while (!success) {
			try {
				password = sc.nextLine();
					try {
						family = new FamilyBudgetManagement(password, FileConstant.ENTRANCE);
					} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
							| NoSuchPaddingException | IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				success = true;
			} catch (PINNotMatchException pinex) {
				System.out.println("Incorrect password. Enter again.");
			} catch (FileModifiedException modex) {
				System.out.println("The save files were modified. PLease correct this and run the program again.");
			} catch (FileNotFoundException foundex) {
				System.out.println("The save files were not found. Please correct this and run the program again.");
			} catch (IOException iox) {
				System.out.println("IO");
			} catch (BadPaddingException e) {
				
			}
		}
		
		System.out.println("Password verified");

		displayMenu();
		/*try {
			BufferedReader in = new BufferedReader(new FileReader(FileConstant.PIN));
			
			answer = in.readLine();
			input = sc.nextLine();
			
			while (!input.equals(answer)) {
				System.out.println("Incorrect. Please try again.");
				input = sc.nextLine();
			}
			
		} catch (IOException iox) {
		}*/
	}

	public void displayMenu() {
		int choice;
		System.out.println("Welcome. Please enter a number 1-3 that corresponds to the following choices: ");
		System.out.println("1. Generate previous montly report");
		System.out.println("2. Transanctions menu");
		System.out.println("3. General Family Info");
		System.out.println("4. Bank accounts menu");
		System.out.println("5. Family member menu");
		System.out.println("6. Bills menu\n");
		
		System.out.println("Your current total family balance: + $" + family.getHouseHoldBalance());
		
		if (loadalert()) {
			System.out.println("ALERT: your current household balance of $" + family.getHouseHoldBalance() + " is lower than your alert threshold of " + family.getMinHouseHoldBalance());
		} else {
			System.out.println("You have no current alerts");
		}
		
		choice = UserInput.intakeChoice(6);
		
		if (choice == 1) {
			System.out.println("fix");
		} else if (choice == 2) {
			TransactionMenu transmenu = new TransactionMenu(password);
		} else if (choice == 3){
			GeneralFamilyInfo faminfo = new GeneralFamilyInfo(password);
		} else if (choice == 4) {
			BankAccountMenu bankmenu = new BankAccountMenu(password);
		} else if (choice == 5) {
			FamilyMemberMenu fammenu = new FamilyMemberMenu(password);
		} else if (choice == 6) {
			BillsMenu billmenu = new BillsMenu(password);
		}
	}

	
	// DEPRECATE THIS
	private int intakeChoice() {
		int input;
		boolean success = false;
		
		
		input = sc.nextInt();
		while (!success) {
			try {
				input = sc.nextInt();
				if (!(input >= 1 && input <= 3)) {
					success = true;
				} else {
					System.out.println("Invlaid choice. Please enter again.");
				}
			} catch (InputMismatchException inpx) {
				sc.nextLine();
				System.out.println("Invlaid choice. Please enter again.");
			}
		}
		return input;
	}
	
	private boolean loadalert() {
		if (family.getHouseHoldBalance() <  family.getMinHouseHoldBalance()) {
			return true;
		}
		return false;
	}
}
