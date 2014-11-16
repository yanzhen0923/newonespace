package yz.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifySearch {
	
 	public String regex;
	
	private String date1;
	
	private String date2;
	
	private  String param;
	
	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	//用户验证电子邮件地址是否合法
	private boolean verifyDate(String date) {
		
		// 用来验证的正则表达
		//用于匹配    n个非"@"字符  + "@"字符   + n个非"@"字符 + "."字符 " + n个非"@"字符
		regex = "^\\d{4}-\\d{2}-\\d{2}$";
		
		//正则表达式对象
		Pattern p = Pattern.compile(regex);
		
		//匹配
		Matcher m = p.matcher(date);
		
		//看是否有匹配结果
		return m.find();
	}
	
	public boolean isFriendsValid() {
		
		if(param == null){
			return false;
		}
		
		if(param.length() > 150 || param.length() == 0){
			return false;
		}
		
		if(condition == null){
			return false;
		}
			
		return true;
	}
	
	public boolean isStrictNewsValid() {
		
		if(param == null){
			return false;
		}
		
		if(param.length() > 150 || param.length() == 0){
			return false;
		}
		
		if(date1 == null || date2 == null){
			return false;
		}
		
		if(!(verifyDate(date1) && verifyDate(date2))){
			return false;
		}
		
		return true;
	}
	
	
	public boolean isAnyNewsValid() {
		
		if(param == null){
			return false;
		}
		
		if(param.length() > 150 || param.length() == 0){
			return false;
		}
				
		return true;
	}
	
}
