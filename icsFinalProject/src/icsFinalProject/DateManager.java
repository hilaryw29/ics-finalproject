package icsFinalProject;

//import java.time.LocalDate; 
import java.time.LocalDateTime; 
import java.util.*;
//import java.time.LocalTime; 
//import java.time.Month;

public class DateManager implements Runnable{
	RecurringBillsList billList;
	
	public DateManager(RecurringBillsList billList, FamilyBudgetManagement manager) {
		
	}
	public void run() {
		
		int time = calculateTimeToNextDay();
		sleep(time);
		
	}
	
	private int calculateTimeToNextDay() {
		LocalDateTime rightNow = LocalDateTime.now();
		int hour = rightNow.getHour();
		int minute = rightNow.getMinute();
		return ((23 - hour) * 60 + (60 - minute))*60*1000;
	}
	
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}
	//mei wan cheng
	private void pay() {
		LinkedList<RecurringBill> bills = billList.getBillList();
		for (RecurringBill i: bills) {
			i.getAccountID()
			if (true) {
				
			}
		}
		
	}

}
