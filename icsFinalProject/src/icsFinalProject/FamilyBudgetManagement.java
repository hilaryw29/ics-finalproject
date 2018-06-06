package icsFinalProject;

public class FamilyBudgetManagement {
	private int familyBalance;
	private double houseHoldBalance;
	private double minHouseHoldBalance;
	private Thread DateManager;
	private FamilyMemberList memberlist;
	private TransactionList transactionList;
	private RecurringBillsList billList;
	public FamilyBudgetManagement(String fileName, String PIN) throws PINNotMatchException, FileNotFoundException, FileModifiedException {
		
	}
	
	public int addTransaction(Transaction transaction) throws AccountException {
		updateBalance(transaction);
		updateHoldBalance();
	}
	
	private void updateBalance(Transaction transaction) throws AccountException {
		FamilyMemberList.updateBalance(transaction);
	}
	
	private void updateHoldBalance() {
		houseHoldBalance=FamilyMemberList.getTotalBalance();
	}
	
	
	

}
