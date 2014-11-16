<%@page import="yz.bean.MySqlConnection"%>
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
		<img src="img/home.png">
	</div>
<%@include file="NavLinks.html" %>
  	<div class="public"><br>
	<p> 欢迎  <% out.println("<b>" + user + "</b>"); %>来到新一空间！     <a href="logout.jsp">切换账户</a></p>
	 <hr/>
	 </div>
  <div class="public">
    <p><b>说说正在发生的事儿吧</b></p>
    <form action="servlet/news" method="post">
    <textarea name="mynews" class="formText3"></textarea><br>
    <select name="priv" class="radius2">
            <option value="0">公开</option>
            <option value="1">私有</option>
            <option value="2">好友可见</option>
    </select>
    <input type="submit" value="发布" class="submitButton2" />
    </form><br/><hr/>
  </div>
  <div class=public>
  <p><b>我的新鲜事</b></p>
	
  <%
  
  	//输出我的新鲜事前五条 
    ResultSet rs = msc.executeQuery("select * from 12058_news where `NickName`='" + user + "' order by `Time` desc limit 0,5");
    while(rs.next()){
    out.println("<div class=\"newsContainer\"> <div><img class=\"avatar\" src=\"" + avt.map.get(user) +"\">");
    out.println(" <b>");
    out.println(si.showLink(user) + "</b> : ");
    out.println(rs.getString(4));
    out.println("</div><div class=\"newsTime\"><br>");
    out.println(rs.getString(3));
    out.println("</div></div><br>");
    }
  %>
  <div class="newsContainer"><a href="browse.jsp?id=<%=user%>">更多我的新鲜事</a></div>
  <hr/>
  <p><b>好友的新鲜事</b></p>
  <% 
  
  	//输出好友的新鲜事前五条
    ResultSet rs0 = msc0.executeQuery("select * from `12058_news` where"
    + "`Authority`=2 and `NickName` in (select `Right` from `12058_friends` where `Left`='" 
    + user + "')  order by `Time` desc limit 0,5");
    while(rs0.next()){
    out.println("<div class=\"newsContainer\"> <div><img class=\"avatar\" src=\"" + avt.map.get(rs0.getString(2)) +"\">");
    out.println(" <b>");
    out.println(si.showLink(rs0.getString(2))+ "</b> : ");
    out.println(rs0.getString(4));
    out.println("</div><div class=\"newsTime\"><br>");
    out.println(rs0.getString(3));
    out.println("</div></div><br>");
    }
  %>
  <div class="newsContainer"><a href="viewfriends.jsp">更多好友的新鲜事</a></div>
  </div>
  
<%@include file="footer.jsp" %>