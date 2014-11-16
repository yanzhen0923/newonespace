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

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1786047360514225475L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user = (String)request.getSession().getAttribute("user");
		//���û�δ��¼
		if(user == null){
			//������ҳ
			response.sendRedirect("../index.jsp");
			return;
		}
		
		String targetUser = request.getParameter("target");
		//�����Զ���Ϊ��
		if(targetUser == null){
			//��������ҳ
			response.sendRedirect("../message.jsp");
			return;
		}
		
		String targetMessage = request.getParameter("mymessage");
		//������Ϊ��
		if(targetMessage.length() == 0){
			//��������ҳ
			out.println(ShowInfo.showError("../message.jsp", "��Ϣ����Ϊ��"));
			return;
		}
		
		try{
			
			MySqlConnection msc = new MySqlConnection();
			//���������԰��в�������
			String q = "insert into 12058_msg (`Sender`,`Time`,`Content`,`Receiver`) values ('"
					+ user + "','"
					+ MyTime.getTime() + "','"
					+ targetMessage + "','"
					+ targetUser + "')";
			msc.executeUpdate(q);
			
			//����������������֪ͨ
			boolean b = msc.Exists("select * from `12058_info_m` where `Sender`='"
					+ user + "' and `Receiver`='"
					+ targetUser + ";" );
			if(b == true){
				//�������Ժ�ֱ�ӷ���
				response.sendRedirect("../message.jsp");
				return;
			}
			
			//����û��֪ͨ������֪ͨ���в���֪ͨ
			String p = "insert into `12058_info_m` values('"
					+ user + "','"
					+ targetUser + "')" ;
			msc.executeUpdate(p);
			
			//��������ҳ
			response.sendRedirect("../message.jsp");
		}
		catch(Exception e){
			out.println(ShowInfo.showError("../message.jsp", e.getMessage()));
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}