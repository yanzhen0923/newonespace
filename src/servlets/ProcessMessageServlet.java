package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;

public class ProcessMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 2474556079490235139L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String currentUser = (String)request.getSession().getAttribute("user");
		//若用未登录
		if(currentUser == null){
			//跳转
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String targetUser = request.getParameter("target");
		//若未提交解除通知的对象
		if(targetUser == null){
			//转回添加好友页面
			response.sendRedirect("../message.jsp");
			return;
		}
		
		//通过验证
		
		try{
		
		//准备连接到数据库
		MySqlConnection msc = new MySqlConnection();
		
		//从数据库里删除通知
		msc.executeUpdate("delete from `12058_info_m` where `Sender`='"
				+ targetUser + "' and `Receiver`='"
				+ currentUser + "'" );
		
		//返回
		response.sendRedirect("../message.jsp");
		
		}
		catch(Exception e){
			out.println(e.getMessage());
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
