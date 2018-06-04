package icsFinalProject;

import java.util.Scanner;
import java.io.*;

public class RegularMainMenu {
	Scanner sc = new Scanner(System.in);
	FamilyBudgetManagement family;
	

	public RegularMainMenu() {		
		intakePin();
	}
	
	private void intakePin() {
		String input, answer;
		
		System.out.println("Please enter password: ");
		family = new FamilyBudgetManagement(sc.nextLine(), FileConstant.MEMBERINFO);
		
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
		System.out.println("Password verified");
	}
}
