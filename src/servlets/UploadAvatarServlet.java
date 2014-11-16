package servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import yz.bean.MySqlConnection;
import yz.bean.ShowInfo;


@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadAvatarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 5345403998190066926L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		String user = (String)request.getSession().getAttribute("user");
		//若未登录
		if(user == null){
			//返回首页
			response.sendRedirect("../index.jsp");
			return;
		}
		
		//尝试上传头像
		try{
			//获取上传的文件
	        Part part = request.getPart("file");
	        
	        //若上传的头像为png格式 则进行处理
	        if(part.getContentType().equals("image/png")){
	        	
	        	//获取时间戳作为文件名
		        String fileName = Long.toString(System.currentTimeMillis()) + ".png";
		        
		        //找到要上传的文件的路径
				String filePath = this.getServletConfig().getServletContext().getRealPath("/") +"avatar\\";
		        
				//获取inputstream对象
		        InputStream imageStream = part.getInputStream();
		        
		        //从stream中读取文件
		        BufferedImage input = ImageIO.read(imageStream);
		        
		        //声明新的要写入的图片及其大小
	            BufferedImage inputbig = new BufferedImage(50, 50, BufferedImage.TYPE_INT_BGR);
	            
	            //将上传的图片写入新图片中  并调整大小
	            Graphics2D g = (Graphics2D) inputbig.getGraphics();
	            g.drawImage(input, 0, 0,50,50,null); //画图
	            g.dispose();
	            
	            //将文件写入指定的目录
	            ImageIO.write(inputbig, "png", new File(filePath + fileName));
	            
	            MySqlConnection msc = new MySqlConnection();
	            
	            //从数据库中查询旧文件的路径
	            ResultSet rs = msc.executeQuery("select `Avatar` from `12058_users` where `NickName`='"
	            		+ user + "'" );
	            
	            //准备删除
	            while(rs.next()){
	            	
	            	String name = rs.getString(1);
	            	
	            	//若原来是默认图片则不删除
	            	if(name.contains("default.png"))
	            		break;
	            	
	            	//若不是默认图片则删除
	            	File file = new File(this.getServletConfig().getServletContext().getRealPath("/") 
	            			+ name);
	            	file.delete();
	            }
	            
	            //更新头像路径
	            msc.executeUpdate("update `12058_users` set `Avatar`='avatar/" + fileName +"' where `NickName`='" + user +  "'");
	            
	            //返回主页
	            response.sendRedirect("../myspace.jsp");
	        }
	        
	        //若不是png格式 则输出错误信息
	        else {
				out.println(ShowInfo.showError("../modify.jsp", "文件不合法"));
			}
            
		}
		catch(Exception e){
			out.println(e.toString());
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
	
