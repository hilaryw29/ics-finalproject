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
	
	private void sortByIncome () {
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
	
	private void sortByExpense () {
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
	
//	private void addMember () {
//		String name;
//		double income;
//		double 
//		
//		System.out.println ("Please enter the name of the family member you'd like to add : ");
//		
//	}
	
	private void editFamMemberMenu(Member person) {
		final int EDIT_INCOME = 1;
		final int EDIT_BUDGET = 2;
		final int SET_GOAL = 3;
		final int DELETE_GOAL = 4;
		final int GOBACK = 5;
		
		System.out.println("EDIT FAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-3 that corresponds to one of the following choices: ");
		System.out.println("1. Edit income");
		System.out.println("2. Edit monthly budget");
		System.out.println("3. Set a new goal");
		System.out.println("4. Delete goal");
		System.out.println("5. Go back to main menu");
		
		int choice = UserInput.intakeChoice(3);
		
		if (choice == EDIT_INCOME) {
			changeIncome(person);
		} else if (choice == EDIT_BUDGET) {
			changeBudget(person);
		} else if (choice == SET_GOAL) {
			setGoal(person);
		} else if (choice == DELETE_GOAL) {
			deleteGoal(person);
		} else {
			goBack();
		}
	}
	
	private void deleteGoal(Member person) {
		// How to delete goal 
		System.out.println("Goal was successfully deleted");
		
	}

	private void setGoal(Member person) {
		System.out.println("Enter the goal description: ");
		String desc = UserInput.intakeString();
		System.out.println("Enter the amout for the goal: $");
		double amount = Math.abs(UserInput.intakeDouble());
		
		boolean success = false;
		while(!success) {
			try {
				person.setGoal(new Goal(amount, desc));
				success = true;
			} catch (GoalException e) {
				if (e.getIfReachChildrenLimit()) {
					System.out.println("You have hit the maximum child goal limit. Please enter amount again");
					amount = Math.abs(UserInput.intakeDouble());
				} else if (e.isGoalExisted()) {
					System.out.println("Goal already exists. Please delete first if you want to set a new goal.");
					success = true;
				}
			}
		}
	}

	private void changeIncome (Member person) {
		System.out.print("Enter the new income of the family member: $");
		double income = Math.abs(UserInput.intakeDouble());
		
		person.setIncome(income);
	}
	
	private void changeBudget (Member person) {
		System.out.print("Enter the new montly budget of the faimly member: ");
		double budget = Math.abs(UserInput.intakeDouble());
		
		person.setBudget(budget);
	}
	
	
}
