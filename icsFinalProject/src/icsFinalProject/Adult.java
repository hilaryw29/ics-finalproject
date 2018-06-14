package icsFinalProject;

import java.io.*;

public class Adult extends Member implements Serializable{
	
	//Constructor for Adult, creates new Adult object given name, income, budget, and percentage
	public Adult(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
		// TODO Auto-generated constructor stub
	}
	
	//Takes in a goal object and return a boolean indicating whether the goal is set successfully...
	//... or not or throws a GoalException if the goal already exists
	public boolean setGoal (Goal adultGoal) throws GoalException {
		if (goal == null) {
			goal = adultGoal;
			return true;
		} else {
			throw new GoalException (false, true, false);
		}
	}
	
	//Writes information of adult to file
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
