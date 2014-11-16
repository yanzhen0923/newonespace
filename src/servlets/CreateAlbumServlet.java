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

		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
				
		PrintWriter out = response.getWriter();
		
		String currentUser = (String)request.getSession().getAttribute("user");
		//若用未登录
		if(currentUser == null){
			//跳转
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String albumName = request.getParameter("albumName");
		
		//若为空
		if(albumName == null){
			//转回添加好友页面
			response.sendRedirect("../album.jsp");
			return;
		}
		
		//若字符串长度为0
		if(albumName.length() == 0){
			//转回添加好友页面
			response.sendRedirect("../album.jsp");
			return;
		}
		
		try{
			
		//准备链接到数据库
		MySqlConnection msc = new MySqlConnection();
		
		//先检查是否已申请
		
		String q = "insert into `12058_album_list` (`NickName`,`Album`) values('"
				+ currentUser + "','"
				+ albumName + "')";
		
		msc.executeUpdate(q);
		
		out.println(ShowInfo.showInfo("../album.jsp", "相册创建成功", "开始上传图片"));
		
		}catch(Exception e){
			out.println(e.getMessage());
		}	
	}
			
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
