<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/message.png">
	</div>
<%@include file="NavLinks.html" %>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="msc0" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
 <%avt.Refresh(); %>
<div class="public"><br>
	<p> 欢迎  <% out.println("<b>" + user + "</b>"); %>来到新一空间</p><hr/>
  	</div>
  <div class="public">
  <%
	//检查有没有留言的通知
	ResultSet rsf = msc.executeQuery("select `Sender` from `12058_info_m` where `Receiver`='"
	+ user + "'");
	
	//如果有留言
	if(rsf != null){
		out.println("<b>来自通知中心的消息</b><br><br>");
		while(rsf.next()){
		String rqer = rsf.getString(1);
		String s = "<div><img src=\""
		+ avt.map.get(rqer) + "\" class=\"avatar\"><b> "
		+ si.showLink(rqer) +" </b>给你留言了</div><form action=\"servlet/processMessage\" method=\"post\">"
		+ "<input type=\"hidden\" name=\"target\" value=\""
		+  rqer + "\">"
		+ "<input type=\"submit\" class=\"normalSubmit\" name=\"submit\" value=\"确定\"></form><br>"  ;
		out.println(s);
		}
		out.println("<hr/>");
	
	}
	
 %>
    <p><b>给好友留言</b></p>
    <form action="servlet/message" method="post">
    <textarea name="mymessage" class="formText3"></textarea><br>
    <select name="target" class="radius2">
    <%
    ResultSet rs = msc.executeQuery("select `Right` from `12058_friends` where `Left`='" + user + "'");
    while(rs.next()){
    out.println("<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "</option>");
    }
     %>
    </select>
    <input type="submit" value="留言" class="submitButton2" />
    </form><br/>
    <hr/><div class="newsContainer" >
      <p><b>我的留言板</b></p>
      <%
	//页面大小
	int pageSize = 5 ;
	//当前行号
	int rowCount = 0;
	//共有多少页
	int pageCount = 1;
	//当前页号
	int pageNum = 1;
	//起始行号
	int start = 0;
	//获取提交的页号信息
	String strPage = request.getParameter("page");
	if(strPage == null){
		//若没有则页号等于1
		pageNum = 1;
	}
	else{
	//设置参数
		try{
		//解析页号信息
		pageNum = Integer.parseInt(strPage); 
		
		//若不合法
		if(pageNum < 1)
			pageNum = 1;
		}
		catch(Exception e){
		}
	}
	
	String q = "select * from 12058_msg where `Sender`='" 
	+ user +"' or `Receiver`='" 
	+ user + "' order by `Time` desc";
	
	ResultSet rs0 = msc.executeQuery(q);
    
    rs0.last();
    rowCount = rs0.getRow();
    pageCount = (rowCount + pageSize - 1) / pageSize;
    
    if(pageNum > pageCount)
    	pageNum = 1;
    	
	start = (pageNum - 1) * pageSize; 
	ResultSet rsCurPage = msc.executeQuery(q + " limit " 
	+ start + "," + pageSize );
    
    
    while(rsCurPage.next()){
      out.println("<div> <img src=\""
      + avt.map.get(rsCurPage.getString(2)) + "\" class=\"avatar\"> <b>"
      + si.showLink(rsCurPage.getString(2)) + "</b> 对 <img src=\""
      + avt.map.get(rsCurPage.getString(3)) + "\" class=\"avatar\"> <b>"
      + si.showLink(rsCurPage.getString(3)) + "</b> 说:<br><br>"
      + rsCurPage.getString(4) + "<br></div><div class=\"newsTime\">"
      + rsCurPage.getString(5) + "</div>");
      }
	%>
	<form method="get" action="message.jsp">
	第<%=pageNum%>页 共<%=pageCount%>页 
	
	<%if(pageNum<pageCount){
		%><a href="message.jsp?page=<%=pageNum+1%>">下一页 </a><%
	}%> 
	<%if(pageNum>1){
		%><a href="message.jsp?page=<%=pageNum-1%>"> 上一页</a><%
	}%>
	
	转到第
		<input class="formText2" type="text" name="page" size="8" > 页 
		<span><input class="normalSubmit" type="submit" value="转到"></span></form>
<% msc.close(); %>
      
    </div>
  </div>
<%@include file="footer.jsp" %>
