<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/register.png">
	</div>
	<div class="form">
		<form action="servlet/register" method="post">
			<label>用户名</label><input type="text" name="name" class="formText1"/><br/>
			<label>密码</label><input type="password" name="pass" class="formText1"/><br/>
			<label>重复密码</label><input type="password" name="rpass" class="formText1"/><br/>
			<label>邮箱</label><input type="text" name="email" class="formText1"/><br/>
			<div class="verifyDiv" >
				<div class="left">
				<label>验证码</label>
				</div>
				<div class="left">
				<input type="text" name="rand" class="formText2"/>
				</div>
				<div class="left">
					<a href="register.jsp"><img class="verify" src="verify.jsp"/></a>
				</div>
			</div><br><p></p>
			<input type="submit" name="submit" value="注册" class="submitButton1" />
		</form>
	</div>
<%@include file="footer.jsp" %>