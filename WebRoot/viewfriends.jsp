<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/news.png">
	</div>
<%@include file="NavLinks.html" %><br><p></p><br>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
 <%avt.Refresh(); %>
<div class="public">
<%
	
	//分页输出好友的所有新鲜事

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
	
	ResultSet rs = msc.executeQuery("select * from `12058_news` where"
    + "(`Authority`=2 and `NickName` in (select `Right` from `12058_friends` where `Left`='" 
    + user + "')) ");
    
    rs.last();
    rowCount = rs.getRow();
    pageCount = (rowCount + pageSize - 1) / pageSize;
    
    if(pageNum > pageCount)
    	pageNum = 1;
    	
	start = (pageNum - 1) * pageSize; 
	ResultSet rsCurPage = msc.executeQuery("select * from `12058_news` where"
    + "`Authority`=2 and `NickName` in (select `Right` from `12058_friends` where `Left`='" 
    + user + "') limit "
    + start + "," + pageSize );
    
    while(rsCurPage.next()){
     out.println("<div class=\"newsContainer\"> <div><img class=\"avatar\" src=\"" + avt.map.get(rsCurPage.getString(2)) +"\">");
    out.println(" <b>");
    out.println(si.showLink(rsCurPage.getString(2))+ "</b> : ");
    out.println(rsCurPage.getString(4));
    out.println("</div><div class=\"newsTime\"><br>");
    out.println(rsCurPage.getString(3));
    out.println("</div></div><br>");
    }
	%>
	<form method="get" action="viewfriends.jsp">
	第<%=pageNum%>页 共<%=pageCount%>页 
	
	<%if(pageNum<pageCount){
		%><a href="viewfriends.jsp?page=<%=pageNum+1%>">下一页 </a><%
	}%> 
	<%if(pageNum>1){
		%><a href="viewfriends.jsp?page=<%=pageNum-1%>"> 上一页</a><%
	}%>
	
	转到第
		<input class="formText2" type="text" name="page" size="8" > 页 
		<span><input class="normalSubmit" type="submit" value="转到"></span></form>
</div>
<% msc.close(); %>
<%@include file="footer.jsp" %>
