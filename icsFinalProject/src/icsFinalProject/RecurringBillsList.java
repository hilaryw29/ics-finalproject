package icsFinalProject;

import java.util.*;
import java.time.LocalDateTime; 
import java.io.*;

//Class description: a class that have a list of monthly bills and performs adding, 
//displaying information, displaying unpaid bills and other functionalities.
public class RecurringBillsList{
	int numOfBills;
	TreeSet<RecurringBill> billList;
	
	//contructs a RecurringBill object by intaking the information from the given file
	public RecurringBillsList(String fileName) throws IOException, FileNotFoundException, FileModifiedException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		numOfBills = Integer.parseInt(in.readLine());
		billList = new TreeSet<>();
		for(int i = 0; i < numOfBills; i ++){
			int ID = Integer.parseInt(in.readLine().substring(4));
			int accountID = Integer.parseInt(in.readLine().substring(12));
			double amount = Double.parseDouble(in.readLine().substring(8));
			String name = in.readLine().substring(6);
			String date = in.readLine().substring(6);
			boolean isFailed = Boolean.parseBoolean(in.readLine().substring(24));
			RecurringBill bill = new RecurringBill(amount, name, date, accountID);
			bill = new RecurringBill(bill, ID);
			bill.setFailed(isFailed);
			billList.add(bill);
		}
		in.close();		
	}
	
	//constructs a new RecurringBillList
	public RecurringBillsList() {
		billList = new TreeSet<>();
		numOfBills = 0;
	}
	
	//intakes a bill and adds to the recurring bill list
	public void addBill(RecurringBill bill) {
		int ID = numOfBills;
		billList.add(new RecurringBill(bill, ID));
		numOfBills++;
	}
	
	//returns all the bills for the current date
	public LinkedList<RecurringBill> getBillList() {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		LinkedList<RecurringBill> list = new LinkedList<>();
		for (RecurringBill i: billList) {
			if (Integer.parseInt(i.getDate()) == day) {
				list.add(i);
			}
		}
		return list;
	}

	public void resetBills() {
		for (RecurringBill i:billList) {
			i.setFailed(false);
		}
	}
	//returns all the unpaid bills in a list
	public LinkedList<RecurringBill> getUnpaidList() {
		Iterator listInterator = billList.iterator();
		LinkedList<RecurringBill> unpaidList = new LinkedList<>();
		while (listInterator.hasNext()) {
			RecurringBill current = (RecurringBill) listInterator.next();
			if (current.isFailed()) {
				unpaidList.add(current);
			}
		}
		return unpaidList;
	}

	//writes all the bills to file
	public void writeFile() {
		try{
			Iterator<RecurringBill> listInterator = billList.iterator();
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.BILLS));
			out.write(""+numOfBills);
			out.newLine();
  			while(listInterator.hasNext()){  
   				out.write(listInterator.next().toString());  
   				out.newLine();
			}
			out.close();
		}
		catch(IOException io){
			return;
		}
	}
	
	//deletes a recurring bill given the id of the bill
	public boolean delBill(int id) {
		return billList.remove(new RecurringBill(new RecurringBill(0,"0","0",0), id));
	}

	//displays the bills for the month
	public String displayMonthlyBills() {
		String s = "";
		for (RecurringBill i: billList) {
			s+= i.toString();		
		}
		return s;
	}
}
