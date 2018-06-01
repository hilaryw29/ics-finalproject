package icsFinalProject;

import java.io.*;

public class FirstMainMenu {

	//public FirstMainMenu () {
	//	isFirstRun();
	//}/
	
	public boolean isFirstRun () {
		try {
			BufferedReader in = new BufferedReader(new FileReader (".\\resources\\save.txt"));
			System.out.println(in.readLine() + "II");

		} catch (Exception e) {
			System.out.println("LOL");
		}
		return false;
	}
}

