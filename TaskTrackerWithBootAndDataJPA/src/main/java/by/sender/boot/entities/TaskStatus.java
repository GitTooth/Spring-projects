package by.sender.boot.entities;

import java.util.ArrayList;
import java.util.List;

public class TaskStatus {
	private static String waiting = "waiting";
	private static String implementation = "implementation";
	private static String verifying = "verifying";
	private static String releasing = "releasing";
	
	public static List<String> getList(){
		List<String> list = new ArrayList<String>();
		list.add(waiting);
		list.add(implementation);
		list.add(verifying);
		list.add(releasing);
		return list; 
	}
	
}
