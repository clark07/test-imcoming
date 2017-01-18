package com.cs.test;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaleDemo {

	public static void main(String[] args) throws Exception {
		
		String[] lines = new String[]{
				"15044291033,1050044291301	9.15-11.2	苏州,无锡"

} ;
		for (String line : lines) {
			//String line = "	15057481045	5.1-5.31	全国";
			Pattern p = Pattern.compile("\\d{8,}");
			Matcher m = p.matcher(line);
			List<String> codeList = new ArrayList<String>();
			while(m.find()){
				codeList.add(m.group());
			}
			
			
			String[] datas = line.split("\\t\\s*");
			String date = datas[datas.length-2];
			String city = datas[datas.length-1];
			
			System.out.println(String.format("#codes-->%s", StringUtils.join(codeList, ",")));
			System.out.println(String.format("#date-->%s", date));
			System.out.println(String.format("#city-->%s", city));
			
			
			String[] dates = date.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date startDate = sdf.parse("2016."+dates[0]);
			Date endDate = sdf.parse("2016."+dates[1]);
			if(startDate.getMonth()!=endDate.getMonth()){
				System.err.println("diff month");
			}
			endDate = new Date(endDate.getTime()+24L*60*60*1000);
			
			
			String yyMM = new SimpleDateFormat("yyMM").format(startDate);
			
			String cityStr = "and s.city in (%s)";
			if(StringUtils.contains(city, "全国")){
				cityStr="";
			}
			String[] citys = city.split(",|、");
			List<String> cityList = new ArrayList<String>();
			for (String string : citys) {
				if(!string.endsWith("市")){
					string += "市";
				}
				
				cityList.add(String.format("'%s'", string));
			}
			
			cityStr = String.format(cityStr, StringUtils.join(cityList, ","));
			
			System.out.println();
			String querySQL = String.format("SELECT s . city '门店所在城市' , so.order_date '订单日期' ,so . shop_code '门店代码' , s . name '门店名称' ,sog .gd_code '商品代码' , sog . gd_name '商品名' , AVG(sog . price) '平均售价', GROUP_CONCAT(DISTINCT(sog .price)) '价格变动' , SUM(sog . buy_num ) '购买总数量' , SUM(sog . gd_amount ) '商品总金额'"+
					"\r\nFROM shop_order_%s so , shop_order_gd_%s sog , shop s"+
					"\r\nWHERE so . order_id = sog . order_id"+
					"\r\nAND s . code = so. shop_code AND so . `status` > 3"+
					"\r\nAND so . order_time BETWEEN '%s' AND '%s'"+
					"\r\nAND sog . gd_code in (%s)"+
					"\r\n%s "+
					"\r\nGROUP BY s . city, so. order_date, so. shop_code , sog . gd_code \r\n ;"
					+ "\r\n#%s", yyMM, yyMM, fullSdf.format(startDate), fullSdf.format(endDate),
					StringUtils.join(codeList, ","), cityStr, String.format("%s-%s-%s销售数据", StringUtils.join(codeList, "-"), date, StringUtils.join(citys, "-"))
					);
			
			
			System.out.println(querySQL);
			System.out.println();
			
		}
		
		
		
	}

}
