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
import yz.bean.VerifyRegister;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -8883981414466574880L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//查看验证码是否正确
		String stdVerify = (String)request.getSession().getAttribute("rand");
		String cmpVerity = request.getParameter("rand");
		
		//若不正确
		if(!stdVerify.equals(cmpVerity)){
			out.println(ShowInfo.showError("../register.jsp", "验证码错误"));
			return;
		}
		
		//接收信息
		VerifyRegister vr = new VerifyRegister();
		vr.setNickName(request.getParameter("name"));
		vr.setPassword(request.getParameter("pass"));
		vr.setRpassword(request.getParameter("rpass"));
		vr.setEmail(request.getParameter("email"));
		
		//若注册信息不合法
		if(vr.isValid() ==  false){
			//输出错误信息 并结束处理进程
			out.println(ShowInfo.showError("../register.jsp", vr.error));
			return;
		}
		
		//注册信息通过验证 准备写入数据库
		try{
		MySqlConnection msc = new MySqlConnection();
		String error = msc.executeUpdate("insert into 12058_users (`NickName`,`Pass`,`Email`,`Authority`) values" + 
		"('"+vr.getNickName() +"','" + Encrypt.md5EncryptTo32Bits(vr.getPassword()) +"','" + vr.getEmail() +"','0');");
		
		//数据库写入失败
		if(!error.equals("success")){
			out.println(ShowInfo.showError("../register.jsp",error));
			return;
		}
		
		//写入成功
		out.println(ShowInfo.showInfo("../index.jsp","注册成功", "点击登录"));
		return;
		
		}
		catch(Exception e){
			//未知错误
			out.println(ShowInfo.showError("../index.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
