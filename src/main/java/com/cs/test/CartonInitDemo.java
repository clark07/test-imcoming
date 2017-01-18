package com.cs.test;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class CartonInitDemo {
	public static void main(String[] args) throws Exception {

		File file = new File("C:\\Users\\admin\\Desktop\\carton.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "GBK"));

		String buf = "";
		int count = 0;
		int errCount = 0;
		List<List<String>> dataList = new ArrayList<List<String>>();
		
		Set<String> filterSet = new HashSet<String>();
		filterSet.add("g");
		filterSet.add("G");
		filterSet.add("克");
		filterSet.add("ml");
		filterSet.add("无");

		while ((buf = br.readLine()) != null) {
			if (count++ == 0)
				continue;

			String[] datas = buf.split("###");
			if (datas.length != 3)
				continue;

			String cartonSize = StringUtils.trimToEmpty(datas[2]);

			if (!CartonUtil.validate(cartonSize)) {
				String[] split = cartonSize.split("/");
				outter:
				for (String cs : split) {
					if (!CartonUtil.validate(cs)) {
						for(String reg : filterSet){
							if (cs.contains(reg)) {
								continue outter;
							}
						}
						//System.err.println(++errCount + "-->" + buf.replace("###", "-->"));
					} else {
						dataList.add(Arrays.asList(datas[0], datas[1], cs,
								CartonUtil.calcCartonSize(cs) + ""));
					}
				}

			} else {
				dataList.add(Arrays.asList(datas[0], datas[1], cartonSize,
						CartonUtil.calcCartonSize(cartonSize) + ""));
			}

		}
		int c = 0;
		
		File outputFile = new File("C:\\Users\\admin\\Desktop\\insertCarton.txt");
		if(outputFile.exists()){
			outputFile.delete();
		}
		outputFile.createNewFile();
		
		FileWriter fw = new FileWriter(outputFile);
		for (List<String> list : dataList) {
			if(list.get(2).equals("1*1") || list.get(3).equals("0")){
				continue;
			}
			 String line = String.format("INSERT INTO `gd_carton` (`gd_code`, `gd_name`, `carton_size`, `single_num`,  `remark`, `creater_user_name`, `create_time`) VALUES (%s, '%s', '%s', %s,  '初始数据系统自动创建', 'administrator', now());",
			 list.get(0),list.get(1),list.get(2), list.get(3));
			//System.out.println(line);
			 
			 fw.write(line);
			 fw.write("\r\n");
		}
		fw.close();
	}
}
