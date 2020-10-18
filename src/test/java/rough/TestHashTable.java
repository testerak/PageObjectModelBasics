package rough;

import java.util.Hashtable;

public class TestHashTable {

	public static void main(String[] args) {
		//  Hashtable is part of java collection util
		
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("Iteration", "1");
		table.put("TestData", "Login1");
		table.put("Browser", "Chrome");
		table.put("RunMode", "Y");
		
		
		// to get data simple use table.get  each row is a new hashtable
		table.get("TestData");
		System.out.println(table.get("TestData"));
	
	
	
	}

		
	
	
}
