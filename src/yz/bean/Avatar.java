package yz.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Avatar {
	
	public Map<String,String> map;
	
	public void Refresh() throws SQLException{
		
		//构造函数里执行查询
		map = new HashMap<String,String>();
		MySqlConnection msc= new MySqlConnection();
		ResultSet rs = msc.executeQuery("select * from 12058_users");
		
		//插入用户名与头像的对应关系
		while (rs.next()){ 
			map.put(rs.getString(1), rs.getString(8));
		}
		msc.close();
	}
}
