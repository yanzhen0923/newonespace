package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;

public class DeleteFriendsServlet extends HttpServlet {

	private static final long serialVersionUID = -8332650899695436781L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String selectedUser = request.getParameter("select");
		//��Ϊ�ύҪɾ���ĺ���
		if(selectedUser == null){
			response.sendRedirect("../friends.jsp");
			return;
		}
		String currentUser = (String)request.getSession().getAttribute("user");
		//��δ��¼
		if(currentUser == null){
			//���ص�¼ҳ
			response.sendRedirect("../index.jsp");
			return;
		}
		try{
			//׼�����ӵ����ݿ�
		MySqlConnection msc = new MySqlConnection();
		//ɾ�������� �ĺ��ѹ�ϵ
		msc.executeUpdate("delete from `12058_friends` where `Left`='" 
				+ currentUser + "' and `Right`='" 
				+ selectedUser + "'");
		//ɾ�����ҵ���ĺ��ѹ�ϵ
		msc.executeUpdate("delete from `12058_friends` where `Left`='" 
				+ selectedUser + "' and `Right`='" 
				+ currentUser + "'");
		//���غ���ҳ��
		out.println("<script language=javascript>alert(\"ɾ���ɹ�\");window.location='../friends.jsp';</script>");
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
