package icsFinalProject;

public class NMN {
	private void editFamMemberMenu(Member person) {
		final int EDIT_INCOME = 1;
		final int EDIT_BUDGET = 2;
		final int GOBACK = 3;
		
		System.out.println("EDIT FAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-3 that corresponds to one of the following choices: ");
		System.out.println("1. Edit income");
		System.out.println("2. Edit monthly budget");
		System.out.println("3. Back to main menu");
		
		int choice = UserInput.intakeChoice(3);
		
		if (choice == EDIT_INCOME) {
			changeIncome(person);
		} else if (choice == EDIT_BUDGET) {
			changeBudget(person);
		} else {
			goBack();
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
