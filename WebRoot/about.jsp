<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/about.png">
	</div>
<%@include file="NavLinks.html" %><br>
<div class="center">
<br>
<b>作者：颜震</b><br>
邮箱：<a href="mailto:yanzhen0923@163.com">yanzhen0923@163.com</a>

</div>
<%@include file="footer.jsp" %>
