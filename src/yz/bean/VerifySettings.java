package yz.bean;

public class VerifySettings {
	
	//��ʵ����
	private String realName;
	
	//�����
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
		
		//��ʵ��������Ϊ��
		if(realName == null)
			return false;
		
		//���Ȳ���Ϊ0
		if(realName.length() == 0)
			return false;
		
		//���᲻��Ϊ��
		if(dorm == null)
			return false;
		
		//���Ȳ���Ϊ0
		if(dorm.length() == 0)
			return false;
		
		//ͨ����֤  ���غϷ�
		return true;
	}
	
}
