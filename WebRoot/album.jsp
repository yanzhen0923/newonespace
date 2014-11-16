<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
<%@include file="header.jsp"%> 
	<div class="topHeader">
		<img src="img/album.png">
	</div>
<%@include file="NavLinks.html" %><br>
<div class="leftPublic"><p></p>
	<div class="leftBar">
	<form method="post" action="servlet/createAlbum">
			<b>新建相册</b><br>
			相册名称 <input type="text" class="formText2" name="albumName"><br>
			<input type="submit" value="创建相册" class="normalSubmit" />
		</form><br><br>
		
		
		<form action="servlet/uploadPhoto" method="post" enctype="multipart/form-data">
		<b>上传照片</b><br>
		选择相册<select name="album" class="radius2">
			<%
				String q = "select `Album` from `12058_album_list` where `NickName`='"
				+ user + "'";
				
				ResultSet rs = msc.executeQuery(q);
				
				while(rs.next()){
				out.println("<option value=\""
				+ rs.getString(1) + "\">"
				+ rs.getString(1) + "</option>" );
				}
				
			 %>
			</select><br>
		<div id="preview">
			<img id="imghead" width=260 height=180 alt="这里显示图片预览">
		</div>
		<input type="file" name="photo" onchange="previewImage(this)" /> 
		<br/>
		添加文字描述 <input type="text" class="formText2" name="description" value="" ><br>
		<input type="submit" value="上传这张照片" class="normalSubmit"/>
		</form>
		</div>
	<div class="rightAlbum">
	<b>我的相册</b>
	<%
			
			q = "select `Album`,`Preview`,`NickName` from `12058_album_list` where `NickName`='"
				+ user + "'";
			
		 	 rs = msc.executeQuery(q);
		 	 
		 	 while(rs.next()){
				out.println("<div class=\"albumContainer\"><div class=\"albumPreview\"><img src=\""
				+ rs.getString(2) + "\"></div><div class=\"albumName\"><a href=\"photos.jsp?album="
				+ rs.getString(1) + "\"><b>"
				+ rs.getString(1) + "</b></a><br><a href=\"servlet/deleteAlbum?user="
				+ rs.getString(3) + "&album="
				+ rs.getString(1)
				+ "\" onclick=\"javascript:return p_del()\">删除这个相册</a></div></div>");
				}
	 %>
	
	
	</div>
</div>

<%@include file="footer.jsp" %>
