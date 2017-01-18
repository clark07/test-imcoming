package com.cs.test;


import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BssCategoryDemo {
	public static void main(String[] args) throws Exception {


		File file = new File("C:\\Users\\admin\\Desktop\\category.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "GBK"));

		String buf = "";
		int count = 0;

		List<Category> categoryList = new ArrayList<Category>();
		while ((buf = br.readLine()) != null) {
			if (count++ == 0)
				continue;

			String[] ss = buf.split("\\s+");
			int discount = NumberUtils.toInt(ss.length==4?ss[2].split("%")[0]:"", 15);
			categoryList.add(new Category(ss[0], ss[1], ss[ss.length-1], discount));
			//System.out.println(StringUtils.join(ss, ","));
		}
		
		Map<String, Category> c1Map = new LinkedHashMap<String, Category>();
		Map<String, Category> c2Map = new LinkedHashMap<String, Category>();
		Map<String, Category> c3Map = new LinkedHashMap<String, Category>();
		for (Category category : categoryList) {
			//System.out.println(category);
			c1Map.put(category.getC1(), category);
			c2Map.put(String.format("%s-%s", category.getC1(), category.getC2()), category);
			c3Map.put(String.format("%s-%s-%s", category.getC1(), category.getC2(),  category.getC3()), category);
		}
		int sort = 0;
		for (Category c : c1Map.values()) {
			System.out.println(String.format("INSERT INTO `category` (`name`, `parent_id`,  `sort`, `depth`, `create_time`, `discount_percent`)  VALUES ('%s', %s, %s, %s, %s, %s);",
					c.getC1(), 0, ++sort, 1, "now()", 15));
		}
		System.out.println(String.format("update category set category_id_path=id where depth=1;"));
		sort = 0;
		for (Category c : c2Map.values()) {
			System.out.println(String.format("INSERT INTO `category` (`name`,  `sort`, `depth`, `create_time`, `discount_percent`)" + " VALUES ('%s', %s, %s, %s, '%s');", 
					c.getC2(), ++sort, 2, "now()", c.getDiscount()));
			System.out.println(String.format("update  category c2, category c1 set c2.parent_id=c1.id "
					+ "where c2.name='%s' and c2.depth=2 and c2.sort=%s and c1.name='%s' and c1.depth=1;", c.getC2(), sort, c.getC1()));
		}
		System.out.println(String.format("update category set category_id_path=concat(parent_id, ',' , id) where depth=2;"));
		sort = 0;
		for (Category c : c3Map.values()) {
			System.out.println(String.format("INSERT INTO `category` (`name`,  `sort`, `depth`, `create_time`, `discount_percent`)" + " VALUES ('%s', %s, %s, %s, '%s');", 
					c.getC3(), ++sort, 3, "now()", c.getDiscount()));
			System.out.println(String.format("update  category c3, category c2 set c3.parent_id=c2.id "
					+ "where c3.name='%s' and c3.depth=3 and c3.sort=%s and c2.name='%s' and c2.depth=2;", c.getC3(), sort, c.getC2()));
		}
		System.out.println(String.format("update category c3, category c2 set c3.category_id_path=concat(c2.category_id_path, ',', c3.id) where c3.parent_id=c2.id and c3.depth=3;"));
	}

	static class Category {
		private String c1;
		private String c2;
		private String c3;
		private int discount;
		
		

		public Category(String c1, String c2, String c3, int discount) {
			super();
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.discount = discount;
		}

		public String getC1() {
			return c1;
		}

		public void setC1(String c1) {
			this.c1 = c1;
		}

		public String getC2() {
			return c2;
		}

		public void setC2(String c2) {
			this.c2 = c2;
		}

		public String getC3() {
			return c3;
		}

		public void setC3(String c3) {
			this.c3 = c3;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		@Override
		public String toString() {
			return "Category [c1=" + c1 + ", c2=" + c2 + ", c3=" + c3
					+ ", discount=" + discount + "]";
		}

		
	}

}
