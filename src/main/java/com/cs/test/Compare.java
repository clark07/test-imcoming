package com.cs.test;


import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Compare {

	public static void main(String[] args) throws Exception {

		File file1 = new File("C:\\Users\\admin\\Desktop\\erp学校.csv");
		File file2 = new File("C:\\Users\\admin\\Desktop\\xx.csv");

		BufferedReader br1 = new BufferedReader(new InputStreamReader(
				new FileInputStream(file1), "GBK"));

		String buf = "";
		int count = 0;
		Map<String, String> map1 = new HashMap<String, String>();
		while ((buf = br1.readLine()) != null) {
			if(count++==0) continue;
			
			String[] ss = buf.split(",");
			map1.put(ss[0].substring(1, ss[0].length()-1), ss[1].substring(1, ss[1].length()-1));
		}
		//System.out.println(map1);

		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				new FileInputStream(file2), "UTF-8"));
		Map<String, String> map2 = new HashMap<String, String>();
		count = 0;
		while ((buf = br2.readLine()) != null) {
			if(count++==0) continue;
			
			String[] ss = buf.split(",");
			map2.put(ss[0].substring(1, ss[0].length()-1), ss[1].substring(1, ss[1].length()-1));
		}
		//System.out.println(map2);
		
		Set<String> allKey = new HashSet<String>();
		allKey.addAll(map1.keySet());
		allKey.addAll(map2.keySet());
		
		String[] ignoreSchool = new String[]{"1387","1383","1385","1366","1535"};
		
		for (String key : allKey) {
			if(ArrayUtils.contains(ignoreSchool, key)){
				continue;
			}
			if(!map1.containsKey(key)){
				System.err.println(String.format("erp not contains school:%s", key));
			}else
			if(!map2.containsKey(key)){
				System.err.println(String.format("php not contains school:%s", key));
			}else{
				if(!map1.get(key).equals(map2.get(key))){
					System.err.println(String.format("school:%s--erp:%s not equals php:%s", key, map1.get(key), map2.get(key)));
				}
			}
			
			
		}
		//1812|1869|1959|1958
	}

}
