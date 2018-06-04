package icsFinalProject;

import java.util.*;
import java.io.*;

public class FirstMainMenu {
	Scanner sc = new Scanner(System.in);

	public FirstMainMenu () {
		if (isFirstRun()) {
			displayMenu();	// Displays the info intake menu
		} else {
			RegularMainMenu regMenu = new RegularMainMenu();
		}
	}
	
	public void displayMenu () {
		int numMember = 0;			// Number of family members
		boolean finished = false;	// Used for input mismatch checking
		
		// Outputs greeting
		System.out.println("Welcome to first time setup. Please input the following info.");
		intakePassword();
		
		
		

		System.out.print("Please enter the number of family members: ");
		
		// Prompts user to enter number of family members
		while (!finished) {
			try {
				numMember = sc.nextInt();
				sc.nextLine();
				finished = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid entry. Please enter again.");
			}
		}
		
		// Family member info is entered
		for (int i = 0; i < numMember; i++) {
			System.out.println("Enter the name of family member #" + (i + 1));
			intakeName();
			intakeIncome();
			writeDefaults();
			intakeRole();
		}
		System.out.println("Thank you for setting up. Let's continue to the main menu.");
		RegularMainMenu regMenu = new RegularMainMenu();
	}
	
	private void intakePassword () {
		// Prompts user for pin and writes it to file
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.PIN));
					System.out.println("Please choose a password: ");
					out.write(sc.nextLine());
					out.newLine();
					out.close();
				} catch (IOException iox) {
				}
	}
	
	private void intakeRole() {
		boolean finished = false;
		String input;
		
		// Prompts user for member role and writes it to the file
		while (!finished) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MEMBERINFO, true));
				
				System.out.println("Please enter the name of their role in the family. Enter 'a' for adult or 'c' for child: ");
				if ((input = sc.nextLine()).equalsIgnoreCase("a") || input.equalsIgnoreCase("c")) {
					out.write(input);
					out.newLine();
					finished = true;
				}
				out.close();
			} catch (IOException iox) {
			}
		}
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
	
	private void intakeName () {
		// Prompts user for member name and writes it to file
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MEMBERINFO, true));
			out.write(sc.nextLine());
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}
	
	private void intakeIncome() {
		boolean finished = false; 
		
		// Prompts user for member income and writes it to file
		while (!finished) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MEMBERINFO, true));
				
				System.out.println("Please enter the name of their monthly income in dollars: ");
				out.write(sc.nextDouble() + "");
				out.newLine();
				sc.nextLine();
				finished = true;
				out.close();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid input. Please try again.");
			} catch (IOException iox) {
			}
		}
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

