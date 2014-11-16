package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;

public class AddFriendsServlet extends HttpServlet {

	private static final long serialVersionUID = -7709014596134875002L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String selectedUser = request.getParameter("select");
		//若未提交花添加的好友
		if(selectedUser == null){
			//转回添加好友页面
			response.sendRedirect("../friends.jsp");
			return;
		}
		String currentUser = (String)request.getSession().getAttribute("user");
		//若用未登录
		if(currentUser == null){
			//跳转
			response.sendRedirect("../index.jsp");
			return;
		}
		try{
			
		//准备链接到数据库
		MySqlConnection msc = new MySqlConnection();
		
		//先检查是否已申请
		boolean b = msc.Exists("select * from `12058_info_f` where `Sender`='"
				+ currentUser + "' and `Receiver`='"
				+ selectedUser + "'");
		
		//若已传送
		if(b == true){
			//返回上一页
			out.println("<script language=javascript>alert('好友请求已传送');history.back(-1);</script>");
			return;
		}
		
		//若未传送
		//将请求写入数据库
		msc.executeUpdate("insert into `12058_info_f` values('" + currentUser + "','" + selectedUser + "')");
		
		//返回上一页
		out.println("<script language=javascript>alert('好友请求已传送');history.back(-1);</script>");
		
		}catch(Exception e){
			out.println(e.getMessage());
		}	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
