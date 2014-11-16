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
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//�鿴��֤���Ƿ���ȷ
		String stdVerify = (String)request.getSession().getAttribute("rand");
		String cmpVerity = request.getParameter("rand");
		
		//������ȷ
		if(!stdVerify.equals(cmpVerity)){
			out.println(ShowInfo.showError("../index.jsp", "��֤�����"));
			return;
		}
		
		//������Ϣ
		VerifyLogin vl = new VerifyLogin();
		vl.setNickName(request.getParameter("name"));
		vl.setPassword(request.getParameter("pass"));
		
		//������Ϊ��
		if(!vl.isValid()){
			out.println(ShowInfo.showError("../index.jsp",vl.error));
			return;
		}
		
		//��Ϣ�Ϸ� д�����ݿ�
		try{
			MySqlConnection msc = new MySqlConnection();
			String q = "select * from 12058_users where `NickName`='"
						+ vl.getNickName() + "' and `Pass`='"
						+ Encrypt.md5EncryptTo32Bits(vl.getPassword()) +"'";
			//�˻�������
			if(!msc.Exists(q)){
				out.println(ShowInfo.showError("../index.jsp","�˻�������"));
				return;
			}
			
			//����sessionֵ
			request.getSession().setAttribute("user", vl.getNickName());
			
			//�˻�����
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
