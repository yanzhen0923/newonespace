<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="msc0" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
 <%avt.Refresh(); %>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/friends.png">
	</div>
<%@include file="NavLinks.html" %><br>
<div class="public">
<p></p>
<%
	//检查有没有加好友的通知
	ResultSet rsf = msc.executeQuery("select `Sender` from `12058_info_f` where `Receiver`='"
	+ user + "'");
	
	//如果有通知
	if(rsf != null){
		
		out.println("<b>来自通知中心的消息</b><br><br>");
		while(rsf.next()){
		String rqer = rsf.getString(1);
		String s = "<div><img src=\""
		+ avt.map.get(rqer) + "\" class=\"avatar\"><b> "
		+ si.showLink(rqer) +" </b>请求加你为好友</div><form action=\"servlet\\processAddFriends\" method=\"post\">"
		+ "<input type=\"radio\" name=\"option\" checked=\"checked\" value=\"yes\" > 接受 "
		+ "<input type=\"radio\" name=\"option\" value=\"no\" > 拒绝 "
		+ "<input type=\"hidden\" name=\"target\" value=\""
		+  rqer + "\">"
		+ "<input type=\"submit\" class=\"normalSubmit\" name=\"submit\" value=\"确定\"></form><br>"  ;
		out.println(s);
		}
		out.println("<hr/>");
	
	}
	
 %>

<div><b>管理好友</b></div><br>
<form action="servlet/deleteFriends" method="post">
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
	
	String q = "select `Right` from `12058_friends` where `Left`='" 
	+ user + "'";
	
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
      out.println("<div><input type=\"radio\" name=\"select\" value=" 
      + rsCurPage.getString(1) + " class=\"radius2\" /> "
	+ "<img src=\"" + avt.map.get(rsCurPage.getString(1)) + "\" alt=\"\" class=\"avatar\"> "
	+ si.showLink(rsCurPage.getString(1)) +"</div><br>");
	}    
 %>
<% msc.close(); %>
<input type="submit" name="submit" value="删除" class="submitButton2" />
</form>
 <form method="get" action="friends.jsp">
	第<%=pageNum%>页 共<%=pageCount%>页 
	
	<%if(pageNum<pageCount){
		%><a href="friends.jsp?page=<%=pageNum+1%>">下一页 </a><%
	}%> 
	<%if(pageNum>1){
		%><a href="friends.jsp?page=<%=pageNum-1%>"> 上一页</a><%
	}%>
	
	转到第
		<input class="formText2" type="text" name="page" size="8" > 页 
		<span><input class="normalSubmit" type="submit" value="转到"></span></form>
<hr/>
<div><b>你和TA可能认识</b></div><br>
<form class="top" action="servlet/addFriends" method="post">
<%
ResultSet rs1 = msc0.executeQuery(
	"select `NickName` from `12058_users` where `NickName` not in ( select `Right` from `12058_friends` where `Left`='" 	
	+ user +"') and `NickName` <> '" + user +"' order by rand() limit 5");
while(rs1.next()){
	out.println("<div><input type=\"radio\" name=\"select\" value=" + rs1.getString(1) + " class=\"radius2\"/> "
	+ "<img src=\"" + avt.map.get(rs1.getString(1)) + "\" alt=\"\" class=\"avatar\"> "
	+ si.showLink(rs1.getString(1)) +"</div><br>");
}
%>
<input type="submit" name="submit" value="添加" class="submitButton2" />
</form><br>
<a href="search.jsp" >查看更多</a>
</div>
<%@include file="footer.jsp" %>
