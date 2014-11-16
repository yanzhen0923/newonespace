<%@page import="yz.bean.ShowInfo"%>
<%@page import="yz.bean.MySqlConnection"%>
<%@page import="java.io.File"%>
<%@page import="yz.bean.DatabaseRecover"%>
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%


		try{
			Connection con;
			Statement sql;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "");
			sql=con.createStatement();
	        sql.executeUpdate("CREATE DATABASE IF NOT EXISTS `yzdbforjsp` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;");
	        sql.executeUpdate("USE `yzdbforjsp`;");
	        con.close();
	        }
	    catch(Exception e){
	        
	        	out.print(e.getMessage());
	        }
	
	String path = application.getRealPath("/");
	String sqlFilePath = path + "db.sql";
	
	DatabaseRecover dbrc = new DatabaseRecover();
	
	dbrc.setFilePath(sqlFilePath);
	
	 ArrayList<String> al = dbrc.GetSQLCmds();

	MySqlConnection msc = new MySqlConnection();
	for(String str : al){
		msc.executeUpdateWithoutClose(str);
	}
	msc.close();
	out.println(ShowInfo.showInstalledInfo("index.jsp", "安装成功", "点击使用"));
 %>
