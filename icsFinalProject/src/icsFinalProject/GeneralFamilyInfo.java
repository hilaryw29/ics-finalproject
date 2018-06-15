package icsFinalProject;

import java.util.*;

public class GeneralFamilyInfo extends Submenu {
	final int VIEW_LOW_BALANCE = 1;
	final int EDIT_LOW_BALANCE = 2;
	final int EDIT_PIN = 3;
	final int DISPLAY_FAMILY_INFO = 4;
	
	//Constructor - creates new GeneralFamilyInfo object
	public GeneralFamilyInfo(FamilyBudgetManagement pass) {
		super(pass);
		displayMenu();
	}
	
	//Displays menu choices for GeneralFamilyInfo menu and prompts user to choose a function they'd...
	//... like to access
	public void displayMenu() {
		int choice;
		System.out.println("\n\nGENERAL FAMILY INFO MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. View low balance threshold");
		System.out.println("2. Edit low balance threshold");
		System.out.println("3. Edit password");
		System.out.println("4. Display family info");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		if (choice == DISPLAY_FAMILY_INFO) {
			displayFamilyInfo();
		} else if (choice == VIEW_LOW_BALANCE) {
			displayLowBalance();
		} else if (choice == EDIT_LOW_BALANCE) {
			editLowBalance();
		} else if (choice == EDIT_PIN) {
			editPin();
		} else {
			goBack();
		}

	}
	
	//Displays the current min balance set for the household
	public void displayLowBalance () {
		System.out.print("The current minimum household balance is: $");
		System.out.println(family.getMinHouseHoldBalance());
		displayMenu();
	}

	//Displays basic info on the family
	public void displayFamilyInfo() {	
		System.out.println("Here is the family info: ");
		System.out.print(family.displayFamilyInfo());		// Remember to write the toString
		displayMenu();
	}
	
	//Allows the user to edit the low balance threshold for the family
	public void editLowBalance() {
		double newBalance;
		System.out.println("Please enter the new balance threshold: ");
		newBalance = UserInput.intakeDouble();
		family.setMinHouseHoldBalance(newBalance);
		System.out.println("New balance threshold set");
		displayMenu();
	}

	//Allows the user to edit the existing pin for this family
	public void editPin() {
		String oldPin;
		String newPin;
		boolean changed;
		
		//Prompts the user to enter their old pin as well as the changed, new pin
		System.out.println("Please enter your old password:");
		UserInput.flush();
		oldPin = UserInput.intakeString();
		System.out.println("Please enter a new password: "); 
		newPin = UserInput.intakeString();
		changed = family.changePassword (oldPin, newPin);
		
		//If the old pin the user entered is incorrect, the user is prompted to re-enter their old pin...
		//... and desired new pin until the pin may be set
		if (changed == false) {
			while (family.changePassword(oldPin, newPin) == false) {
				System.out.println("Unable to update password, please re-enter your old password:");
				oldPin = UserInput.intakeString();
				System.out.println("Please re-enter a new password:");
				newPin = UserInput.intakeString();
				changed = family.changePassword (oldPin, newPin);
			}
		}
		System.out.println("Password changed");
		displayMenu();
	}

}
