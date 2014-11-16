package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;
import yz.bean.MyTime;
import yz.bean.ShowInfo;

public class NewsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 343941966872119685L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//��δ��¼�򷵻���ҳ
		if(user == null){
			response.sendRedirect("../index.jsp");
			return;
		}
		
		//����ϢΪ��
		if(request.getParameter("mynews").length() == 0){
			//Ҫ�󷵻�
			out.println(ShowInfo.showError("../myspace.jsp", "��Ϣ����Ϊ��"));
			return;
		}
		
		
		//ͨ����֤
		try{
			MySqlConnection msc = new MySqlConnection();
			//�����Ų��뵽���ݿ�
			String q = "insert into 12058_news (`NickName`,`Time`,`Content`,`Authority`) values ('" 
					+ user + "','"
					+ MyTime.getTime() + "','"
					+ request.getParameter("mynews") + "',"
					+ request.getParameter("priv") + ")";
			msc.executeUpdate(q);
			//����
			response.sendRedirect("../myspace.jsp");
		}
		catch(Exception e){
			out.println(ShowInfo.showError("../myspace.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
