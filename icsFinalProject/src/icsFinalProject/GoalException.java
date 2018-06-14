package icsFinalProject;

//Class description: the class Inherits the Exception class and throws an exception
//message when the goal already exists or when a child sets a goal beyond the limit
public class GoalException extends Exception {
	private boolean nameNotFound, goalExisted, reachChildrenLimit;

	//constructs an exception with whether the name is found or whether the goal exists
	//or whether the child limit is reached
	public GoalException(boolean nameNotFound, boolean goalExisted, boolean reachChildrenLimit) {
		this.nameNotFound = nameNotFound;
		this.goalExisted = goalExisted;
		this.reachChildrenLimit = reachChildrenLimit;
	}
	
	//return whether name is found or not
	public boolean isNameFound() {
		return nameNotFound;
	}
	
	//return whether the child limit is reached
	public boolean getIfReachChildrenLimit(){
		return reachChildrenLimit;
	}
	
	//return whether the goal has already existed
	public boolean isGoalExisted(){
		return goalExisted;
	}
}
