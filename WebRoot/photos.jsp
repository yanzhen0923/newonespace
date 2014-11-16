<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/album.png">
	</div>
<%@include file="NavLinks.html" %><br>
<div class="public"><br><p></p>
<%

	String albumt = request.getParameter("album");
	
	if(albumt == null){
		response.sendRedirect("album.jsp");
		return;
	}
	
	String album = new String(albumt.getBytes("ISO-8859-1"),"utf-8");
	
	String q = "select * from `12058_album_list` where `Album`='"
	+ album + "' and `NickName`='"
	+ user + "'" ;
	
	if(!msc.Exists(q)){
		response.sendRedirect("album.jsp");
		return;
	}
	
	out.println("<div><a href=\"album.jsp\">" + user + "的相册 "  + "</a>" + ": " + album + "</div>");
	
	//页面大小
	int pageSize = 1;
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
	
	q = "select * from `12058_album` where `Album`='" 
	+ album +"' and `NickName`='" 
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
      out.println("<div class=\"albumParent\"><div class=\"albumBody\"> <img src=\"album/"
      + rsCurPage.getString(4) + "\"></div></div><div class=\"albumDes\">"
      + rsCurPage.getString(5) + "</div>");
      }
	%>
	<div class="albumNav"><form method="get" action="photos.jsp">
	第<%=pageNum%>页 共<%=pageCount%>页 
	
	<%if(pageNum<pageCount){
		%><a href="photos.jsp?album=<%=album%>&page=<%=pageNum+1%>">下一页 </a><%
	}%> 
	<%if(pageNum>1){
		%><a href="photos.jsp?album=<%=album%>&page=<%=pageNum-1%>"> 上一页</a><%
	}%>
	
	转到第
	<input class="formText2" type="hidden" name="album" value="<%=album%>" size="8" >
		<input class="formText2" type="text" name="page" size="8" > 页 
		<span><input class="normalSubmit" type="submit" value="转到"></span></form>
<% msc.close(); %>
</div></div>
<%@include file="footer.jsp" %>
