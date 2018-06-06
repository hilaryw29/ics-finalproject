package icsFinalProject;

import java.util.*;
import java.time.LocalDateTime; 
import java.io.*;

public class RecurringBillsList {
	LinkedList<RecurringBill> billList;
	int numOfBills;
	public RecurringBillsList(String fileName) throws IOException, FileNotFoundException, FileModifiedException {
		BufferedReader read = new BufferedReader(new FileReader(fileName));
		String line = read.readLine();
		if (!line.endsWith("</Num>")) throw new FileModifiedException(fileName,"")
	}
	
	public RecurringBillsList() {
		billList = new LinkedList();
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
			if (!current.pay()) {
				unpaidList.add(current);
			}
		}
		return unpaidList;
	}
}
