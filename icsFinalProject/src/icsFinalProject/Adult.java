package icsFinalProject;

import java.io.*;

public class Adult extends Member {

	public Adult(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
		// TODO Auto-generated constructor stub
	}
	
	public boolean setGoal (Goal adultGoal) throws GoalException {
		if (goal == null) {
			goal = adultGoal;
			return true;
		} else {
			throw new GoalException (false, true, false);
		}
	}
	
	
	@Override
	public void writeFile() {
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
			out.write("<Role>a</Role>");
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}

}
