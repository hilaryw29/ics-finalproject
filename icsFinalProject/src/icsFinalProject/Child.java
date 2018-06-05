package icsFinalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Child extends Member {
	final double MAX_GOAL_AMOUNT = 5000;

	public Child(String name, double income, double budget, double percentage) {
		super(name, income, budget, percentage);
		// TODO Auto-generated constructor stub
	}
	
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
