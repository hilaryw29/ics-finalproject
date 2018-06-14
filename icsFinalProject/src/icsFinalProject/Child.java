package icsFinalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

//Class Description: the class inherits the characteristics of a member and creates a new object
public class Child extends Member implements Serializable{
	private final double MAX_GOAL_AMOUNT = 5000;
	
	////Takes in a name, income, budget and a percentage to construct a child object
	public Child(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
	}
	
	//Takes in a goal object and and returns a boolean indicating whether the goal
	//is set successfully or not, if unsuccessful, the method will return a GoalException
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

	//the method inherits the member class's method and writes out the info of
	//a child member's information to a file
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
			out.write("<Balance>0</Balance>");				
			out.newLine();
			out.write("<Role>c</Role>");
			out.newLine();
			out.close();
		} catch (IOException iox) {
		}
	}

}
