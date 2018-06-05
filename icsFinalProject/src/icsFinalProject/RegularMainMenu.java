package icsFinalProject;

import java.util.*;
import java.io.*;

public class RegularMainMenu {
	Scanner sc = new Scanner(System.in);
	FamilyBudgetManagement family;
	

	public RegularMainMenu() {		
		intakePin();
	}
	
	private void intakePin() {
		String input, answer;
		boolean success = false;
		
		System.out.println("Please enter password: ");
		
		while (!success) {
			try {
				family = new FamilyBudgetManagement(sc.nextLine(), FileConstant.MEMBERINFO);
				sucess = true;
			} catch (PINNotMatchException pinex) {
				System.out.println("Incorrect password. Enter again.");
			} catch (FileModifiedException modex) {
				System.out.println("The save files were modified. PLease correct this and run the program again.");
			} catch (FileNotFoundException foundex) {
				System.out.println("The save files were not found. Please correct this and run the program again.");
			} catch (IOException iox) {
			}
		}
		
		System.out.println("Password verified");

		displayMenu();
		/*try {
			BufferedReader in = new BufferedReader(new FileReader(FileConstant.PIN));
			
			answer = in.readLine();
			input = sc.nextLine();
			
			while (!input.equals(answer)) {
				System.out.println("Incorrect. Please try again.");
				input = sc.nextLine();
			}
			
		} catch (IOException iox) {
		}*/
	}

	public void displayMenu() {
		System.out.println("Welcome. Please enter a number 1-3 that corresponds to the following choices: ");
		System.out.println("1. Generate previous montly report");
		System.out.println("2. Transanctions menu");
		System.out.println("3. View or edit current info\n");
		System.out.println("Your current total family balance: + $" + family.getHouseHoldBalance());
		
		if (loadalert()) {
			System.out.println("ALERT: your current household balance of $" + family.getHouseHoldBalance() + " is lower than your alert threshold of " + family.getMinHouseHoldBalance());
		} else {
			System.out.println("You have no current alerts");
		}
	}

	private int intakeChoice() {
		int input;
		boolean success = false;
		
		
		input = sc.nextInt();
		while (!success) {
			try {
				input = sc.nextInt();
				if (!(input >= 1 && input <= 3)) {
					success = true;
				} else {
					System.out.println("Invlaid choice. Please enter again.");
				}
			} catch (InputMismatchException inpx) {
				sc.nextLine();
				System.out.println("Invlaid choice. Please enter again.");
			}
		}
		return input;
	}
	
	private boolean loadalert() {
		if (family.getHouseHoldBalance() <  family.getMinHouseHoldBalance()) {
			return true;
		}
		return false;
	}
}
