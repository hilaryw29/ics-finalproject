package icsFinalProject;

import java.util.*;
import java.io.*;

public class FirstMainMenu {
	Scanner sc = new Scanner(System.in);
	String pin;
	FamilyBudgetManagement family;
	
	public FirstMainMenu () {
		if (isFirstRun()) {
			displayMenu();	// Displays the info intake menu
		} else {
			RegularMainMenu regMenu = new RegularMainMenu();
		}
	}//
	
	public void displayMenu () {
		int numMember = 0;			// Number of family members
		boolean finished = false;	// Used for input mismatch checking
		String roleInput;
		
		// Outputs greeting
		System.out.println("Welcome to first time setup. Please input the following info.");
		intakePassword();
		
		
		

		System.out.print("Please enter the number of family members: ");
		
		// Prompts user to enter number of family members
		/*while (!finished) {
			try {
				numMember = sc.nextInt();
				sc.nextLine();
				finished = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid entry. Please enter again.");
			}
		}*/
		numMember = UserInput.intakeInt();
		
		// Family member info is entered
		System.out.println("Please enter the name of their role in the family. Enter 'a' for adult or 'c' for child: ");
		String role = UserInput.intakeType("a", "c");
		
		
		for (int i = 0; i < numMember; i++) {
			if (role.equalsIgnoreCase("a")) {
				family.addFamilyMember(new Adult(intakeName(), intakeIncome(), intakeBudget(), intakePercentage()));
			} else {
				family.addFamilyMember(new Child(intakeName(), intakeIncome(), intakeBudget(), intakePercentage()));
			}
		}
		
		
		System.out.println("Thank you for setting up. Let's continue to the main menu.");
		RegularMainMenu regMenu = new RegularMainMenu();
	}
	
	private void intakePassword () {
		// Prompts user for pin and writes it to file
		System.out.println("Please choose a password: ");
		pin = sc.nextLine();
		family = new FamilyBudgetManagement(pin);
	}
	
	private String intakeRole() {
		boolean finished = false;
		String input;
		
		// Prompts user for member role and writes it to the file
		while (!finished) {
			System.out.println("Please enter the name of their role in the family. Enter 'a' for adult or 'c' for child: ");
			if ((input = sc.nextLine()).equalsIgnoreCase("a") || input.equalsIgnoreCase("c")) {
				finished = true;
			}
		}
		return input;
	}
	
	private void writeDefaults() {
		// Writes default values for each member
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MEMBERINFO, true));
			out.write("-1");
			out.newLine();
			out.write("0");
			out.newLine();
			out.write("0");				// wtf is balance lol
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}
	
	private String intakeName () {
		// Prompts user for member name
		System.out.println("Enter the name of family member #" + (i + 1));
		return sc.nextLine();
	}
	
	private double intakeIncome() {
		System.out.print("Enter their monthly income in dollars: ");
		return intakeDouble();
	}
	
	private double intakeBudget () {
		System.out.print("Enter their monthly budget in dollars: ");
		return intakeDouble();
	}
	
	private double intakePercentage () {
		double input;
		System.out.print("Enter the percentage of income that is to be allocated to savings automatically: ");
		input = intakeDouble();
		
		while (input > 100) {
			System.out.println("Invalid input. Enter a number under 100.");
			input = intakeDouble();
		}
		return input;
	}
	
	// DEPRECATE
	private double intakeDouble() {
		boolean finished = false; 
		double input;
		
		while (!finished) {
			try {				
				input = sc.nextDouble();
				finished = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid input. Please try again.");
			}
		}
		return input;
	}
	
	public boolean isFirstRun () {
		try {
			BufferedReader in = new BufferedReader(new FileReader (FileConstant.MEMBERINFO));
			return (in.readLine() == null);
		} catch (IOException e) {
			System.out.println("Yikes");
		}
		return false;
	}
}

