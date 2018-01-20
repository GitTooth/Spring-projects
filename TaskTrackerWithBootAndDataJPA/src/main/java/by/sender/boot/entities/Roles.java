package by.sender.boot.entities;

import java.util.ArrayList;
import java.util.List;

public class Roles {
	private static String admin = "ROLE_MANAGER";
	private static String user = "ROLE_DEVELOPER";
	
	public static List<String> getList(){
		List<String> list = new ArrayList<String>();
		list.add(admin);
		list.add(user);
		return list; 
	}
	
}
