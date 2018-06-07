package icsFinalProject;

public class FamilyMemberMenu extends Submenu {
	
	public FamilyMemberMenu (String pass) {
		super(pass);
		displayMenu();
	}
	
	public void displayMenu () {
		int choice;
		System.out.println("FAMILY MEMBER MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. View family name");
		System.out.println("2. Edit family name");
		System.out.println("3. View low balance threshold");
		System.out.println("4. Edit low balance threshold");
		System.out.println("5. Edit pin");
		choice = intakeChoice(3);
	}
	
	public void sortByIncome () {
		Member [] sorted;
		System.out.print("List of family members sorted by income: ");
		sorted = family.sortMemberByIncome ();
		
		for (int i = 0; i < sort.length; i ++) {
			System.out.println (sorted [i]);
		}
	}
}
