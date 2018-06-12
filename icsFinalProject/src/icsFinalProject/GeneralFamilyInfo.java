package icsFinalProject;

import java.util.*;

public class GeneralFamilyInfo extends Submenu {
	final int VIEW_LOW_BALANCE = 1;
	final int EDIT_LOW_BALANCE = 2;
	final int EDIT_PIN = 3;
	final int DISPLAY_FAMILY_INFO = 4;

	public GeneralFamilyInfo(FamilyBudgetManagement pass) {
		super(pass);
		displayMenu();
	}

	public void displayMenu() {
		int choice;
		System.out.println("GENERAL FAMILY INFO MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. View low balance threshold");
		System.out.println("2. Edit low balance threshold");
		System.out.println("3. Edit pin");
		System.out.println("4. Display family info");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		if (choice == DISPLAY_FAMILY_INFO) {
			displayFamilyInfo();
		} else if (choice == VIEW_LOW_BALANCE) {
			System.out.print("The current minimum household balance is: $");
			System.out.println(family.getMinHouseHoldBalance());
		} else if (choice == EDIT_LOW_BALANCE) {
			editLowBalance();
		} else if (choice == EDIT_PIN) {
			editPin();
		} else {
			goBack();
		}

	}
	

	public void displayFamilyInfo() {	
		System.out.println("Here is the family info: ");
		System.out.print(family.displayFamilyInfo());
		displayMenu();
	}

	public void editLowBalance() {
		double newBalance;
		System.out.println("Please enter the new balance threshold: ");
		newBalance = UserInput.intakeDouble();
		family.setMinHouseHoldBalance(newBalance);
		System.out.println("New balance threshold set");
		displayMenu();
	}

	public void editPin() {
		String oldPin;
		String newPin;
		boolean changed;
		
		System.out.println("Please enter your old pin:");
		oldPin = UserInput.intakeString();
		System.out.println("Please enter a new pin: "); 
		newPin = UserInput.intakeString();
		changed = family.changePassword (oldPin, newPin);
		
		if (changed == false) {
			while (family.changePassword(oldPin, newPin) == false) {
				System.out.println("Unable to update pin, please re-enter your old pin:");
				oldPin = UserInput.intakeString();
				System.out.println("Please re-enter a new pin:");
				newPin = UserInput.intakeString();
				changed = family.changePassword (oldPin, newPin);
			}
		}
		System.out.println("Pin changed");
		displayMenu();
	}

}
