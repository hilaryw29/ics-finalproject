package icsFinalProject;
import java.io.*;
import java.util.*;

//Class Description: the class stores a list of members in the family and can display
//all the transactions, show budget-keeping results, or transferring money between accounts
public class FamilyMemberList implements Serializable{
	private static final long serialVersionUID = 1L;
	private int numOfMember = 0;
	private ArrayList<Member> family;
	
	public FamilyMemberList(String memFile, String accFile){
		
	}
	
	//constructor that creates a new family member list
	public FamilyMemberList(){
		family = new ArrayList<>(10);
	}
	
	//intakes a member object and add to the family list
	public void addFamilyMember(Member m){
		family.add(m);
		numOfMember++;
	}
	
	//intakes a transaction and update the balance
	public void updateBalance(Transaction t) throws AccountException{
		for(int i = 0; i < numOfMember; i ++){
			//handles the situation when the member is paying
			if ((family.get(i).getName()).equals(t.getPayer())) {
				if(!family.get(i).updateBalance(t.getPayerAccountID(), t.getAmount()*(-1))){
					throw new AccountException(false,"Account Does Not Exist");  
					// throw exception when name doesn't match or account does not exist
				}
			}
			//handles the situation when the member is receiving money
			if ((family.get(i).getName()).equals(t.getPayee())) {
				if(!family.get(i).updateBalance(t.getPayeeAccountID(), t.getAmount())){
					throw new AccountException(false,"Account Does Not Exist");
					// throw exception when name doesn't match or account does not exist
				}
			}
		}
	}
	
	//check whether the given name of the member is in the list
	public Member isNameInList(String name){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				return family.get(i);
			}
		}
		return null;
	}
//	public boolean transferMoney(double, String)
	
	
	//assign budget by taking in the name of the member and the amount of the budget
	public boolean assignBudget(String name, double amount){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				family.get(i).setBudget(amount);
				return true;
			}
		}
		return false; 
	}
	
	//outputs the amount in the budget of the member with the given name
	public double checkBudget(String name){
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				return family.get(i).getBudget();
			}
		}
		return -1;
	}
	
	//checks the given account's balance for a member with the given name; 
	//return whether the balance is higher than the given amount
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
	
	//displays all the family members' information
	public String displayInfo(){
		String s = "";
		for(int i = 0; i < numOfMember; i ++){
			s += family.get(i) + "\n";
		}
		return s;
	}
	
	//sets a goal for each family member
	public void setGoal(Goal goal, String name) throws GoalException{
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).getName()).equals(name)){
				family.get(i).setGoal(goal);
			}
		}
		throw new GoalException(false, true, true);
		//throws the exception when name is not found or goal already existed or child limit is reached
	}
	
	//intakes an account object and adds to the accounts under the given member
	public int addAccount(Account account, String name) throws AccountException{
		for(int i = 0; i < numOfMember; i ++){
			if((family.get(i).equalTo(name))){
				return(family.get(i).addAccount(account));
			}
		}
		throw new AccountException(true, "Name Not Found"); 
		// throws the exception when name is not found
	}
	
	//lists all the accounts for all the family members 
	public LinkedList<Account> listAccount(){
		LinkedList<Account> account = new LinkedList<>();
		for (Member i: family) {
			account.addAll(i.getAccountList());
		}
		return account;
	}
	
	//lists all the accounts of a specific member given the name
	public LinkedList<Account> listAccount(String name){
		LinkedList<Account> account = new LinkedList<>();
		for (Member i: family) {
			if (i.equalTo(name)) {
				account.addAll(i.getAccountList());
				return account;
			}
		}
		return null;
	}
	
	//calculates the average expenses of the family
	public double calculateAveExpense(){
		return getTotalBalance() / numOfMember;
	}
	
	//sorts the member by income from low to high
	public Member[] sortMemberByIncome(){
		Member[] member = (Member[]) family.toArray(new Member[numOfMember]);
		for(int i = numOfMember - 1; i >=0; i --){
			int maxIndex = i; 
			for(int j = 0; j < i; j ++){
				if(member[j].compareToIncome(member[maxIndex]) > 0){
					maxIndex = j;
				}
			}
			Member temp = member[i];
			member[i] = member[maxIndex];
			member[maxIndex] = temp;
		}
		return member;
	}
	
	//sorts the member by expense from low to high
	public Member[] sortMemberByExpense(){
		Member[] member = (Member[]) family.toArray(new Member[numOfMember]);
        	for (int i = 0; i < numOfMember-1; i++){
            		for (int j = 0; j < numOfMember-i-1; j++){
                		if (member[j].compareToExpense(member[j+1]) > 0){
                    		Member temp = member[j];
                    		member[j] = member[j+1];
                    		member[j+1] = temp;
                		}
            		}
        	}
		return member;
	}
	
	//find a member by the given name
	public Member searchMember(String name){
		for(int i = 0; i < numOfMember; i ++){
			if(family.get(i).equalTo(name)){
				return family.get(i);
			}
		}
		return null;
	}
	
	//gets the total balance for the whole family
	public double getTotalBalance() {
		double sum = 0;
		for (int i = 0; i < numOfMember;i++) {
			sum+=family.get(i).getBalance();
		}
		return sum;
	}
	
	//gets the member with the lowest balance
	public Member findLowestBalance() {
		return searchLowestBalance(0);
	}
	
	//private method that searches the member with the lowest balance
	private Member searchLowestBalance(int depth) {
		if (depth == numOfMember - 1) {
			return family.get(depth);
		} else {
			return family.get(depth).compareTo(searchLowestBalance(depth+1));
			// recursion searching the list each time with a different depth
		}
	}
	
	//lists all the members in the family
	public ArrayList<Member> listMember(){
		return family;
	}
	
	//allocates the income for a specific member given the name and transfer part of the income
	//calculates by the percentage to a given account using id
	public void allocateIncome(String name, int id, int percentage) throws AccountException{
		for (Member i : family) {
			if (i.equalTo(name)) {
				if (!i.updateBalance(id, 1.0*i.getIncome()*percentage/100))
					throw new AccountException(false,name);
					//throws an exception when the account does not exist or the name does not match
				return;
			}
			
		}
		
	}
	
	public String toString() {
		return displayInfo();
	}
	
}
