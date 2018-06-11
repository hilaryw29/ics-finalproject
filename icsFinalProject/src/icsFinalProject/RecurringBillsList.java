package icsFinalProject;

import java.util.*;
import java.time.LocalDateTime; 
import java.io.*;

public class RecurringBillsList implements Serializable{
	int numOfBills;
	TreeSet<RecurringBill> billList;
	public RecurringBillsList(String fileName) throws IOException, FileNotFoundException, FileModifiedException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		numOfBills = Integer.parseInt(in.readLine());
		billList = new TreeSet<>();
		for(int i = 0; i < numOfBills; i ++){
			double amount = Double.parseDouble(in.readLine());
			String name = in.readLine();
			String date = in.readLine();
			int accountID = Integer.parseInt(in.readLine());
			int ID = Integer.parseInt(in.readLine());
			RecurringBill bill = new RecurringBill(amount, name, date, accountID);
			billList.add(new RecurringBill(bill, ID));
		}
		in.close();		
	}
	
	public RecurringBillsList() {
		billList = new TreeSet<>();
		numOfBills = 0;
	}
	
	public void addBill(RecurringBill bill) {
		int ID = billList.last().getID()+1;
		billList.add(new RecurringBill(bill, ID));
	}
	public LinkedList<RecurringBill> getBillList() {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		LinkedList<RecurringBill> list = new LinkedList<>();
		for (RecurringBill i: billList) {
			if (Integer.parseInt(i.getDate())==day) {
				list.add(i);
			}
		}
		return list;
	}

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

	public void writeFile() {
		try{
			Iterator<RecurringBill> listInterator = billList.iterator();
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.BILLS));
			out.write(numOfBills);
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
	public boolean delBill(int id) {
		return billList.remove(new RecurringBill(new RecurringBill(0,"0","0",0), id));
	}

	public String displayMonthlyBills() {
		String s = "";
		for (RecurringBill i: billList) {
			s+= i.toString();		
		}
		return s;
	}
	

}
