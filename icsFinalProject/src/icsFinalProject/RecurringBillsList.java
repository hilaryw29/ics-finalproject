package icsFinalProject;

import java.util.*;
import java.io.*;
public class RecurringBillsList {
	LinkedList<RecurringBill> billList;
	int numOfBills;
	public RecurringBillsList(String fileName) throws IOException, FileNotFoundException, FileModifiedException {
		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			
		} catch(FileModifiedException e) {
			throw e;
		} catch(FileNotFoundException e) {
			throw e;
		} catch(IOException e) {
			throw e;sdfijnsdfmklsn
		}
	}
	
	public RecurringBillsList() {
		billList = new LinkedList();
	}
	
	
}
