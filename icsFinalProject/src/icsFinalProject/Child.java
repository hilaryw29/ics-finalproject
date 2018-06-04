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
			out.write("<Name>" + name + "</Name>");
			out.newLine();
			out.write("<Income>" + income + "</Income>");
			out.newLine();
			out.write("<Budget>-1</Budget>");
			out.newLine();
			out.write("<Expense>0</Expense>");
			out.newLine();
			out.write("<Balance>0</Balance>");				// wtf is balance lol
			out.newLine();
			out.write("<Role>c</Role>");
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}

}
