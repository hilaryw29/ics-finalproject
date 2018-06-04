package icsFinalProject;

import java.util.*;
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
}
