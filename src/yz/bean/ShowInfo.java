package yz.bean;

public class ShowInfo {
	
	//返回错误信息
	public static String showError(String redirect,String message) {
			
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>新一空间</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">再来一次</a></div>"
				+ "<div class=\"bgcenter\">新一空间 &copy; 版权所有@2014</div></body></html>";
		return  msg;
			
		}
	
	
	//返回摘要信息
	public static String showInfo(String redirect,String message,String outline) {
		
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>新一空间</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">" + outline +"</a></div>"
				+ "<div class=\"bgcenter\">新一空间 &copy; 版权所有@2014</div></body></html>";
		return  msg;
			
		}
	
	public static String showInstalledInfo(String redirect,String message,String outline) {
		
		String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/><title>新一空间</title><link rel=\"stylesheet\" type=\"text/css\" href=\"class.css\" /></head><body>"
				+ "<div class=\"center\"><lable >"
				+ message
				+ "</lable><br><br><a href="+ redirect +">" + outline +"</a></div>"
				+ "<div class=\"bgcenter\">新一空间 &copy; 版权所有@2014</div></body></html>";
		return  msg;
			
		}
	
	//用于返回产生超链接到个人主页的html代码
	public String showLink(String param) {
		return "<a href=\"browse.jsp?id=" + param + "\">" + param + "</a>";
		
	}


}
