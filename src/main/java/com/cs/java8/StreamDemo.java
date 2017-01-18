package com.cs.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamDemo {
public static void main(String[] args) {
	
	
		List<String> list = Arrays.asList("str1", "str2", "str3");
		list.stream().findFirst().ifPresent(System.out::println);
		
		
		IntStream is = IntStream.builder().add(3).add(1).add(2).build();
		
		//is.parallel().forEach(System.out::println);
		//is.parallel().forEachOrdered(System.out::println);

}
}
