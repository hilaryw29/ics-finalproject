package icsFinalProject;

import java.util.*;

public class Submenu {
	String password;
	protected FamilyBudgetManagement family;
	
	public Submenu (FamilyBudgetManagement family) {
		this.family = family;
	}
	
	public int intakeChoice (int upper) {
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

	public void goBack() {
		RegularMainMenu regMenu  = new RegularMainMenu (password);
	}
}
