package yz.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyRegister {

	private String nickName;
	
	//����
	private String password;
	
	//�ظ�����
	private String Rpassword;

	private String email;
	
	//������Ϣ
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
	
	
	//�û���֤�����ʼ���ַ�Ƿ�Ϸ�
	private boolean verifyEmail(String email) {
		
		// ������֤��������
		//����ƥ��    n����"@"�ַ�  + "@"�ַ�   + n����"@"�ַ� + "."�ַ� " + n����"@"�ַ�
		String regex = "[^@]+@[^@]+\\.[^@]+";
		
		//������ʽ����
		Pattern p = Pattern.compile(regex);
		
		//ƥ��
		Matcher m = p.matcher(email);
		
		//���Ƿ���ƥ����
		return m.find();
	}
	
	public boolean isValid(){
		
		//���ֲ���Ϊ�� 
		//���Ȳ��ܳ���150
		if(nickName == "" || nickName.length() > 150){
			error = "�Ƿ��û���";
			return false;
		}
		
		//���벻��Ϊ��
		if (password == "" || Rpassword == ""){
			error = "���벻��Ϊ��";
			return false;
		}
		
		
		//��������Ӧ��һ��
		if(password.equals(Rpassword) == false){
			error = "�������벻һ��";
			return false;
		}
		
		//�������ʼ���ַ�Ϸ���
		if(verifyEmail(email) ==  false){
			error = "�����ʼ���ַ���Ϸ�";
			return false;
		}
		
		
		//ͨ����֤  ���غϷ���Ϣ
		return true;
	}
	
}
