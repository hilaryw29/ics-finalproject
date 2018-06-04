package icsFinalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Child extends Member {

	public Child(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeFile() {
		// TODO Auto-generated method stub
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MEMBERINFO));
			out.write(name);
			out.newLine();
			out.write(income + "");
			out.newLine();
			out.write("-1");
			out.newLine();
			out.write("0");
			out.newLine();
			out.write("0");				// wtf is balance lol
			out.newLine();
			out.write("a");
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}

}
