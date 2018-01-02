package by.sender.tooth.model;

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
	
	public static String getAdmin() {
		return admin;
	}
	public static void setAdmin(String admin) {
		Roles.admin = admin;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		Roles.user = user;
	}
	
}
