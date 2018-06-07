import java.util.*;
public class FamilyMemberList{
	private int numOfMember;
	private ArrayList<Member> family;
	
	public FamilyMemberList(String memFile, String accFile){
	
	}
	public FamilyMemberList(){
		
	}
	public void addFamilyMember(Member m){
		family.add(m);
	}
	public void updateBalance(Transaction t) throws AccountException{
		for(int i = 0; i < numOfMember; i ++){
			if ((family.get(i).getName()).equals(t.getPayer())) {
				if(!family.get(i).updateBalance(t.getId(), (family.get(i).getBalance() - t.getAmount))){
					throw new AccountException();
				}
			}
			if ((family.get(i).getName()).equals(t.getPayee())) {
				if(!family.get(i).updateBalance(t.getId(), (family.get(i).getBalance() + t.getAmount))){
					throw new AccountException();
				}
			}
			if((family.get(i).getName()).equals(t.getPayer()) && (family.get(i).getName()).equals(t.getPayee())){
				throw new AccountException(true, false);
			}
		}
	}
	public Member isNameInList(String name){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				return family.get(i);
			}
		}
		return null;
	}
//	public boolean transferMoney(double, String)
	
	public boolean assignBudget(String name, double amount){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				family.get(i).setBudget(amount);
				return true;
			}
		}
		return false; 
	}
	public double checkBudget(String name){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				return family.get(i).getBudget();
			}
		}
		return -1;
	}
	public boolean checkAccountBalance(String name, int id, double amount){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				if(family.get(i).findAccount(id).getBalance() < amount){
					return false;
				}
				else{
					return true;
				}
			}
		}
		return false;
	}
	public void displayInfo(){
		for(int i = 0; i < numOfMember; i ++){
			System.out.println(family.get(i));
		}
	}
	public void setGoal(Goal goal, String name) throws GoalException{
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				family.get(i).setGoal(goal);
			}
		}
		throw new GoalException(false, true, true);
	}
	public int addAccount(Account account, String name) throws AccountException{
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				return(family.get(i).addAccount(account));
		}
		throw new AccountException(true, false);
	}
	public LinkedList<Account> listAccount(){
		
	}
	
	public double calculateAveExpense(){
		return getTotalBalance() / numOfMember;
	}
	
	public Member[] sortMemberByIncome(){
		for(int i = numOfMember - 1; i >=0; i --){
			int maxIndex = 0; 
			for(int j = 0; j < i; j ++){
				if(family.get(j).getIncome() > family.get(maxIndex).getIncome()){
					maxIndex = j;
				}
			}
			family.set(maxIndex, family.set(i, family.get(maxIndex)));
		}
	}
	public Member[] sortMemberByExpense(){
		for(int i = numOfMember - 1; i >=0; i --){
			int maxIndex = 0; 
			for(int j = 0; j < i; j ++){
				if(family.get(j).getExpense() > family.get(maxIndex).getExpense()){
					maxIndex = j;
				}
			}
			family.set(maxIndex, family.set(i, family.get(maxIndex)));
		}
	}
	public Member searchMember(String name){
		boolean found = false;
		for(int i = 0; i < numOfMember && !found; i ++){
			if((family.get(i).getName()).equals(name)){
				return family.get(i);
				found = true;
			}
		}
		return null;
	}
	
	public double getTotalBalance() {
		double sum = 0;
		for (int i = 0; i < numOfMember;i++) {
			sum+=family.get(i).getBalance();
		}
		return sum;
	}

	public Member findLowestBalance() {
		return searchLowestBalance(0);
	}
	
	private Member searchLowestBalance(int depth) {
		if (depth == numOfMember - 1) {
			return family.get(depth);
		} else {
			return family.get(depth).compareTo(searchLowestBalance(depth+1));
		}
	}
}