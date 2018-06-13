package icsFinalProject;

import java.util.LinkedList;

public class BankAccountMenu extends Submenu {
	public BankAccountMenu (FamilyBudgetManagement family) { 
		super(family); 
		displayMenu();
	}
	
	public void displayMenu() {
		
		
		int choice;
		
		System.out.println("BANK ACCOUNT MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. List all bank accounts in the family");
		System.out.println("2. List all bank accouts under a certain family member's name");
		System.out.println("3. Select a bank account");
		System.out.println("4. Add a new bank account");
		System.out.println("5. Delete a bank account");
		System.out.println("6. Back to main menu");
		
		choice = UserInput.intakeChoice(6);
		
		if (choice == 1) {
			listAccounts();
		} else if (choice == 2) {
			listAccountsMember();
		} else if (choice == 3) {
			selectAccount();
		} else if (choice == 4) {
			addAccount();
		} else if (choice == 5) {
			deleteAccount();
		} else if (choice == 6) {
			goBack();
		}
	}
	
	private void deleteAccount() {
		listAccounts();
		System.out.print("Enter the ID of the account you want to delete: ");
		int id = UserInput.intakeInt();
		System.out.print("Enter the name of the owner of the account: ");
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

	private void addAccount() {
		System.out.print("Enter the name of the owner of the new account: ");
		String name = UserInput.intakeName();
		System.out.print("Enter the type of account. Enter 'c' for chequing or 's' for savings.");
		String type = UserInput.intakeType("c", "s");
		System.out.print("Enter the amount of money currently in this account: $");
		double amount = UserInput.intakeDouble();
		
		if (type.equalsIgnoreCase("c")) {
			try {
				family.addAccount(new Account("Chequing", amount), name); 
			} catch (AccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				family.addAccount(new Account("Saving", amount), name);
			} catch (AccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Your account was succesfully added.");
		displayMenu();
	}

	private void selectAccount() {
		listAccounts();
		System.out.println("Enter the number associated with the bank account you want to select");
		int choice = UserInput.intakeInt();
		Account selected;
		
		boolean success = false;
		while (!success) {			
			//try {
				//selected = family.listAccount("Family").get(choice);
				success = true;
			//} catch (IndexOutOfBoundsException ix) {
				System.out.println("Invalid choice. Try again.");
				choice = UserInput.intakeInt();
			//}
		}
		
		//System.out.println("Account " + selected.getId() + " is selected");
		//editAccountMenu(selected);
	}

	private void editAccountMenu(Account selected) {
		int choice;
		
		System.out.println("BANK ACCOUNT MENU");
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

	private void editAccountType(Account selected) {
		System.out.println("The current account " + selected.getId() + " is of type: " + selected.getAccountType());
		System.out.println("To change this account to savings, enter 's'. To change this account to chequing, enter 'c'");
		String choice = UserInput.intakeType("s", "c");
		
		if (choice.equalsIgnoreCase("s")) {
			selected.setAccountType("Saving");
		} else {
			selected.setAccountType("Chequing");
		}
		displayMenu();
	}

	private void listAccountsMember() {
		System.out.print("Enter the name of the family member: ");
		//UserInput.flush();
		String name = UserInput.intakeName();
		
		LinkedList <Account> found;
		//try {
			found = family.listAccount(name);
		//} catch (AccountException ax) {
		//}
		if (found == null) {
			System.out.println("Currently there is no account.");
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

	private void listAccounts() {
		LinkedList <Account> found;
		System.out.println("Listing all accounts");
		
		//try {
			found = family.listAccount();
		//} catch (AccountException ax) {
		//}
		
		int idx = 1;
		try {
			for(Account i:found) {
				System.out.print((idx++) + ". ");
				System.out.println(i);
			}
		} catch (NullPointerException e) {
			System.out.println("Currently there is no account.");
		}	
		displayMenu();
	}
	
}
