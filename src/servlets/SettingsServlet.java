package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;
import yz.bean.VerifySettings;

public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 347784224898301076L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//设置字符编码
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			
			String user = (String)request.getSession().getAttribute("user");
			//若未登录
			if(user == null){
				//返回首页
				response.sendRedirect("../index.jsp");
				return;
			}
			
			//传递参数
			VerifySettings vs = new VerifySettings();
			vs.setRealName(request.getParameter("realname"));
			vs.setDorm(request.getParameter("dormitory"));
			
			//检查提交的参数是否合法
			if(!vs.isValid()){
				//若不合法
				response.sendRedirect("../modify.jsp");
				return;
			}
			
			//拼凑出构成生日的字符串
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String birth = year + "-" + month + "-" + day;
			
			//通过了验证
			try{
				
				MySqlConnection msc = new MySqlConnection();
				
				//执行更新操作
				String q = "update `12058_users` set `RealName`='"
						+ vs.getRealName() + "',`Dorm`='"
						+ vs.getDorm() + "',`Birthday`='"
						+ birth +"' where `NickName`='"
						+ user + "'";
				msc.executeUpdate(q);
				
				//返回设置页面
				PrintWriter out = response.getWriter();
				out.println("<script language=javascript>alert('修改成功');window.location='../modify.jsp';</script>");
			}
			catch(Exception e){
				
			}
			
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
