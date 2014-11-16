package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yz.bean.MySqlConnection;
import yz.bean.ShowInfo;

public class CreateAlbumServlet extends HttpServlet {

	private static final long serialVersionUID = -204420620115352753L;

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
		
		String albumName = request.getParameter("albumName");
		
		//��Ϊ��
		if(albumName == null){
			//ת����Ӻ���ҳ��
			response.sendRedirect("../album.jsp");
			return;
		}
		
		//���ַ�������Ϊ0
		if(albumName.length() == 0){
			//ת����Ӻ���ҳ��
			response.sendRedirect("../album.jsp");
			return;
		}
		
		try{
			
		//׼�����ӵ����ݿ�
		MySqlConnection msc = new MySqlConnection();
		
		//�ȼ���Ƿ�������
		
		String q = "insert into `12058_album_list` (`NickName`,`Album`) values('"
				+ currentUser + "','"
				+ albumName + "')";
		
		msc.executeUpdate(q);
		
		out.println(ShowInfo.showInfo("../album.jsp", "��ᴴ���ɹ�", "��ʼ�ϴ�ͼƬ"));
		
		}catch(Exception e){
			out.println(e.getMessage());
		}	
	}
			
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
