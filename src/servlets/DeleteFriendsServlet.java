package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;

public class DeleteFriendsServlet extends HttpServlet {

	private static final long serialVersionUID = -8332650899695436781L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String selectedUser = request.getParameter("select");
		//若为提交要删除的好友
		if(selectedUser == null){
			response.sendRedirect("../friends.jsp");
			return;
		}
		String currentUser = (String)request.getSession().getAttribute("user");
		//若未登录
		if(currentUser == null){
			//返回登录页
			response.sendRedirect("../index.jsp");
			return;
		}
		try{
			//准备连接到数据库
		MySqlConnection msc = new MySqlConnection();
		//删除从左到右 的好友关系
		msc.executeUpdate("delete from `12058_friends` where `Left`='" 
				+ currentUser + "' and `Right`='" 
				+ selectedUser + "'");
		//删除从右到左的好友关系
		msc.executeUpdate("delete from `12058_friends` where `Left`='" 
				+ selectedUser + "' and `Right`='" 
				+ currentUser + "'");
		//返回好友页面
		out.println("<script language=javascript>alert(\"删除成功\");window.location='../friends.jsp';</script>");
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
