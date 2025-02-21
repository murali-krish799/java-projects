package com.krishsolution.pro;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Client {
	public static void main(String[] args) throws IOException {
		
		Hashtable<String,Integer> hashTable= new Hashtable<>();
		hashTable.put("krish", 1);
		
		Enumeration<Integer> elements = hashTable.elements();
		String name="C:/Program Files/Java/jdk-17/bin";
		while(elements.hasMoreElements()) {
			
			System.out.println(elements.nextElement());
		}
		
		
		CreatePro createPro= new CreatePro();
		
//		createPro.createProFile("dbConfig.properties");
	
//	createPro.getproDetails("dbConfig.properties");
		Map<String, String>map= new HashMap<>();
		map.put("username", "srav");
		map.put("url", "updated,,");
	createPro.proUpdate(map, "dbConfig.properties");
	
	
	}

}
