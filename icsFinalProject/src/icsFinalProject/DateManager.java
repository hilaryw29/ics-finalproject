package icsFinalProject;

import java.io.*;
//import java.time.LocalDate; 
import java.time.LocalDateTime; 
import java.util.*;
//import java.time.LocalTime; 
//import java.time.Month;

//Class Description: Running in the back to keep track of time, pays bills automatically
//according to preauthorized time, record the payment to transactions, and generate monthly report.
public class DateManager implements Runnable{
	RecurringBillsList billList;
	FamilyBudgetManagement manager;
	
	//
	public DateManager(RecurringBillsList billList, FamilyBudgetManagement manager) {
		this.billList = billList;
		this.manager = manager;
		
	}
	
	//
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			int month = getMonth();
			int time = calculateTimeToNextDay();
			try {
				sleep(time);
			} catch (InterruptedException e) {
				System.exit(0);
			}
			if (getMonth() != month) {
				generateMonthlyReport();
				resetBills();
			}
			pay();
		}
	}
	 
	//calculates the time
	private int calculateTimeToNextDay() {
		LocalDateTime rightNow = LocalDateTime.now();
		int hour = rightNow.getHour();
		int minute = rightNow.getMinute();
		return ((23 - hour) * 60 + (60 - minute))*60*1000;
	}
	
	private int getMonth() {
		Calendar c = Calendar.getInstance();  
	    c.setTime(new Date());  
	    int month = c.get(Calendar.MONTH);
	    return month;
	}
	//the thread is given the order to sleep
	private void sleep(int time) throws InterruptedException{
		Thread.sleep(time);
	}
	
	private void resetBills() {
		billList.resetBills();
	}
	
	private void generateMonthlyReport() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(FileConstant.MONTHLYREPORT,false));
			out.write(manager.displayFamilyInfo());
			out.newLine();
			out.write(manager.displayTransaction());
			out.newLine();
			out.write(manager.displayMonthlyBills());
			out.close();
		} catch(IOException e) {
			
		}	
	}
	
	private void pay() {
		LinkedList<RecurringBill> bills = billList.getBillList();
		for (RecurringBill i: bills) {
			if (manager.tryPay(i.getName(), i.getAccountID(), i.getAmount())) {
				try {
					manager.addTransaction(i.generateTransaction());
				} catch (AccountException e) {
				}
			} else {
				i.setFailed(true);
				System.out.println("The bill is not payed due to low account balance:");
				System.out.println(i);
			}
		}
	}

}
