package icsFinalProject;

import java.util.*;

public class UserInput {
	static Scanner sc = new Scanner(System.in);
	
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
	
	public static int intakeChoice (int upper) {
		Scanner sc = new Scanner(System.in);
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

	public static String intakeDate () {
		Scanner sc = new Scanner(System.in);
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
	
	// GIVE THIS A TRY SEE IF IT WORKS YIKES YIKES YIKES YIKES 
	public static String intakeName (/*String pin*/) {
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
	
	public static String intakeString () {
		return sc.nextLine();
	}
	
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
	
	public static String intakeType(String a, String b) {
		boolean finished = false;
		String input = null;
		
		// Prompts user for member role and writes it to the file
		while (!finished) {
			if ((input = sc.nextLine()).equalsIgnoreCase(a) || input.equalsIgnoreCase(b)) {
				finished = true;
			}
		}
		return input;
	}

}
