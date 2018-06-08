package icsFinalProject;

public class FamilyMemberMenu extends Submenu {
	final int SORT_INCOME = 1;
	final int SORT_EXPENSE = 2;
	final int ADD_MEMBER = 3;
	final int SELECT_MEMBER = 4;
	
	public FamilyMemberMenu (String pass) {
		super(pass);
		displayMenu();
	}
	
	public void displayMenu () {
		int choice;
		System.out.println("FAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. Sort family member by income");
		System.out.println("2. Sort family member by expense"); 
		System.out.println("3. Add family member");
		System.out.println("4. Select family member");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		if (choice == SORT_INCOME) {

		} else if (choice == SORT_EXPENSE) {
			
		} else if (choice == ADD_MEMBER) {
			
		} else if (choice == SELECT_MEMBER) {
			
		} else {
			goBack();
		}
	}
	
	public void sortByIncome () {
		Member [] sorted;
		System.out.print("List of family members sorted by income: ");
		sorted = family.sortMemberByIncome ();
		
		for (int i = 0; i < sorted.length; i ++) {
			System.out.println ("Name: " + sorted [i].getName());
			System.out.println("Income: "+ sorted[i].getName());
			System.out.println ("");
		}
		displayMenu();
	}
	
	public void sortByExpense () {
		Member [] sorted;
		System.out.println("List of family members sorted by expense: ");
		sorted = family.sortMemberByExpense();
		
		for (int i = 0; i < sorted.length; i++) {
			System.out.println("Name:" + sorted[i].getName());
			System.out.println("Expense: " + sorted[i].getExpense());
			System.out.println("");
		}
		displayMenu();
	}
	
	private void listMembers () {
		// NEED TO GET A LIST/ARRAY OF MEMBERS SO THAT WE CAN HAVE AN ID...
		// ...IN FRONT OF EACH MEMBER AS AN OPTION
	}
	
	private void selectMember () {
		listMembers();
		
		System.out.println("Please enter the number associated with the member you'd like to select: ");
		int choice = UserInput.intakeInt();
		Member selected;
		
		boolean success = false;
		while (!success) {			
			try {
				selected = family.listMembers().get(choice);
				success = true;
			} catch (IndexOutOfBoundsException ix) {
				System.out.println("Invalid choice. Try again.");
				choice = UserInput.intakeInt();
			}
		}
		
		System.out.println("Member " + selected.getName() + " is selected");
		editFamMemberMenu(selected);
		
	}
	
	
}
