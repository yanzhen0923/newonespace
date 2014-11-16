package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;

public class ProcessAddFriendsServlet extends HttpServlet {

	private static final long serialVersionUID = 4505110502394173502L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MySqlConnection msc = new MySqlConnection();
		
		String currentUser = (String)request.getSession().getAttribute("user");
		//若用未登录
		if(currentUser == null){
			//跳转
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String option = request.getParameter("option");
		//若未提交选项
		if(option == null){
			//转回上一层
			out.println("<script language=javascript>history.back(-1);</script>");
			return;
		}
		
		String target = request.getParameter("target");
		//若未提交要添加的好友
		if(target == null){
			//转回上一层
			out.println("<script language=javascript>history.back(-1);</script>");
			return;
		}
		
		//通过了验证开始处理请求
		
		//不论接受还是拒绝 先解除通知
		msc.executeUpdate("delete from `12058_info_f` where `Sender`='"
				+ target + "' and `Receiver`='"
				+ currentUser + "'" );
		
		//若果提交的是拒绝的请求
		if(option.equals("no")){
			out.println("<script language=javascript>alert(\"成功解除通知\");history.back(-1);</script>");
			return;
		}
		
		//若提交的是接受的请求
		else if(option.equals("yes")){
			//将好友信息写入数据库
			
			msc.executeUpdate("insert into `12058_friends` values('"
					+ currentUser +"','"
					+ target +"')" );
			//反过来写一次
			msc.executeUpdate("insert into `12058_friends` values('"
					+ target +"','"
					+ currentUser +"')" );
			
			out.println("<script language=javascript>alert(\"成功添加好友\");window.location='../friends.jsp';</script>");	
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
