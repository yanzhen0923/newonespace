<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/settings.png">
	</div>
<%@include file="NavLinks.html" %><br>
	<div class="public"><p></p>
	<form method="post" action="servlet/settings" class="form1">
			<label>真实姓名</label><input type="text" name="realname" class="formText1" /><br/>
			<label>宿舍号</label><input type="text" name="dormitory" class="formText1" /><br/>
			<label>生日</label><select name="year" class="radius2">
				<option value="1985">1985</option>
				<option value="1986">1986</option>
				<option value="1987">1987</option>
				<option value="1988">1988</option>
				<option value="1989">1989</option>
				<option value="1990">1990</option>
				<option value="1991">1991</option>
				<option value="1992">1992</option>
				<option value="1993">1993</option>
				<option value="1994">1994</option>
			</select>年
			<select name="month" class="radius2">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		  </select>月
		  <select name="day" class="radius2">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		  </select >日<br>
			<input type="submit" name="submit" value="修改" class="submitButton2" />
		</form><br>
<form action="servlet/uploadAvatar" method="post" enctype="multipart/form-data" class="form1">
<h3>选择要上传的头像  只支持png格式</h3>
<input type="file" name="file" size="50" class="inputFile"/>
<br />
<input type="submit" value="上传头像" class="normalSubmit"/>
</form>
</div>
  </body>
</html>
