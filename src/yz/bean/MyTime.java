package yz.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTime {
	public static String getTime() {
		
		//��ȡ��ǰʱ��
		Date currDate = Calendar.getInstance().getTime();
		
		//�Թ̶���ʽ�����ַ�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//����
		return sdf.format(currDate);

	}
}
