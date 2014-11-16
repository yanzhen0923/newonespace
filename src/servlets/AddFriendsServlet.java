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
		
		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String selectedUser = request.getParameter("select");
		//��δ�ύ����ӵĺ���
		if(selectedUser == null){
			//ת����Ӻ���ҳ��
			response.sendRedirect("../friends.jsp");
			return;
		}
		String currentUser = (String)request.getSession().getAttribute("user");
		//����δ��¼
		if(currentUser == null){
			//��ת
			response.sendRedirect("../index.jsp");
			return;
		}
		try{
			
		//׼�����ӵ����ݿ�
		MySqlConnection msc = new MySqlConnection();
		
		//�ȼ���Ƿ�������
		boolean b = msc.Exists("select * from `12058_info_f` where `Sender`='"
				+ currentUser + "' and `Receiver`='"
				+ selectedUser + "'");
		
		//���Ѵ���
		if(b == true){
			//������һҳ
			out.println("<script language=javascript>alert('���������Ѵ���');history.back(-1);</script>");
			return;
		}
		
		//��δ����
		//������д�����ݿ�
		msc.executeUpdate("insert into `12058_info_f` values('" + currentUser + "','" + selectedUser + "')");
		
		//������һҳ
		out.println("<script language=javascript>alert('���������Ѵ���');history.back(-1);</script>");
		
		}catch(Exception e){
			out.println(e.getMessage());
		}	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
