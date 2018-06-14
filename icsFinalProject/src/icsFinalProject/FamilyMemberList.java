package icsFinalProject;
import java.io.*;
import java.util.*;
public class FamilyMemberList implements Serializable{
	private static final long serialVersionUID = 1L;
	private int numOfMember = 0;
	private ArrayList<Member> family;
	
	public FamilyMemberList(String memFile, String accFile){
		
	}
	public FamilyMemberList(){
		family = new ArrayList<>(10);
	}
	public void addFamilyMember(Member m){
		family.add(m);
		numOfMember++;
	}
	public void updateBalance(Transaction t) throws AccountException{
		for(int i = 0; i < numOfMember; i ++){
			if ((family.get(i).getName()).equals(t.getPayer())) {
				if(!family.get(i).updateBalance(t.getPayerAccountID(), t.getAmount()*(-1))){
					throw new AccountException(false,"Account Does Not Exist");
				}
			}
			if ((family.get(i).getName()).equals(t.getPayee())) {
				if(!family.get(i).updateBalance(t.getPayeeAccountID(), t.getAmount())){
					throw new AccountException(false,"Account Does Not Exist");
				}
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
			if((family.get(i).equalTo(name))){
				return(family.get(i).addAccount(account));
			}
		}
		throw new AccountException(true, "Name Not Found");
	}
	
	public LinkedList<Account> listAccount(){
		LinkedList<Account> account = new LinkedList<>();
		for (Member i: family) {
			account.addAll(i.getAccountList());
		}
		return account;
	}
	
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
	
	public double calculateAveExpense(){
		return getTotalBalance() / numOfMember;
	}
	
	public Member[] sortMemberByIncome(){
		Member[] member = (Member[]) family.toArray(new Member[numOfMember]);
		for(int i = numOfMember - 1; i >=0; i --){
			int maxIndex = 0; 
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
	public Member searchMember(String name){
		for(int i = 0; i < numOfMember; i ++){
			if(family.get(i).equalTo(name)){
				return family.get(i);
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
	
	public ArrayList<Member> listMember(){
		return family;
	}
	
	public void allocateIncome(String name, int id, int percentage) throws AccountException{
		for (Member i : family) {
			if (i.equalTo(name)) {
				if (!i.updateBalance(id, 1.0*i.getIncome()*percentage/100))
					throw new AccountException(false,name);
				return;
			}
			
		}
		
	}
	
}
