package yz.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyRegister {

	private String nickName;
	
	//密码
	private String password;
	
	//重复密码
	private String Rpassword;

	private String email;
	
	//错误消息
	public String error = null;
	
	public void setNickName(String nickName) {
		this.nickName = nickName.trim();
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public void setRpassword(String Rpassword) {
		this.Rpassword = Rpassword.trim();
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public String getNickName() {
		return nickName;
	}
	public String getPassword() {
		return password;
	}
	public String getRpassword() {
		return Rpassword;
	}
	public String getEmail() {
		return email;
	}
	
	
	//用户验证电子邮件地址是否合法
	private boolean verifyEmail(String email) {
		
		// 用来验证的正则表达
		//用于匹配    n个非"@"字符  + "@"字符   + n个非"@"字符 + "."字符 " + n个非"@"字符
		String regex = "[^@]+@[^@]+\\.[^@]+";
		
		//正则表达式对象
		Pattern p = Pattern.compile(regex);
		
		//匹配
		Matcher m = p.matcher(email);
		
		//看是否有匹配结果
		return m.find();
	}
	
	public boolean isValid(){
		
		//名字不能为空 
		//长度不能超过150
		if(nickName == "" || nickName.length() > 150){
			error = "非法用户名";
			return false;
		}
		
		//密码不能为空
		if (password == "" || Rpassword == ""){
			error = "密码不能为空";
			return false;
		}
		
		
		//两次密码应该一致
		if(password.equals(Rpassword) == false){
			error = "两次密码不一致";
			return false;
		}
		
		//检查电子邮件地址合法性
		if(verifyEmail(email) ==  false){
			error = "电子邮件地址不合法";
			return false;
		}
		
		
		//通过验证  返回合法信息
		return true;
	}
	
}
