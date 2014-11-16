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

			//�����ַ�����
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			
			String user = (String)request.getSession().getAttribute("user");
			//��δ��¼
			if(user == null){
				//������ҳ
				response.sendRedirect("../index.jsp");
				return;
			}
			
			//���ݲ���
			VerifySettings vs = new VerifySettings();
			vs.setRealName(request.getParameter("realname"));
			vs.setDorm(request.getParameter("dormitory"));
			
			//����ύ�Ĳ����Ƿ�Ϸ�
			if(!vs.isValid()){
				//�����Ϸ�
				response.sendRedirect("../modify.jsp");
				return;
			}
			
			//ƴ�ճ��������յ��ַ���
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String birth = year + "-" + month + "-" + day;
			
			//ͨ������֤
			try{
				
				MySqlConnection msc = new MySqlConnection();
				
				//ִ�и��²���
				String q = "update `12058_users` set `RealName`='"
						+ vs.getRealName() + "',`Dorm`='"
						+ vs.getDorm() + "',`Birthday`='"
						+ birth +"' where `NickName`='"
						+ user + "'";
				msc.executeUpdate(q);
				
				//��������ҳ��
				PrintWriter out = response.getWriter();
				out.println("<script language=javascript>alert('�޸ĳɹ�');window.location='../modify.jsp';</script>");
			}
			catch(Exception e){
				
			}
			
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
