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
	
	//用于连接到数据库
	private Connection con;
	
	//用于查询的语句
	private Statement sql;
	
	//错误信息
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
	
	//构造函数
	public MySqlConnection(){
		
		//用户名
		userName = "root";
		
		//密码为空
		passWord = "";
		
		//连接数据库时以utf-8方式进行文字编码
		dbUrl = "jdbc:mysql://127.0.0.1/yzdbforjsp?useUnicode=true&characterEncoding=utf-8";
	}
	
	
	//执行更新操作
	//返回更新状态
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
	
	//执行更新操作
	//返回更新状态
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
	
	//执行查询操作 返回查询结果
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
	
	
	//执行查询操作
	//返回结果是否存在
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
	
	
	//关闭数据库连接的接口
	//避免内存泄漏
	public void close() throws SQLException {
		con.close();
	}
	
}
