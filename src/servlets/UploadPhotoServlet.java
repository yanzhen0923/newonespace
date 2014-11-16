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
		
		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		String user = (String)request.getSession().getAttribute("user");
		//��δ��¼
		if(user == null){
			//������ҳ
			response.sendRedirect("../index.jsp");
			return;
		}
		
		//�����ϴ�ͷ��
		try{	     
			//��ȡ�������
	        String album = request.getParameter("album");
	        
	        if(album == null){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        if(album.length() == 0){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        //��ȡ����
	        String descrpiton = request.getParameter("description");
	        
	        if(descrpiton == null){
	        	response.sendRedirect("../album.jsp");
	        	return;
	        }
	        
	        //�������������Ĭ��
	        if(descrpiton.length() == 0){
	        	descrpiton = "����Ƭû��������Ϣ";
	        }
	        
	        //��ȡ�ϴ����ļ�
	        Part part = request.getPart("photo");
	        
	        //���ϴ���ͷ��Ϊһ�¸�ʽ ����д���
	        if(part.getContentType().equals("image/png")
	        		||  part.getContentType().equals("image/jpeg")
	        		|| part.getContentType().equals("image/gif")
	        		|| part.getContentType().equals("image/bmp")){
	        	
	        	//��ȡʱ�����Ϊ�ļ���
	        	String timeStamp = Long.toString(System.currentTimeMillis());
	        	
	        	//�ϴ����ļ�
		        String fileName = timeStamp + ".png";
		        
		        //���ɵķ���
		        String filenamef = timeStamp + "f" + ".png";
		        
		        //�ҵ�Ҫ�ϴ����ļ���·��
				String filePath = this.getServletConfig().getServletContext().getRealPath("/") +"album\\";
		        
				//��ȡinputstream����
		        InputStream imageStream = part.getInputStream();
		        
		        //��stream�ж�ȡ�ļ�
		        BufferedImage input = ImageIO.read(imageStream);
		        
		        //��ȡ���
		        double width = input.getWidth();
		        double height = input.getHeight();
		        
		        //��߱�
		        double rate = width / height;
		       
		        double maxWH = 720.0;
		        
		        //������
		        if(rate >= 1 && width > maxWH){
		        		height = (maxWH / width) * height;
		        		width = maxWH;
		        }
		        
		        //������
		        if(rate < 1 && height > maxWH){
	        		width = (maxWH / height) * width;
	        		height = maxWH;
		        }
		        
		        int intWidth = (int)width;
		        int intHeight  = (int)height;
		        
		        //�����µ�Ҫд���ͼƬ�����С
	            BufferedImage inputbig = new BufferedImage(intWidth, intHeight, BufferedImage.TYPE_INT_BGR);
	            
	            //�����µ�Ҫд��ķ��漰���С �̶�Ϊ128*128
	            BufferedImage inputsmall = new BufferedImage(128, 128,BufferedImage.TYPE_INT_BGR);
	            
	            //���ϴ���ͼƬд����ͼƬ��  ��������С
	            Graphics2D g = (Graphics2D) inputbig.getGraphics();
	            g.drawImage(input, 0, 0,intWidth,intHeight,null); //��ͼ
	            g.dispose();
	            
	            //���ϴ���ͼƬд���·�����  ��������С
	            Graphics2D g1 = (Graphics2D) inputsmall.getGraphics();
	            g1.drawImage(input, 0, 0,128,128,null); //��ͼ
	            g1.dispose();
	            
	            //���ļ�д��ָ����Ŀ¼
	            ImageIO.write(inputbig, "png", new File(filePath + fileName));
	            ImageIO.write(inputsmall, "png", new File(filePath + filenamef));
	            
	            MySqlConnection msc =  new MySqlConnection();
	            
	            //�����ݿ��������ͼƬ
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
	            
	            //׼��ɾ����ʷ����
	            while(rs.next()){
	            	
	            	String name = rs.getString(1);
	            	
	            	//��ԭ����Ĭ��ͼƬ��ɾ��
	            	if(name.contains("default"))
	            		break;
	            	
	            	//������Ĭ��ͼƬ��ɾ��
	            	File file = new File(this.getServletConfig().getServletContext().getRealPath("/") 
	            			+ name);
	            	file.delete();
	            }
	            
	            //���÷���·��
	            q = "update `12058_album_list` set `Preview`='album/"
	            		+ filenamef + "' where `NickName`='"
	            		+ user +"' and `Album`='"
	            		+ album + "'" ;
	            
	            msc.executeUpdate(q);
	            
	            //��ȫ��ż����ִ����
	            out.println(ShowInfo.showInfo("../album.jsp", "�ϴ��ɹ�", "�������"));
	    
	        }
	        
	        //������png��ʽ �����������Ϣ
	        else {
				out.println(ShowInfo.showError("../album.jsp", "�ļ����Ϸ�"));
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
