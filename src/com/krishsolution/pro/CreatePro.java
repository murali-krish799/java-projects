package com.krishsolution.pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CreatePro {

	private static final String String = null;

	public void createProFile(String fileName) throws IOException {

		File file = createFile(fileName);

		// try with resource avoid memory leakage
		try (FileOutputStream fos = new FileOutputStream(file);) {
			proData().store(fos, "data base data details");
			System.out.println("data inserted successfully...");
		}
	}

	private File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {

			file.createNewFile();
			System.out.println("file got created..");
		}
		return file;
	}

	public Properties proData() {

		Properties pro = new Properties();
//		pro.setProperty("username", "krish");
//		pro.setProperty("password", "krish123");
//		pro.setProperty("url", "http://localhost:8080/db");
		return pro;

	}

	public void getproDetails(String fileName) throws IOException {

		File file = createFile(fileName);
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file);) {

				Properties pro = proData();
				pro.load(fis);
				Set<Object> keySet = pro.keySet();
				for (Object obj : keySet) {

					System.out.println((String) obj);
				}
				System.out.println("******");
				pro.keySet().forEach(e -> System.out.println((String) e));

				pro.keySet().forEach(System.out::println);
				System.out.println("data in console..");
			}
		}

	}

	public Properties proUpdate(Map<String, String> changeData, String fileName) throws IOException {
		File file = createFile(fileName);
		HashSet<String>avoidDuplicate= new HashSet<>();
		
		Properties proData = proData();
		Properties oldData=proData;
		ArrayList<Object> mapList = new ArrayList<>(changeData.keySet());

		if (file.exists()) {

			try (FileInputStream fis = new FileInputStream(file);) {

				proData.load(fis);
				ArrayList<Object> proList = new ArrayList<>(proData.keySet());

				int i = 0;
				int j = 0;

				while (i <= proData.size() - 1) {

					j = 0;

					while (j <= changeData.size() - 1) {
						if (proData.containsKey(mapList.get(j))) {
							
							avoidDuplicate.add((String)mapList.get(j));
							String proKey = (String) proList.get(i);
							String mapKey = (String) mapList.get(j);

							if (proKey.equals(mapKey)) {

								proData.setProperty(proKey, changeData.get(mapKey));
							}

						}
						j++;
					}
					i++;
				}
			}
			try (FileWriter fw = new FileWriter(file);) {
				if (avoidDuplicate.size() == mapList.size()) {
					proData.store(fw, "change properties");
					System.out.println("data updated successfully :with new credetials");
				} else {
					oldData.store(fw, "old date doesn't update");
					System.out.println("old data available only");
					System.out.println("update not happaned may be keys doesn't match to properties file \n try again with proper  keys");
				}
			}
		}
		return proData;
	}

}
