package com.cs.test;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartonUtil {

	private final static Pattern p_num = Pattern.compile("\\d+");
	public static boolean validate(String cartonSize){
		if(StringUtils.isBlank(cartonSize) || !Pattern.matches("(\\d+\\*)*\\d+", cartonSize)){
			return false;
		}
		return true;
	}
	
	
	public static int calcCartonSize(String cartonSize){
		if(!validate(cartonSize)){
			return 0;
		}
		
		Matcher m = p_num.matcher(cartonSize); 
		int num = 1;
		while(m.find()){
			num *= NumberUtils.toInt(m.group());
		}
		return num;
	}
}
