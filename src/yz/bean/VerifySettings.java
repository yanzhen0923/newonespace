package yz.bean;

public class VerifySettings {
	
	//真实姓名
	private String realName;
	
	//宿舍号
	private String dorm;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDorm() {
		return dorm;
	}
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	
	public boolean isValid() {
		
		//真实姓名不能为空
		if(realName == null)
			return false;
		
		//长度不能为0
		if(realName.length() == 0)
			return false;
		
		//宿舍不能为空
		if(dorm == null)
			return false;
		
		//长度不能为0
		if(dorm.length() == 0)
			return false;
		
		//通过验证  返回合法
		return true;
	}
	
}
