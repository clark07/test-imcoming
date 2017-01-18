package com.cs.test;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PurchasePrice {

	public static void main(String[] args) throws Exception {
		
		File file = new File("d:\\副本CBD成本价为0.csv");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "GBK"));
		
		
		String buf = "";
		while((buf=br.readLine())!=null){
			String[] datas = buf.split(",");
			System.out.println(StringUtils.join(datas, "-->"));
		}
		
	}
}
