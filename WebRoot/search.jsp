<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%! String user;String q;int sType = 0;%>
<% user = (String)session.getAttribute("user");
if(user == null)
response.sendRedirect("index.jsp");
%>
<jsp:useBean id="msc" class="yz.bean.MySqlConnection" scope="session"/>
<jsp:useBean id="avt" class="yz.bean.Avatar" scope="session"/>
<jsp:useBean id="si" class="yz.bean.ShowInfo" scope="session"/>
<jsp:useBean id="vs" class="yz.bean.VerifySearch" scope="session"/>
 <%avt.Refresh(); %>
<%@include file="header.jsp"%>
	<div class="topHeader">
		<img src="img/search.png">
	</div>
<%@include file="NavLinks.html" %><br>
<div class="public">
    <br><b>搜好友</b>
    <form action="search.jsp" method="get">
    <input name="tPer" class="formText2"></input> <input name="tCon" value="any" type="radio" checked="checked"/>非全字匹配(模糊)<input name="tCon" value="strict" type="radio"/>全字匹配(精确)<br>
    <input type="submit" value="搜索" class="submitButton2" />
    </form><hr/>
    <br><b>搜状态</b>
    <form action="search.jsp" method="get">
    <input name="tNews" class="formText2"></input><br>
    <input type="radio" checked="checked" name="byWhat" value="strict" class="radius2" class="2"></input>按日期搜索
    从 <input name="tFrom" class="formText2"></input>
    到 <input name="tTo" class="formText2"></input> 必须为0000-00-00格式 <br>
    <input type="radio" name="byWhat" value="any" class="radius2" class="2"></input>不添加约束条件<br>
    <input type="submit" value="搜索" class="submitButton2" />
    </form><hr/>
    
    <%
    
    sType = 0;
    
    //搜好友
    vs.setParam(request.getParameter("tPer"));
    vs.setCondition(request.getParameter("tCon"));
    if(vs.isFriendsValid()){
    
    	//设置搜索对象为好友
    	sType = 1;
    	
    	//转码并保存参数
     	String result = new String(vs.getParam().getBytes("ISO-8859-1"),"utf-8");
     	vs.setParam(result);
     	
     	//若是精确查询
     	if("strict".equals(vs.getCondition())){
     		q = "select * from `12058_users` where `NickName`='"
     		+ result + "'";
     	}
     	
     	else{
     	
     	//设置最终要查询的语句
     	q = "select * from `12058_users` where `NickName` like '%"
     	+ result + "%'";
     	}
    }
    
    
    //若不是搜好友 检测是否搜新鲜事
    if(sType != 1){
    
    	//获取提交参数
    	vs.setParam(request.getParameter("tNews"));
    	
    	//若参数不为空
    	if(vs.isAnyNewsValid()){
    		//若为无条件查询
    		if("any".equals(request.getParameter("byWhat"))){
    			
    			//设置类型为无约束条件查询
    			sType = 2;
    			
    			//转码并保存参数
    			String result = new String(vs.getParam().getBytes("ISO-8859-1"),"utf-8");
    			vs.setParam(result);
    			
    			//设置语句
    			q = "select * from `12058_news` where `Content` like '%"
	     			+ result 
	     			+ "%' and (`Authority`=0 or (`Authority`=2 and `NickName` in (select `Right` from `12058_friends` where `Left`='"
		     	+ user + "')))"; 
    		}
    		
    		//若为有条件查询
    		else if("strict".equals(request.getParameter("byWhat"))){

    			//获取时间参数
	    		vs.setDate1(request.getParameter("tFrom"));
			    vs.setDate2(request.getParameter("tTo"));
			    
			   	//若时间对象合法
			   	if(vs.isStrictNewsValid()){
			   	
			   		//设置为有约束条件查询
					sType = 3;
					
					//转码并保存参数
					String result = new String(vs.getParam().getBytes("ISO-8859-1"),"utf-8");
					vs.setParam(result);
				
				//设置查询语句	
		     	q = "select * from `12058_news` where `Content` like '%"
		     	+ result + "%' and `Time`>='"
		     	+ vs.getDate1() +  "' and `Time`<='" + vs.getDate2() 
		     	+ "' and (`Authority`=0 or (`Authority`=2 and `NickName` in (select `Right` from `12058_friends` where `Left`='"
		     	+ user + "')))";   	
    			
    		}
    	}
    }
  }
  
  
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
  		//终于获取完参数了
  		
  		//若 应该显示结果
   		if(sType != 0){
   		
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
		
		 if(rs == null){
	    	response.sendRedirect("search.jsp");
	    	return;
	    }
		
	    rs.last();
	    rowCount = rs.getRow();
	    pageCount = (rowCount + pageSize - 1) / pageSize;
	    
	    if(pageNum > pageCount)
	    	pageNum = 1;
	    	
		start = (pageNum - 1) * pageSize; 
		ResultSet rsCurPage = msc.executeQuery(q + " limit "
	    + start + "," + pageSize);
	    
	    if(rsCurPage == null){
	    	response.sendRedirect("search.jsp");
	    	return;
	    }
	    
	    //若应该有输出信息 则走以下流程
	    
	    if(sType == 2 || sType == 3){
	    
	    //先输出头
	    out.println("<div><b>状态的搜索结果</b></div>");
	    
	    while(rsCurPage.next()){
	    	
		    out.println("<div class=\"newsContainer\"> <div><img class=\"avatar\" src=\"" + avt.map.get(rsCurPage.getString(2)) +"\">");
		    out.println(" <b>");
		    out.println(si.showLink(rsCurPage.getString(2))+ "</b> : ");
		    out.println(rsCurPage.getString(4));
		    out.println("</div><div class=\"newsTime\"><br>");
		    out.println(rsCurPage.getString(3));
		    out.println("</div></div><br>");
		  }
		  
		  
		  //根据不同的搜索类型
		  //输出不同的搜索结果导航栏
		  
		  //若为无条件查询
		  if(sType == 2){
		  	
		  	out.println("<form method=\"get\" action=\"search.jsp\">第"
		  	+ pageNum +"页  共" + pageCount + "页" );
		  	
		  	if(pageNum < pageCount){
		  		out.println("<a href=\"search.jsp?byWhat=any&tNews="+
		  		vs.getParam() + "&page=" + (pageNum+1) +"\">下一页 </a>");
		  	}
		  	
		  	if(pageNum > 1){
		  		out.println("<a href=\"search.jsp?byWhat=any&tNews="+
		  		vs.getParam() + "&page=" + (pageNum-1) +"\">上一页 </a>");
		  	}
		  	
		  	out.println("转到第 <input type=\"hidden\" name=\"tNews\" value=\"" + vs.getParam() +"\">"
		    + "<input type=\"hidden\" name=\"byWhat\" value=\"any\">"
		    + "<input class=\"formText2\" type=\"text\" name=\"page\" size=\"8\">页 "
		    + "<span><input class=\"normalSubmit\" type=\"submit\" value=\"转到\"></span></form>");
		    
		  }
		  
		  //若为带时间的查询
		  else if(sType == 3){
		  	out.println("<form method=\"get\" action=\"search.jsp\">第"
		  	+ pageNum +"页  共" + pageCount + "页" );
		  	
		  	if(pageNum < pageCount){
		  		out.println("<a href=\"search.jsp?byWhat=strict&tNews="+
		  		vs.getParam() + "&tFrom=" + vs.getDate1() + "&tTo=" + vs.getDate2()
		  		+ "&page=" + (pageNum+1) +"\">下一页 </a>");
		  	}
		  	
		  	if(pageNum > 1){
		  		out.println("<a href=\"search.jsp?byWhat=strict&tNews="+
		  		vs.getParam() + "&tFrom=" + vs.getDate1() + "&tTo=" + vs.getDate2()
		  		+ "&page=" + (pageNum-1) +"\">上一页 </a>");
		  	}
		  	
		  	out.println("转到第 <input type=\"hidden\" name=\"tNews\" value=\"" + vs.getParam() +"\">"
		    + "<input type=\"hidden\" name=\"byWhat\" value=\"strict\">"
		    + "<input type=\"hidden\" name=\"tFrom\" value=\"" + vs.getDate1() + "\">" 
		    + "<input type=\"hidden\" name=\"tTo\" value=\"" + vs.getDate2() + "\">"
		    + "<input class=\"formText2\" type=\"text\" name=\"page\" size=\"8\">页 "
		    + "<span><input class=\"normalSubmit\" type=\"submit\" value=\"转到\"></span></form>");
		  }
	    }
	    
	    
	    //若搜索好友
	    else if(sType == 1){
	   
	    //先输出头
	    out.println("<div><b>好友的搜索结果</b></div>");
	    
	    while(rsCurPage.next()){
		    out.println("<div class=\"newsContainer\"><img class=\"avatar\" src=\"" + avt.map.get(rsCurPage.getString(1)) +"\">");
		    out.println(" <b>");
		    out.println(si.showLink(rsCurPage.getString(1))+ "</b>");
		    out.println("</div><br>");
		}
		
		out.println("<form method=\"get\" action=\"search.jsp\">第"
	  	+ pageNum +"页  共" + pageCount + "页" );
	  	
	  	if(pageNum < pageCount){
	  		out.println("<a href=\"search.jsp?tPer="+
	  		vs.getParam() + "&page=" + (pageNum+1) +"\">下一页 </a>");
	  	}
	  	
	  	if(pageNum > 1){
	  		out.println("<a href=\"search.jsp?tPer="+
	  		vs.getParam() + "&page=" + (pageNum-1) +"\">上一页 </a>");
	  	}
	  	
	  	out.println("转到第 <input type=\"hidden\" name=\"tPer\" value=\"" + vs.getParam() +"\">"
	    + "<input class=\"formText2\" type=\"text\" name=\"page\" size=\"8\">页 "
	    + "<span><input class=\"normalSubmit\" type=\"submit\" value=\"转到\"></span></form>");
	    
	    
	  }
			    
	    
}
    %>
<% msc.close(); %>
    
  </div>
<%@include file="footer.jsp" %>
