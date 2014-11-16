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

		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String currentUser = (String)request.getSession().getAttribute("user");
		//����δ��¼
		if(currentUser == null){
			//��ת
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String targetUser = request.getParameter("target");
		//��δ�ύ���֪ͨ�Ķ���
		if(targetUser == null){
			//ת����Ӻ���ҳ��
			response.sendRedirect("../message.jsp");
			return;
		}
		
		//ͨ����֤
		
		try{
		
		//׼�����ӵ����ݿ�
		MySqlConnection msc = new MySqlConnection();
		
		//�����ݿ���ɾ��֪ͨ
		msc.executeUpdate("delete from `12058_info_m` where `Sender`='"
				+ targetUser + "' and `Receiver`='"
				+ currentUser + "'" );
		
		//����
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
