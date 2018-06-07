package icsFinalProject;
import java.util.*;

public class GeneralFamilyInfo extends Submenu {
	final int VIEW_FAM_NAME = 1;
	final int EDIT_FAM_NAME = 2;
	final int VIEW_LOW_BALANCE = 3;
	final int EDIT_LOW_BALANCE = 4;
	final int EDIT_PIN = 5;
	
	public GeneralFamilyInfo (String pass) {
		super(pass);
		displayMenu();
	}
	public void displayMenu () {
		int choice;
		System.out.println ("GENERAL FAMILY INFO MENU");
		System.out.println("Please enter a number 1-5 that corresponds to one of the following choices: ");
		System.out.println("1. View family name");
		System.out.println("2. Edit family name");
		System.out.println("3. View low balance threshold");
		System.out.println("4. Edit low balance threshold");
		System.out.println("5. Edit pin");
		choice = intakeChoice (3);
		
		if (choice == VIEW_EDIT_FAM_NAME) {
			findByDate();
		} else if (choice == VIEW_EDIT_LOW_BALANCE) {
			addTrans();	
			addTransaction();
		} else if (choice == EDIT_PIN) {
			findByAmount();
		} 

	}
	
	
}
