package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.Encrypt;
import yz.bean.MySqlConnection;
import yz.bean.ShowInfo;
import yz.bean.VerifyLogin;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4261016544681578021L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//查看验证码是否正确
		String stdVerify = (String)request.getSession().getAttribute("rand");
		String cmpVerity = request.getParameter("rand");
		
		//若不正确
		if(!stdVerify.equals(cmpVerity)){
			out.println(ShowInfo.showError("../index.jsp", "验证码错误"));
			return;
		}
		
		//接收信息
		VerifyLogin vl = new VerifyLogin();
		vl.setNickName(request.getParameter("name"));
		vl.setPassword(request.getParameter("pass"));
		
		//若输入为空
		if(!vl.isValid()){
			out.println(ShowInfo.showError("../index.jsp",vl.error));
			return;
		}
		
		//信息合法 写入数据库
		try{
			MySqlConnection msc = new MySqlConnection();
			String q = "select * from 12058_users where `NickName`='"
						+ vl.getNickName() + "' and `Pass`='"
						+ Encrypt.md5EncryptTo32Bits(vl.getPassword()) +"'";
			//账户不存在
			if(!msc.Exists(q)){
				out.println(ShowInfo.showError("../index.jsp","账户不存在"));
				return;
			}
			
			//设置session值
			request.getSession().setAttribute("user", vl.getNickName());
			
			//账户存在
			response.sendRedirect("../myspace.jsp");
		}
		catch(Exception e){
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
