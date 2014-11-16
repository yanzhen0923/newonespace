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
			//��ȡ�ϴ����ļ�
	        Part part = request.getPart("file");
	        
	        //���ϴ���ͷ��Ϊpng��ʽ ����д���
	        if(part.getContentType().equals("image/png")){
	        	
	        	//��ȡʱ�����Ϊ�ļ���
		        String fileName = Long.toString(System.currentTimeMillis()) + ".png";
		        
		        //�ҵ�Ҫ�ϴ����ļ���·��
				String filePath = this.getServletConfig().getServletContext().getRealPath("/") +"avatar\\";
		        
				//��ȡinputstream����
		        InputStream imageStream = part.getInputStream();
		        
		        //��stream�ж�ȡ�ļ�
		        BufferedImage input = ImageIO.read(imageStream);
		        
		        //�����µ�Ҫд���ͼƬ�����С
	            BufferedImage inputbig = new BufferedImage(50, 50, BufferedImage.TYPE_INT_BGR);
	            
	            //���ϴ���ͼƬд����ͼƬ��  ��������С
	            Graphics2D g = (Graphics2D) inputbig.getGraphics();
	            g.drawImage(input, 0, 0,50,50,null); //��ͼ
	            g.dispose();
	            
	            //���ļ�д��ָ����Ŀ¼
	            ImageIO.write(inputbig, "png", new File(filePath + fileName));
	            
	            MySqlConnection msc = new MySqlConnection();
	            
	            //�����ݿ��в�ѯ���ļ���·��
	            ResultSet rs = msc.executeQuery("select `Avatar` from `12058_users` where `NickName`='"
	            		+ user + "'" );
	            
	            //׼��ɾ��
	            while(rs.next()){
	            	
	            	String name = rs.getString(1);
	            	
	            	//��ԭ����Ĭ��ͼƬ��ɾ��
	            	if(name.contains("default.png"))
	            		break;
	            	
	            	//������Ĭ��ͼƬ��ɾ��
	            	File file = new File(this.getServletConfig().getServletContext().getRealPath("/") 
	            			+ name);
	            	file.delete();
	            }
	            
	            //����ͷ��·��
	            msc.executeUpdate("update `12058_users` set `Avatar`='avatar/" + fileName +"' where `NickName`='" + user +  "'");
	            
	            //������ҳ
	            response.sendRedirect("../myspace.jsp");
	        }
	        
	        //������png��ʽ �����������Ϣ
	        else {
				out.println(ShowInfo.showError("../modify.jsp", "�ļ����Ϸ�"));
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
	
