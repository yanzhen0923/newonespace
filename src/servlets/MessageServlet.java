package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;
import yz.bean.MyTime;
import yz.bean.ShowInfo;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1786047360514225475L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//若用户未登录
		if(user == null){
			//返回首页
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String targetUser = request.getParameter("target");
		//若留言对象为空
		if(targetUser == null){
			//返回留言页
			response.sendRedirect("../message.jsp");
			return;
		}
		
		String targetMessage = request.getParameter("mymessage");
		//若留言为空
		if(targetMessage.length() == 0){
			//返回留言页
			out.println(ShowInfo.showError("../message.jsp", "消息不能为空"));
			return;
		}
		
		try{
			
			MySqlConnection msc = new MySqlConnection();
			//插入在留言板中插入留言
			String q = "insert into 12058_msg (`Sender`,`Time`,`Content`,`Receiver`) values ('"
					+ user + "','"
					+ MyTime.getTime() + "','"
					+ targetMessage + "','"
					+ targetUser + "')";
			msc.executeUpdate(q);
			
			//若在留言区中已有通知
			boolean b = msc.Exists("select * from `12058_info_m` where `Sender`='"
					+ user + "' and `Receiver`='"
					+ targetUser + ";" );
			if(b == true){
				//插入留言后直接返回
				response.sendRedirect("../message.jsp");
				return;
			}
			
			//若还没有通知在留言通知表中插入通知
			String p = "insert into `12058_info_m` values('"
					+ user + "','"
					+ targetUser + "')" ;
			msc.executeUpdate(p);
			
			//返回留言页
			response.sendRedirect("../message.jsp");
		}
		catch(Exception e){
			out.println(ShowInfo.showError("../message.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}