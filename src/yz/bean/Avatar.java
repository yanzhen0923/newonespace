package yz.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Avatar {
	
	public Map<String,String> map;
	
	public void Refresh() throws SQLException{
		
		//���캯����ִ�в�ѯ
		map = new HashMap<String,String>();
		MySqlConnection msc= new MySqlConnection();
		ResultSet rs = msc.executeQuery("select * from 12058_users");
		
		//�����û�����ͷ��Ķ�Ӧ��ϵ
		while (rs.next()){ 
			map.put(rs.getString(1), rs.getString(8));
		}
		msc.close();
	}
}
