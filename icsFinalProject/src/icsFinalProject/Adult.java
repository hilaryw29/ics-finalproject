package icsFinalProject;

import java.io.*;

//Class Description: the class inherits the characteristics of a member and creates a new object
public class Adult extends Member implements Serializable{
	
	//Takes in a name, income, budget and a percentage to construct an adult object
	public Adult(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
	}
	
	//Takes in a goal object and return a boolean indicating whether the goal is
	//set successfully or not or throws a GoalException if the goal already exists
	public boolean setGoal (Goal adultGoal) throws GoalException {
		if (goal == null) {
			goal = adultGoal;
			return true;
		} else {
			throw new GoalException (false, true, false);
		}
	}
	
	
	//the method inherits the member class's method and writes out the info of
	// an adult member's information to a file
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
			out.write("<Balance>0</Balance>");				
			out.newLine();
			out.write("<Role>a</Role>");
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}

}
