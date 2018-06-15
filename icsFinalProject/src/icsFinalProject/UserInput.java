package icsFinalProject;

import java.util.*;

public class UserInput {
	static Scanner sc = new Scanner(System.in);
	
	//Intakes a double and error-checks to ensure that the input is valid
	public static double intakeDouble() {
		boolean finished = false; 
		double input = 0;
		
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
	
	//Intakes choice as an integer and ensures that the input is less than the upper bound...
	//... specified (passed in the parameter)
	public static int intakeChoice (int upper) {
		int input = 0;
		boolean finished = false;
		
		while (!finished) {
			try {
				input = sc.nextInt();
				if (input >= 1 && input <= upper) {
					finished = true;
				} else {
					System.out.println("Invalid input. Please enter again.");
				}
			} catch (InputMismatchException ix) {
				sc.nextLine();
				System.out.println ("Invalid input. Please enter again.");
			}
		}
		return input;
	}

	//Intakes a date in the format of a String and error-checks to ensure that the input...
	//... is valid
	public static String intakeDate () {
		String input = null;
		boolean valid = false;
		
		System.out.println("Enter the date in the format yyyy/mm/dd");
		while (!valid) {
			try {
				input = sc.nextLine();
				if (input.length() == 10 && input.charAt(4) == '/' && input.charAt(7) == '/') {
					Integer.parseInt(input.substring(0,3));
					Integer.parseInt(input.substring(5,6));
					Integer.parseInt(input.substring(8,9));
					valid = true;
				} else {
					System.out.println("Invalid input. Enter the date in the format yyyy/mm/dd");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Enter the date in the format yyyy/mm/dd");
			}
		}
		return input;
	}
	
	//Intakes a String input for a family member's name and error-checks to ensure...
	//... that the name entered actually correlates to a member in the family
	public static String intakeName () {
		String input = null;
		boolean found = false;
		
		while (!found) {
			input = sc.nextLine();
			if (RegularMainMenu.family.searchMember(input) == null) {
				System.out.println("Name not found. Try again.");
			} else {
				return input;
			}
		}
		return input;
	}
	
	//Intakes a String input
	public static String intakeString () {
		return sc.nextLine();
	}
	
	//Intakes an integer user response and error-checks to ensure the input is valid
	public static int intakeInt() {
		boolean success = false;
		int input = 0;
		
		while (!success) {
			try {
				input = sc.nextInt();
				success = true;
			} catch (InputMismatchException ix) {
				sc.nextLine();
				System.out.println("Invalid input. Please enter again.");
			}
		}
		return input;
	}
	
	//Intakes a String user response and error-checks to make sure that what the user entered...
	//... is within the (two) available options
	public static String intakeType(String a, String b) {
		boolean finished = false;
		String input = null;
		
		// Prompts user for member role and writes it to the file
		while (!finished) {
			if ((input = sc.nextLine()).equalsIgnoreCase(a) || input.equalsIgnoreCase(b)) {
				finished = true;
			} else {
				System.out.print("Invalid. Please enter " + a + " or " + b);
			}
		}
		return input;
	}

	//Flushes line to allow for input
	public static void flush() {
		sc.nextLine();
	}

}
