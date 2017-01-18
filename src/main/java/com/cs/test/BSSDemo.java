package com.cs.test;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class BSSDemo {

	
	public static void main(String[] args) throws Exception {
		


		File file = new File("d://goods_category");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));

		String buf = "";
		
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		while ((buf = br.readLine()) != null) {

			String[] ss = buf.split("\\t");
			
			//System.out.println(StringUtils.join(ss, ","));
			String categoryId = ss[1];
			String goodsId = ss[0];
			if(!map.containsKey(categoryId)){
				map.put(categoryId, new LinkedHashSet<String>());
			}
			map.get(categoryId).add(goodsId);
		}
		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			System.out.println(String.format("update goods_info gi set gi.category_id=%s, gi.update_time=gi.update_time where gi.id in (%s);", entry.getKey(), StringUtils.join(entry.getValue(), ",")));
		}
		
	}
}
