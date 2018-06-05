public class FamilyMemberList{
	private int numOfMember;
	private ArrayList<Member> family;
	
	public FamilyMemberList(String memFile, String accFile){
	
	}
	
	public void updateBalance(Transaction t){
		for(int i = 0; i < numOfMember; i ++){
			if ((family.get(i).getName()).equals(t.getPayer())) {
				if(!family.get(i).updateBalance(t.getID, (family.get(i).getBalance() - t.getAmount))){
					throw new AccountException;
				}
			}
			if ((family.get(i).getName()).equals(t.getPayee())) {
				if(!family.get(i).updateBalance(t.getID, (family.get(i).getBalance() + t.getAmount))){
					throw new AccountException;
				}
			}
		}
	}
	public Member isNameInList(String name){
		int bottom = 0;
		int top = numOfMember-1;
		boolean found = false;
		int middle;
		
		while(!found && top > bottom){
			middle = (top + bottom) / 2;
			if((family.get(middle).getName()).equals(name)){
				return family.get(middle);
			}
			else if((family.get(middle).getName())
		}
	}
}