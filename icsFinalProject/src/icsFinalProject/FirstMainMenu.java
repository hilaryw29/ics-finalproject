package icsFinalProject;

import java.io.*;

public class FirstMainMenu {

	public FirstMainMenu () {
		System.out.println(isFirstRun());
	}
	
	public boolean isFirstRun () {
		try {
			BufferedReader in = new BufferedReader(new FileReader (".\\resources\\save.txt"));
			return (in.readLine() == null);
		} catch (IOException e) {
			System.out.println("Yikes");
		}
	}
	return false;
}

