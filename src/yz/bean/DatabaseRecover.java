package yz.bean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseRecover {

	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public ArrayList<String> GetSQLCmds() {
		try{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		ArrayList<String> ar = new ArrayList<String>();
		String line = "",lines = "";
		while ((line = br.readLine()) != null) {
			if(!line.equals("") && !line.startsWith("--"))
				lines += line;
		}
		
		ar = new ArrayList<String>(Arrays.asList(lines.split(";")));
		br.close();
		return ar;
		
	}catch(Exception e){
		
		return null;
	}
		
	}
}
