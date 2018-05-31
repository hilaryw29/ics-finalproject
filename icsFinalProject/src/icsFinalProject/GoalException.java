package icsFinalProject;

public class GoalException extends Exception {
	private boolean nameNotFound, goalExisted, reachChildrenLimit;

	public GoalException(boolean nameNotFound, boolean goalExisted, boolean reachChildrenLimit) {
		this.nameNotFound = nameNotFound;
		this.goalExisted = goalExisted;
		this.reachChildrenLimit = reachChildrenLimit;
	}
	
	public boolean isNameFound() {
		return nameNotFound;
	}
	
	public boolean getIfReachChildrenLimit(){
		return reachChildrenLimit;
	}
	
	public boolean isGoalExisted(){
		return goalExisted;
	}
}