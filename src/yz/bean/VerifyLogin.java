package yz.bean;

public class VerifyLogin {

	private String nickName;
	private String password;
	
	//������Ϣ
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
		
		//�û�������Ϊ�� 
		//�û������Ȳ��ܳ���150
		if(nickName == "" || nickName.length() > 150){
			error = "�Ƿ��û���";
			return false;
		}
		
		//���벻��Ϊ��
		if (password == ""){
			error = "���벻��Ϊ��";
			return false;
		}
		
		return true;
	}
}
