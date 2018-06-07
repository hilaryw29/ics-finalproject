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
					Integer.parseInt(input,3);
					Integer.parseInt(input.substring(5), 1);
					Integer.parseInt(input.substring(8), 1);
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
}