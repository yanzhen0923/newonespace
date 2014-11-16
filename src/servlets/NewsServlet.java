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

public class NewsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 343941966872119685L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//若未登录则返回首页
		if(user == null){
			response.sendRedirect("../index.jsp");
			return;
		}
		
		//若消息为空
		if(request.getParameter("mynews").length() == 0){
			//要求返回
			out.println(ShowInfo.showError("../myspace.jsp", "消息不能为空"));
			return;
		}
		
		
		//通过验证
		try{
			MySqlConnection msc = new MySqlConnection();
			//将新闻插入到数据库
			String q = "insert into 12058_news (`NickName`,`Time`,`Content`,`Authority`) values ('" 
					+ user + "','"
					+ MyTime.getTime() + "','"
					+ request.getParameter("mynews") + "',"
					+ request.getParameter("priv") + ")";
			msc.executeUpdate(q);
			//返回
			response.sendRedirect("../myspace.jsp");
		}
		catch(Exception e){
			out.println(ShowInfo.showError("../myspace.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
