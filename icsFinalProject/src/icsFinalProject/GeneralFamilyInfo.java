package icsFinalProject;

import java.util.*;

public class GeneralFamilyInfo extends Submenu {
	final int VIEW_FAM_NAME = 1;
	final int EDIT_FAM_NAME = 2;
	final int VIEW_LOW_BALANCE = 3;
	final int EDIT_LOW_BALANCE = 4;
	final int EDIT_PIN = 5;

	public GeneralFamilyInfo(String pass) {
		super(pass);
		displayMenu();
	}

	public void displayMenu() {
		int choice;
		System.out.println("GENERAL FAMILY INFO MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. View family name");
		System.out.println("2. Edit family name");
		System.out.println("3. View low balance threshold");
		System.out.println("4. Edit low balance threshold");
		System.out.println("5. Edit pin");
		choice = intakeChoice(5);
		
		//How to view and edit family name??
		if (choice == VIEW_FAM_NAME) {

		} else if (choice == EDIT_FAM_NAME) {

		} else if (choice == VIEW_LOW_BALANCE) {
			System.out.print("The current minimum household balance is: $");
			System.out.println(family.getMinHouseHoldBalance());
		} else if (choice == EDIT_LOW_BALANCE) {
			editLowBalance();
		} else if (choice == EDIT_PIN) {
			editPin();
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
