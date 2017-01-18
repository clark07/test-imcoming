package com.cs.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {
public static void main(String[] args) {
	
	List<SortBean> list= new ArrayList<SortBean>();
	list.add(new SortBean(10));
	list.add(new SortBean(9));
	list.add(new SortBean(15));
	
	
	Collections.sort(list, new Comparator<SortBean>(){

		public int compare(SortBean o1, SortBean o2) {
			return o1.getValue()-o2.getValue();
		}
		
	});
	
	System.out.println(list);
	
}

static class SortBean{
	
	private int value;

	
	public SortBean(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SortBean [value=" + value + "]";
	}
	
	
}

}
