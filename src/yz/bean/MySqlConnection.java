package yz.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {

	private String userName;
	private String passWord;
	private String dbUrl;
	
	//�������ӵ����ݿ�
	private Connection con;
	
	//���ڲ�ѯ�����
	private Statement sql;
	
	//������Ϣ
	public String conError;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getSql() {
		return sql;
	}

	public void setSql(Statement sql) {
		this.sql = sql;
	}
	
	//���캯��
	public MySqlConnection(){
		
		//�û���
		userName = "root";
		
		//����Ϊ��
		passWord = "";
		
		//�������ݿ�ʱ��utf-8��ʽ�������ֱ���
		dbUrl = "jdbc:mysql://127.0.0.1/yzdbforjsp?useUnicode=true&characterEncoding=utf-8";
	}
	
	
	//ִ�и��²���
	//���ظ���״̬
	public String executeUpdate(String param) {
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, userName, passWord);
			sql=con.createStatement();
	        sql.executeUpdate(param);
	        con.close();
	        return "success";
	   }catch(Exception e) {
		   conError = e.getMessage();
		   return conError;
	   }    
	}
	
	//ִ�и��²���
	//���ظ���״̬
	public String executeUpdateWithoutClose(String param) {
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, userName, passWord);
			sql=con.createStatement();
	        sql.executeUpdate(param);
	        return "success";
	   }catch(Exception e) {
		   conError = e.getMessage();
		   return conError;
	   }    
	}
	
	//ִ�в�ѯ���� ���ز�ѯ���
	public ResultSet executeQuery(String param) {
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, userName, passWord);
			sql=con.createStatement();
	        ResultSet rs = sql.executeQuery(param);
	        return rs;
	   }catch(Exception e) {
		   conError = e.getMessage();
		   return null;
	   }    
	}
	
	
	//ִ�в�ѯ����
	//���ؽ���Ƿ����
	public boolean Exists(String param){
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, userName, passWord);
			sql=con.createStatement();
	        ResultSet rs =  sql.executeQuery(param);
	        boolean b = false;
	        if(rs.next()){
	        	b = true;
	        }
	        con.close();
	        return b;
	   }catch(Exception e) {
		   conError = e.toString();
	   }
	    return false;
	}
	
	
	//�ر����ݿ����ӵĽӿ�
	//�����ڴ�й©
	public void close() throws SQLException {
		con.close();
	}
	
}
