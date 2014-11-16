<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/login.png">
	</div>
	<div class="form">
		<form action="servlet/login" method="post">
			<label>用户名</label><input name="name" type="text" class="formText1" /><br>
			<label>密码</label><input name="pass" type="password" class="formText1"/><br>
			<div class="verifyDiv" >
				<div class="left">
				<label>验证码</label>
				</div>
				<div class="left">
				<input type="text" name="rand" class="formText2"/>
				</div>
				<div class="left">
					<a href="index.jsp"><img class="verify" src="verify.jsp"/></a>
				</div>
			</div><br><p></p>
			<input type="submit" value="登陆" class="submitButton1" />
			</form>
		<hr/>
			<em>公共账号: yz</em><br>
			<em>密码: 654123</em>
		<hr/>
		<label>没有帐号? </label>
		<a href="register.jsp">点我注册</a>
		<hr/>
		<em>作者信息</em><br><br>
		<em>学号: 1120112058</em><br>
		<em>姓名: 颜震</em><br>
		<em>班号: 08311101</em><br>
	</div>
<%@include file="footer.jsp" %>