package yz.bean;

public class ShowInfo {
	
	//���ش�����Ϣ
	public static String showError(String redirect,String message) {
			
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>��һ�ռ�</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">����һ��</a></div>"
				+ "<div class=\"bgcenter\">��һ�ռ� &copy; ��Ȩ����@2014</div></body></html>";
		return  msg;
			
		}
	
	
	//����ժҪ��Ϣ
	public static String showInfo(String redirect,String message,String outline) {
		
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>��һ�ռ�</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">" + outline +"</a></div>"
				+ "<div class=\"bgcenter\">��һ�ռ� &copy; ��Ȩ����@2014</div></body></html>";
		return  msg;
			
		}
	
	public static String showInstalledInfo(String redirect,String message,String outline) {
		
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>��һ�ռ�</title><link rel=\"stylesheet\" type=\"text/css\" href=\"class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">" + outline +"</a></div>"
				+ "<div class=\"bgcenter\">��һ�ռ� &copy; ��Ȩ����@2014</div></body></html>";
		return  msg;
			
		}
	
	//���ڷ��ز��������ӵ�������ҳ��html����
	public String showLink(String param) {
		return "<a href=\"browse.jsp?id=" + param + "\">" + param + "</a>";
		
	}


}
