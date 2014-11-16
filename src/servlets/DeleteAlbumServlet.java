package servlets;

import java.io.File;
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

public class DeleteAlbumServlet extends HttpServlet {

	private static final long serialVersionUID = 421852938177895678L;

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
		
		//String targetUser = new String(request.getParameter("user").getBytes("ISO-8859-1"),"utf-8");
		
		String targetUser = request.getParameter("user");
		
		if(targetUser == null){
			response.sendRedirect("../album.jsp");
			return;
		}
		
		targetUser = new String(targetUser.getBytes("ISO-8859-1"),"utf-8");
		
		if(!user.equals(targetUser)){
			response.sendRedirect("../album.jsp");
			return;
		}
		
		String targetAlbum = request.getParameter("album");
		
		if(targetAlbum == null){
			response.sendRedirect("../album.jsp");
			return;
		}
		
		targetAlbum = new String(targetAlbum.getBytes("ISO-8859-1"),"utf-8");
		
		//��֤ͨ�� ׼��ɾ��
		MySqlConnection msc = new MySqlConnection();
		
		//��ɾ���������ÿһ��������ͼƬ   ��ɾ�����ݿ���ļ�¼
		
		//��ɾ����
		
		String q = "select `Preview` from `12058_album_list` where `NickName`='"
				+ targetUser + "' and `Album`='"
				+ targetAlbum + "'";
		
		ResultSet rs = msc.executeQuery(q);
		try {
			while (rs.next()) {
				
				//��ΪĬ�Ϸ�����ɾ��
				if(rs.getString(1).contains("default")){
					break;
				}
				
				String path = this.getServletConfig().getServletContext().getRealPath("/") 
						+ rs.getString(1);
				File f = new File(path);
				f.delete();
			}
		} catch (SQLException e) {
			//out.println(ShowInfo.showError("../album.jsp", "ɾ��ʧ�� ����ϵ����Ա"));
		}
		
		
		//��ɾͼƬ
		
		q = "select `PhotoName` from `12058_album` where `Album`='"
				+ targetAlbum + "'";
		
		rs = msc.executeQuery(q);
		
		try {
			while (rs.next()) {
				String path = this.getServletConfig().getServletContext().getRealPath("/") + "album/"
						+ rs.getString(1);
				
				File f = new File(path);
				f.delete();
			}
		} catch (SQLException e) {
			out.println(ShowInfo.showError("../album.jsp", "ɾ��ʧ�� ����ϵ����Ա"));
		}
		
		//��������ɾ����¼
		q = "delete from `12058_album_list` where `Album`='"
				+ targetAlbum + "'";
		
		msc.executeUpdate(q);
		
		
		//����Ƭ����ɾ����¼
		q = "delete from `12058_album` where `Album`='"
				+ targetAlbum + "'";
		
		msc.executeUpdate(q);
		
		out.println(ShowInfo.showInfo("../album.jsp", "ɾ���ɹ�","�������"));
				
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
