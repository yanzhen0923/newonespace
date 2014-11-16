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

		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//若用未登录
		if(user == null){
			//跳转
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
		
		//验证通过 准备删除
		MySqlConnection msc = new MySqlConnection();
		
		//先删除相册封面和每一张相册里的图片   再删除数据库里的记录
		
		//先删封面
		
		String q = "select `Preview` from `12058_album_list` where `NickName`='"
				+ targetUser + "' and `Album`='"
				+ targetAlbum + "'";
		
		ResultSet rs = msc.executeQuery(q);
		try {
			while (rs.next()) {
				
				//若为默认封面则不删除
				if(rs.getString(1).contains("default")){
					break;
				}
				
				String path = this.getServletConfig().getServletContext().getRealPath("/") 
						+ rs.getString(1);
				File f = new File(path);
				f.delete();
			}
		} catch (SQLException e) {
			//out.println(ShowInfo.showError("../album.jsp", "删除失败 请联系管理员"));
		}
		
		
		//再删图片
		
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
			out.println(ShowInfo.showError("../album.jsp", "删除失败 请联系管理员"));
		}
		
		//从相册表里删除记录
		q = "delete from `12058_album_list` where `Album`='"
				+ targetAlbum + "'";
		
		msc.executeUpdate(q);
		
		
		//从照片表里删除记录
		q = "delete from `12058_album` where `Album`='"
				+ targetAlbum + "'";
		
		msc.executeUpdate(q);
		
		out.println(ShowInfo.showInfo("../album.jsp", "删除成功","返回相册"));
				
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
