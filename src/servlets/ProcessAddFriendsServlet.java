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

		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MySqlConnection msc = new MySqlConnection();
		
		String currentUser = (String)request.getSession().getAttribute("user");
		//����δ��¼
		if(currentUser == null){
			//��ת
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String option = request.getParameter("option");
		//��δ�ύѡ��
		if(option == null){
			//ת����һ��
			out.println("<script language=javascript>history.back(-1);</script>");
			return;
		}
		
		String target = request.getParameter("target");
		//��δ�ύҪ��ӵĺ���
		if(target == null){
			//ת����һ��
			out.println("<script language=javascript>history.back(-1);</script>");
			return;
		}
		
		//ͨ������֤��ʼ��������
		
		//���۽��ܻ��Ǿܾ� �Ƚ��֪ͨ
		msc.executeUpdate("delete from `12058_info_f` where `Sender`='"
				+ target + "' and `Receiver`='"
				+ currentUser + "'" );
		
		//�����ύ���Ǿܾ�������
		if(option.equals("no")){
			out.println("<script language=javascript>alert(\"�ɹ����֪ͨ\");history.back(-1);</script>");
			return;
		}
		
		//���ύ���ǽ��ܵ�����
		else if(option.equals("yes")){
			//��������Ϣд�����ݿ�
			
			msc.executeUpdate("insert into `12058_friends` values('"
					+ currentUser +"','"
					+ target +"')" );
			//������дһ��
			msc.executeUpdate("insert into `12058_friends` values('"
					+ target +"','"
					+ currentUser +"')" );
			
			out.println("<script language=javascript>alert(\"�ɹ���Ӻ���\");window.location='../friends.jsp';</script>");	
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
