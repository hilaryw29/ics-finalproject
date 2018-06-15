package icsFinalProject;

import java.util.LinkedList;

//Class Description: the class displays menu that deals with bank accounts
public class BankAccountMenu extends Submenu {
	
	//Constructor - creates a new BankAccountMenu object
	public BankAccountMenu (FamilyBudgetManagement family) { 
		super(family); 
		displayMenu();
	}
	
	//the method displays the menu and give choices for user to choose
	public void displayMenu() {
		final int LIST_ALL = 1;
		final int LIST_BY_MEMBER = 2;
		final int SEL_ACCOUNT = 3;
		final int ADD_ACCOUNT = 4;
		final int DELETE_ACCOUNT = 5;
		final int BACK = 6;
		
		int choice;
		
		System.out.println("\n\nBANK ACCOUNT MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. List all bank accounts in the family");
		System.out.println("2. List all bank accounts under a certain family member's name");
		System.out.println("3. Select a bank account");
		System.out.println("4. Add a new bank account");
		System.out.println("5. Delete a bank account");
		System.out.println("6. Back to main menu");
		
		choice = UserInput.intakeChoice(6);
		
		//Takes the user to the function they've selected from the menu options above
		if (choice == LIST_ALL) {
			listAccounts();
		} else if (choice == LIST_BY_MEMBER) {
			listAccountsMember();
		} else if (choice == SEL_ACCOUNT) {
			selectAccount();
		} else if (choice == ADD_ACCOUNT) {
			addAccount();
		} else if (choice == DELETE_ACCOUNT) {
			deleteAccount();
		} else if (choice == BACK) {
			goBack();
		}
	}
	
	//the private method is used to select an account to delete
	private void deleteAccount() {
		listAccountsOnly();
		System.out.print("Enter the ID of the account you want to delete: ");
		int id = UserInput.intakeInt();
		System.out.print("Enter the name of the owner of the account: ");
		UserInput.flush();
		String name = UserInput.intakeName();
		
		boolean success = false;
		while (!success) {
			if (family.removeAccount(name, id)) {
				System.out.println("This account was successfully deleted.");
				success = true;
			} else {
				System.out.println("Cannot find account ID. Please enter again.");
				id = UserInput.intakeInt();
			}
		}
		displayMenu();
	}
	
	//the method takes in an account to add
	private void addAccount() {
		System.out.print("Enter the name of the owner of the new account: ");
		UserInput.flush();
		String name = UserInput.intakeName();
		System.out.print("Enter the type of account. Enter 'c' for chequing or 's' for savings.");
		String type = UserInput.intakeType("c", "s");
		System.out.print("Enter the amount of money currently in this account: $");
		double amount = UserInput.intakeDouble();
		
		//Creates a new bank account depending on the type of account the user have indicated...
		//... an interest in opening (e.g. chequing or savings)
		if (type.equalsIgnoreCase("c")) {
			try {
				family.addAccount(new Account("Chequing", amount), name); 
			} catch (AccountException e) {
			}
		} else {
			try {
				family.addAccount(new Account("Saving", amount), name);
			} catch (AccountException e) {
			}
		}
		System.out.println("Your account was succesfully added.");
		displayMenu();
	}
	
	//Allows user to select a bank account they'd like to edit
	private void selectAccount() {
		listAccountsOnly();
		
		System.out.println("Enter the number associated with the bank account you want to select");
		int choice = UserInput.intakeInt() - 1;
		Account selected = null;
		
		boolean success = false;
		while (!success) {			
			try {
				selected = (Account) family.listAccount().get(choice);
				success = true;
			} catch (IndexOutOfBoundsException ix) {
				System.out.println("Invalid choice. Try again.");
				choice = UserInput.intakeInt();
			}
		}
		
		System.out.println("Account " + (choice + 1) + " is selected");
		editAccountMenu(selected);
	}
	
	//Menu which gives the user the option to edit a specific account they've selected...
	//... in the selectAccount method
	private void editAccountMenu(Account selected) {
		int choice;
		
		System.out.println("EDIT BANK ACCOUNT MENU");
		System.out.println("Please enter a number 1-2 that corresponds to one of the following choices: ");
		System.out.println("1. Edit account type");
		System.out.println("2. Back to main menu");

		choice = UserInput.intakeChoice(2);
		
		if (choice == 1) {
			editAccountType(selected);
		} else {
			goBack();
		}
	}
	
	//Allows the user to edit the type of a specific account (e.g. chequing or savings)
	private void editAccountType(Account selected) {
		System.out.println("The current account (ID:" + selected.getId() + ") is of type: " + selected.getAccountType());
		System.out.println("To change this account to savings, enter 's'. To change this account to chequing, enter 'c'");
		UserInput.flush();
		String choice = UserInput.intakeType("s", "c");
		
		if (choice.equalsIgnoreCase("s")) {
			selected.setAccountType("Saving");
		} else {
			selected.setAccountType("Chequing");
		}
		System.out.println("Account type successfully changed");
		displayMenu();
	}

	//Lists all the existing bank accounts under a specific family member
	private void listAccountsMember() {
		System.out.print("Enter the name of the family member: ");
		UserInput.flush();
		String name = UserInput.intakeName();
		LinkedList <Account> found;
		//try {
			found = family.listAccount(name);
		//} catch (AccountException ax) {
		//}
		if (found.size() == 0) {
			System.out.println("\nCurrently there is no account.\n");
		} else {
			try {
				for(Account i:found) {
					System.out.println(i); 
				}
			} catch (NullPointerException e) {
				System.out.println("Currently there is no account.");
			}
		}
		displayMenu();
	}
	
	//Lists all the existing bank accounts in the family
	private void listAccounts() {
		listAccountsOnly();
		displayMenu();
	}
	
	private void listAccountsOnly () {
		LinkedList <Account> found;
		
		found = family.listAccount();
		
		int idx = 1;
		try {
			for(Account i:found) {
				System.out.print((idx++) + ". ");
				System.out.println(i);
			}
		} catch (NullPointerException e) {
			System.out.println("Currently there is no account.");
		}	
	}
}
