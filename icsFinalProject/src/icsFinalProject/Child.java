package icsFinalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Child extends Member implements Serializable{
	private final double MAX_GOAL_AMOUNT = 5000;

	//Constructor - takes in a name, income, budget, and percentage to be moved to savings per month...
	//... to create a new Child object
	public Child(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
		// TODO Auto-generated constructor stub
	}
	
	//Takes in a Goal object and returns a boolean indicating whether the goal is set successfully... 
	//... or not, if unsuccessful, the method will return a GoalException
	public boolean setGoal (Goal childGoal) throws GoalException {
		if (goal == null) {
			if (childGoal.getAmount() <= MAX_GOAL_AMOUNT) {
				goal = childGoal; 
				return true;
			} else {
				throw new GoalException (false, false, true);
			}
		} else {
			throw new GoalException(false, true, false); 
		}
	}

	//Writes information of adult to file
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
