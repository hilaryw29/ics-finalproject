package icsFinalProject;
import java.util.*;

public class FamilyMemberMenu extends Submenu {
	final int SORT_INCOME = 1;
	final int SORT_EXPENSE = 2;
	final int ADD_MEMBER = 3;
	final int SELECT_MEMBER = 4;
	
	//Constructor - creates a new FamilyMemberMenu object
	public FamilyMemberMenu (FamilyBudgetManagement pass) {
		super(pass);
		displayMenu();
	}
	
	//Displays all the choices available in FamilyMemberMenu and allows the user to choose which...
	//... function they'd like to access
	public void displayMenu () {
		int choice;
		System.out.println("\n\nFAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. Sort family member by lowest to highest income");
		System.out.println("2. Sort family member by lowest to highest expense"); 
		System.out.println("3. Add family member");
		System.out.println("4. Select family member");
		System.out.println("5. Back to main menu");
		choice = intakeChoice(5);
		
		//
		if (choice == SORT_INCOME) {
			sortByIncome();
		} else if (choice == SORT_EXPENSE) {
			sortByExpense();
		} else if (choice == ADD_MEMBER) {
			addMember();
		} else if (choice == SELECT_MEMBER) {
			selectMember();
		} else {
			goBack();
		}
	}
	
	//Outputs a list of all family members sorted by income
	private void sortByIncome () {
		Member [] sorted;
		System.out.println("List of family members sorted by income: ");
		sorted = family.sortMemberByIncome ();
		
		for (int i = 0; i < sorted.length; i ++) {
			System.out.println ("Name: " + sorted [i].getName());
			System.out.println("Income: "+ sorted[i].getIncome());
			System.out.println ("");
		}
		displayMenu();
	}
	
	//Outputs a list of all family members sorted by expense
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
	
	//Selects family member takes the user to another menu where they can make changes...
	//... to the selected family member
	private void selectMember () {		
		System.out.println("Please enter the name of the member you'd like to select: ");
		UserInput.flush();
		String name = UserInput.intakeName();
		Member selected = family.searchMember(name);
		
				
		System.out.println("Member " + selected.getName() + " is selected");
		editFamMemberMenu(selected);
		
	} 
	
	//Adds a new family member to the family
	private void addMember () {
		String name;
		double income;
		double budget;
		double percentage;
		String isAdult;
		
		//Prompts user for all necessary info to create new family member
		System.out.println ("Please enter the name of the family member you'd like to add : ");
		UserInput.flush();
		name = UserInput.intakeString();
		System.out.println("Please enter your monthly income: ");
		income = UserInput.intakeDouble();
		System.out.println("Please enter your monthly budget: ");
		budget = UserInput.intakeDouble();
		System.out.println("Please enter the percentage of your income you'd like to devote to your monthly budget: ");
		percentage = UserInput.intakeDouble()/100;
		System.out.println("Are you a child or an adult? (Please enter 'c' for child or 'a' for adult)");
		UserInput.flush();
		isAdult = UserInput.intakeType("a", "c");
		
		//Creates the new family member based on whether they are an adult or child
		if (isAdult.equalsIgnoreCase("a")) {
			family.addFamilyMember(new Adult (name, income, budget, percentage));
		} else {
			family.addFamilyMember(new Child (name, income, budget, percentage));
		}
		System.out.println("Family member added.");
		
		displayMenu();
	}
	
	//Allows the user to make edits to a specific family member they have selected
	private void editFamMemberMenu(Member person) {
		final int EDIT_INCOME = 1;
		final int EDIT_BUDGET = 2;
		final int SET_GOAL = 3;
		final int DELETE_GOAL = 4;
		final int GOBACK = 5;
		
		System.out.println("\n\nEDIT FAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-3 that corresponds to one of the following choices: ");
		System.out.println("1. Edit income");
		System.out.println("2. Edit monthly budget");
		System.out.println("3. Set a new goal");
		System.out.println("4. Delete goal");
		System.out.println("5. Go back to main menu");
		
		int choice = UserInput.intakeChoice(5);
		
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
	
	//Deletes the goal of a specific family member
	private void deleteGoal(Member person) {
		// How to delete goal 
		family.deleteGoal(person);
		System.out.println("Goal was successfully deleted");
		editFamMemberMenu(person);
	}
	
	//Set the goal for a specific family member
	private void setGoal(Member person) {
		System.out.println("Enter the goal description: ");
		UserInput.flush();
		String desc = UserInput.intakeString();
		System.out.println("Enter the amount for the goal: $");
		double amount = Math.abs(UserInput.intakeDouble());
		
		//If the goal is unable to be set, prompts the user to fix the possible issues preventing...
		//... them from setting a goal
		boolean success = false;
		while(!success) {
			try {
				person.setGoal(new Goal(amount, desc));
				success = true;
				System.out.println("Goal successfully set.");
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
		
		editFamMemberMenu(person);
	}
	
	//Changes the income of a specific family member
	private void changeIncome (Member person) {
		System.out.print("Enter the new income of the family member: $");
		double income = Math.abs(UserInput.intakeDouble());	
		
		person.setIncome(income);
		System.out.println("Income changed.");
		editFamMemberMenu(person);
	}
	
	//Changes/updates the budget for a specific family member
	private void changeBudget (Member person) {
		System.out.print("Enter the new montly budget of the family member: ");
		double budget = Math.abs(UserInput.intakeDouble());
		
		person.setBudget(budget);
		System.out.println("Budget changed.");
		
		editFamMemberMenu(person);
	}
	
	
}
