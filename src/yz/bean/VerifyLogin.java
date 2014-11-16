package yz.bean;

public class VerifyLogin {

	private String nickName;
	private String password;
	
	//错误信息
	public String error;
	
	public void setNickName(String nickName) {
		this.nickName = nickName.trim();
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public String getNickName() {
		return nickName;
	}
	public String getPassword() {
		return password;
	}
	
	public boolean isValid(){
		
		//用户名不能为空 
		//用户名长度不能超过150
		if(nickName == "" || nickName.length() > 150){
			error = "非法用户名";
			return false;
		}
		
		//密码不能为空
		if (password == ""){
			error = "密码不能为空";
			return false;
		}
		
		return true;
	}
}
