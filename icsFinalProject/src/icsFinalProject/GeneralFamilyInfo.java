package icsFinalProject;
import java.util.*;

public class GeneralFamilyInfo extends Submenu {
	final int VIEW_EDIT_FAM_NAME = 1;
	final int VIEW_EDIT_LOW_BALANCE = 2;
	final int EDIT_PIN = 3;
	
	public GeneralFamilyInfo (String pass) {
		super(pass);
		displayMenu();
	}
	public void displayMenu () {
		int choice;
		System.out.println ("GENERAL FAMILY INFO MENU");
		System.out.println("Please enter a number 1-3 that corresponds to one of the following choices: ");
		System.out.println("1. View/edit family name");
		System.out.println("2. View/edit low balance threshold");
		System.out.println("3. Edit pin");
		choice = intakeChoice (3);

	}
}
