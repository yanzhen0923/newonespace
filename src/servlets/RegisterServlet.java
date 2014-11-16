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
		
		//�鿴��֤���Ƿ���ȷ
		String stdVerify = (String)request.getSession().getAttribute("rand");
		String cmpVerity = request.getParameter("rand");
		
		//������ȷ
		if(!stdVerify.equals(cmpVerity)){
			out.println(ShowInfo.showError("../register.jsp", "��֤�����"));
			return;
		}
		
		//������Ϣ
		VerifyRegister vr = new VerifyRegister();
		vr.setNickName(request.getParameter("name"));
		vr.setPassword(request.getParameter("pass"));
		vr.setRpassword(request.getParameter("rpass"));
		vr.setEmail(request.getParameter("email"));
		
		//��ע����Ϣ���Ϸ�
		if(vr.isValid() ==  false){
			//���������Ϣ �������������
			out.println(ShowInfo.showError("../register.jsp", vr.error));
			return;
		}
		
		//ע����Ϣͨ����֤ ׼��д�����ݿ�
		try{
		MySqlConnection msc = new MySqlConnection();
		String error = msc.executeUpdate("insert into 12058_users (`NickName`,`Pass`,`Email`,`Authority`) values" + 
		"('"+vr.getNickName() +"','" + Encrypt.md5EncryptTo32Bits(vr.getPassword()) +"','" + vr.getEmail() +"','0');");
		
		//���ݿ�д��ʧ��
		if(!error.equals("success")){
			out.println(ShowInfo.showError("../register.jsp",error));
			return;
		}
		
		//д��ɹ�
		out.println(ShowInfo.showInfo("../index.jsp","ע��ɹ�", "�����¼"));
		return;
		
		}
		catch(Exception e){
			//δ֪����
			out.println(ShowInfo.showError("../index.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
