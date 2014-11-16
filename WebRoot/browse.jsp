<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
 <%avt.Refresh(); %>
<%! String user;%>
<%!

//本页要用到的变量 
String q = ""; String result="";

%>
<% user = (String)session.getAttribute("user");
if(user == null)
{
response.sendRedirect("index.jsp");
return;
}
%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/personal.png">
	</div>
<%@include file="NavLinks.html" %><br>
<%
String args= request.getParameter("id");

if(args == null){
	
	//若没有参数默认看自己
	args = user;
}

boolean isMe = false;

//转码
result = new String(args.getBytes("ISO-8859-1"),"utf-8");

	boolean r = msc.Exists("select * from `12058_users` where `NickName`='" + result + "'");
	//若此人不存在
	if(r == false){
	
		//默认访问自己的主页
		result = user;
	}
	
	//若存在
	//确定与 本人关系
	//若是本人
	
if(result.equals(user)){
	isMe = true;
	out.println("<div class=\"public\">");
	ResultSet rsInfo = msc.executeQuery("select `RealName`,`Dorm`,`Birthday` from 12058_users where `NickName`='"
	+ user +"'");
	//输出个人信息
	while(rsInfo.next()){
	out.println("<p>欢迎 <img src=\"" +
	avt.map.get(user) + "\" class=\"avatar\"><b> "
	+ result + " !</b></p><p> 真名 : <b>"
	+ rsInfo.getString(1) + "</b> 住在 : <b>"
	+ rsInfo.getString(2) + "</b></p><p> 生日 : <b>"
	+ rsInfo.getString(3) + "</b></p>");
	}
	out.println("<hr/></div>");
	
	q = "select * from `12058_news` where `NickName`='" + user + "' order by `Time` desc";
}
//若不是本人，则判断两人是否为好友
else{


	
	boolean r1 = msc.Exists("select * from `12058_friends` where `Left`='"
	 + user + "' and `Right`='"
	 + result + "'" );
	 //若是好友
	 if(r1 == true){
	 
	 	//输出个人信息
	 	out.println("<div class=\"public\">");
		ResultSet rsInfo = msc.executeQuery("select `RealName`,`Dorm`,`Birthday` from `12058_users` where `NickName`='"
		+ result +"'");
		while(rsInfo.next()){
		out.println("<p> 您正在访问 <img src=\"" +
		avt.map.get(result) + "\" class=\"avatar\"><b> "
		+ result + "</b> 的个人主页  (已加好友)</p><p> 真名 : <b>"
		+ rsInfo.getString(1) + "</b> 住在 : <b>"
		+ rsInfo.getString(2) + "</b></p><p> 生日 : <b>"
		+ rsInfo.getString(3) + "</b></p>");
		}
		out.println("<hr/></div>");
		
		q = "select * from `12058_news` where `NickName`='" 
		+ result + "' and `Authority`<>1" 
		+ " order by `Time` desc";
		
	 }
	 
	 //若不是好友
	 //输出摘要和公开的状态
	 else{
	 
	 //摘要
	 	out.println("<div class=\"public\">");
		out.println("<p> 您正在访问 <img src=\"" +
		avt.map.get(result) + "\" class=\"avatar\"><b> "
		+ result + "</b> 的个人主页 " +
		"<form action=\"servlet\\addFriends\" method=\"post\"><input type=\"hidden\" name=\"select\" value=\""
		+ result + "\"><input class=\"normalSubmit\" type=\"submit\" value=\"加TA为好友\"/></form>" 
		+ "</p>");
		out.println("<hr/></div>");
		
	//公开的状态
		q = "select * from `12058_news` where `NickName`='" 
		+ result + "' and `Authority`=0" 
		+ " order by `Time` desc";
		
	 }
 }
 %>
 <div class="public">
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
	
	
	
	ResultSet rs = msc.executeQuery(q);
	
	//光标移到最后    
    rs.last();
    
    //得到最后一行的数字
    rowCount = rs.getRow();
    
    //看共有多少页
    pageCount = (rowCount + pageSize - 1) / pageSize;
    
    //容错
    if(pageNum > pageCount)
    	pageNum = 1;
    	
    //起始位置
	start = (pageNum - 1) * pageSize; 
	
	//执行查询
	//从其实位置查询到其上的pageSize条
	ResultSet rsCurPage = msc.executeQuery(q + " limit "
    + start + "," + pageSize);
    
    
    //根据需求输出输出
    while(rsCurPage.next()){
     out.println("<div class=\"newsContainer\"> <div><img class=\"avatar\" src=\"" + avt.map.get(rsCurPage.getString(2)) +"\">");
    out.println(" <b>");
    out.println(si.showLink(rsCurPage.getString(2))+ "</b> : ");
    out.println(rsCurPage.getString(4));
    out.println("</div><div class=\"newsTime\"><br>");
    out.println(rsCurPage.getString(3));
    out.println("</div><br>");
    
    if(isMe){
    	out.println("<div class=\"newsTime\"><a href=\"servlet/deleteNews?id=" 
    	+ rsCurPage.getString(1) + "\">删除这条状态</a></div></div><br>");
    }
    
    }
	%>
	<form method="get" action="browse.jsp">
	第<%=pageNum%>页 共<%=pageCount%>页 
	
	<!--  如果还有下一页-->
	<%if(pageNum<pageCount){
		%><a href="browse.jsp?id=<%=result %>&page=<%=pageNum+1%>">下一页 </a><%
	}%> 
	
	<!--  如果还有上一页-->
	<%if(pageNum>1){
		%><a href="browse.jsp?id=<%=result%>&page=<%=pageNum-1%>"> 上一页</a><%
	}%>
	
	转到第
		<input type="hidden" name="id" value="<%=result%>">
		<input class="formText2" type="text" name="page" size="8" > 页 
		
		<span><input class="normalSubmit" type="submit" value="转到"></span></form>
<%
	//关闭数据库连接  防止内存泄露 
	msc.close(); 
%>
</div>
<%@include file="footer.jsp" %>
