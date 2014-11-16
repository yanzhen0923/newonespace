package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;
import yz.bean.ShowInfo;

public class DeleteNewsServlet extends HttpServlet {

	private static final long serialVersionUID = -1212795642368932464L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//����δ��¼
		if(user == null){
			//��ת
			response.sendRedirect("../index.jsp");
			return;
		}
		
		
		
		int id = 0;
		
		try{
			id = Integer.parseInt(request.getParameter("id"));
		}
		catch(Exception e){
			response.sendRedirect("../browse.jsp");
		}
		
		MySqlConnection msc = new MySqlConnection();
		
		//����id�Ƿ��Ӧ����
		String q  ="select `NickName` from `12058_news` where `Id`="
				+ id;
		
		ResultSet rs = msc.executeQuery(q);
		
		
		int count = 0;
		
		try {
			while(rs.next()){
				
				//����Ǳ���
				if(rs.getString(1).equals(user)){
					count = 1;
				}
			}
		} catch (SQLException e) {
			response.sendRedirect("../browse.jsp");
		}
		
		//�����Ǳ�������ʾ����
		if(count == 0){
			out.println(ShowInfo.showInfo("../browse.jsp", "�Ƿ�����", "�������"));
			return;
		}
		
		q = "delete from `12058_news` where `Id`="
				+ id + " and `NickName`='"
				+ user + "'" ;
		
		if("success".equals(msc.executeUpdate(q))){
			out.println(ShowInfo.showInfo("../browse.jsp", "ɾ���ɹ�", "�������"));
		}
		
		else{
			out.println(ShowInfo.showInfo("../browse.jsp", "�Ƿ�����", "�������"));
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
