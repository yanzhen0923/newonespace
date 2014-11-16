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
public class UploadPhotoServlet extends HttpServlet {

	private static final long serialVersionUID = -5265307028287649418L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
			//获取相册名字
	        String album = request.getParameter("album");
	        
	        if(album == null){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        if(album.length() == 0){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        //获取描述
	        String descrpiton = request.getParameter("description");
	        
	        if(descrpiton == null){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        //若无描述则添加默认
	        if(descrpiton.length() == 0){
	        	descrpiton = "此照片没有描述信息";
	        }
	        
	        //获取上传的文件
	        Part part = request.getPart("photo");
	        
	        //若上传的头像为一下格式 则进行处理
	        if(part.getContentType().equals("image/png")
	        		||  part.getContentType().equals("image/jpeg")
	        		|| part.getContentType().equals("image/gif")
	        		|| part.getContentType().equals("image/bmp")){
	        	
	        	//获取时间戳作为文件名
	        	String timeStamp = Long.toString(System.currentTimeMillis());
	        	
	        	//上传的文件
		        String fileName = timeStamp + ".png";
		        
		        //生成的封面
		        String filenamef = timeStamp + "f" + ".png";
		        
		        //找到要上传的文件的路径
				String filePath = this.getServletConfig().getServletContext().getRealPath("/") +"album\\";
		        
				//获取inputstream对象
		        InputStream imageStream = part.getInputStream();
		        
		        //从stream中读取文件
		        BufferedImage input = ImageIO.read(imageStream);
		        
		        //获取宽高
		        double width = input.getWidth();
		        double height = input.getHeight();
		        
		        //宽高比
		        double rate = width / height;
		       
		        double maxWH = 720.0;
		        
		        //若过宽
		        if(rate >= 1 && width > maxWH){
		        		height = (maxWH / width) * height;
		        		width = maxWH;
		        }
		        
		        //若过高
		        if(rate < 1 && height > maxWH){
	        		width = (maxWH / height) * width;
	        		height = maxWH;
		        }
		        
		        int intWidth = (int)width;
		        int intHeight  = (int)height;
		        
		        //声明新的要写入的图片及其大小
	            BufferedImage inputbig = new BufferedImage(intWidth, intHeight, BufferedImage.TYPE_INT_BGR);
	            
	            //声明新的要写入的封面及其大小 固定为128*128
	            BufferedImage inputsmall = new BufferedImage(128, 128,BufferedImage.TYPE_INT_BGR);
	            
	            //将上传的图片写入新图片中  并调整大小
	            Graphics2D g = (Graphics2D) inputbig.getGraphics();
	            g.drawImage(input, 0, 0,intWidth,intHeight,null); //画图
	            g.dispose();
	            
	            //将上传的图片写入新封面中  并调整大小
	            Graphics2D g1 = (Graphics2D) inputsmall.getGraphics();
	            g1.drawImage(input, 0, 0,128,128,null); //画图
	            g1.dispose();
	            
	            //将文件写入指定的目录
	            ImageIO.write(inputbig, "png", new File(filePath + fileName));
	            ImageIO.write(inputsmall, "png", new File(filePath + filenamef));
	            
	            MySqlConnection msc =  new MySqlConnection();
	            
	            //在数据库中添加新图片
	            String q = "insert into `12058_album` (`NickName`,`Album`,`PhotoName`,`Description`) values('"
	            		+ user + "','" 
	            		+ album + "','"
	            		+ fileName + "','"
	            		+ descrpiton + "')" ;
	            
	            msc.executeUpdate(q);
	            
	            q = "select `Preview` from `12058_album_list` where `NickName`='"
	            		+ user + "' and `Album`='"
	            		+ album + "'" ;
	            		
	            ResultSet rs = msc.executeQuery(q);
	            
	            //准备删除历史封面
	            while(rs.next()){
	            	
	            	String name = rs.getString(1);
	            	
	            	//若原来是默认图片则不删除
	            	if(name.contains("default"))
	            		break;
	            	
	            	//若不是默认图片则删除
	            	File file = new File(this.getServletConfig().getServletContext().getRealPath("/") 
	            			+ name);
	            	file.delete();
	            }
	            
	            //设置封面路径
	            q = "update `12058_album_list` set `Preview`='album/"
	            		+ filenamef + "' where `NickName`='"
	            		+ user +"' and `Album`='"
	            		+ album + "'" ;
	            
	            msc.executeUpdate(q);
	            
	            //若全都偶正常执行完
	            out.println(ShowInfo.showInfo("../album.jsp", "上传成功", "返回相册"));
	    
	        }
	        
	        //若不是png格式 则输出错误信息
	        else {
				out.println(ShowInfo.showError("../album.jsp", "文件不合法"));
			}
            
		}
		catch(Exception e){
			out.println(e.toString());
		}

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
