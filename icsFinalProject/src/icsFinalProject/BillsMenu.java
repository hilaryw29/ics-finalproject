package icsFinalProject;

public class BillsMenu extends Submenu {
	final int ADD_BILL = 1;
	final int DISPLAY_BILL = 2;
	
	public BillsMenu (FamilyBudgetManagement pass) {
		super(pass);
		displayMenu();
	}
	
	public void displayMenu () {
		int choice;
		System.out.println("BILLS MENU");
		System.out.println("Please enter a number 1-3 that corresponds to one of the following choices: ");
		System.out.println("1. Add preauthorized monthly expense deduction");
		System.out.println("2. Display bills");
		System.out.println("3. Back to main menu");
		choice = intakeChoice(3);
		
		if (choice == ADD_BILL) {
			addBill();
		} else if (choice == DISPLAY_BILL) {
			displayBills();
		} else {
			goBack();
		}
	}
	
	public void displayBills () {
		System.out.println("Here are the current bills: ");
		System.out.println(family.displayMonthlyBills());
		displayMenu();
	}
	
	public void addBill () {
		String name; 
		double amount;
		String date;
		int id;
		
		System.out.println("Please enter the name of the recurring monthly expense you'd like to add:");
		name = UserInput.intakeString();
		System.out.println("Please enter the amount of the recurring bill:");
		amount = UserInput.intakeDouble();
		System.out.println("Please enter the date when the bill will be deducted each month (e.g. an integer from 1-30)");
		date = UserInput.intakeDate();																							// Shouldn't this be an int
		System.out.println("Please enter the ID of the account where the money will be withdrawn from:");
		id = UserInput.intakeInt();
		
		// Will never be successful
		boolean success = false;
		while (!success) {
			success = family.addMonthlyBill(new RecurringBill (amount, name, date, id));
			if (!success) {
				System.out.println("Error");
				System.out.println("Please enter the date of deduction again: ");
				date = UserInput.intakeDate();
				System.out.println("Please enter the ID of the account where the money will be deducted from again: ");
				id = UserInput.intakeInt();
			}
		}
		displayMenu();
		
	}
	
}
