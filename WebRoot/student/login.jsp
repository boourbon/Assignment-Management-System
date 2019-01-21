<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<jsp:include page="../include.jsp" />
	
	<style>
		
		#main-panel
		{
			width: 80%;
			margin: 10px auto auto auto;
			border: 1px solid #CCC;
			border-radius: 4px;
			padding: 8px;
			min-height: 360px;
			background-color: #4F7CA2;
		}	
		
		.m-input
		{
			margin-left: 40%;
			margin-top: 10px;
			padding: 2px;
			border: 1px solid #ccc;
		}
		.m-button
		{
		    margin-left: 40%;
		    margin-top: 20px;
			padding: 2px 10px;
			border: 1px solid #ccc;
			background-color: #FFF;
		}	
			
	</style>
  </head>
  
  <body>

    <div id='main-panel'>
    
      <div id='login'>
      	<input class='m-input username' placeholder='Student ID' />  <br>
      	<input type='password' class='m-input password' placeholder='Password' /> <br>
      	<button class='m-button' onclick="MAIN.login()" > Log In </button>
      	<label class='note'> </label>
     </div>
    
  </body>
  
  <script>
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
 
  	MAIN.login = function()
  	{
  		var req = {};
  		req.id = $(".username").val(); // value
  		req.password = $(".password").val();
  		
  		Af.rest ("StudentLogin.api", req, function(ans){  		
  			// Af.trace( ans );
  			if( ans.errorCode != 0)
  			{
  				$(".note").html ( ans.reason );
  				return;
  			}
  			
  			location.href= "student/index.jsp";
  		});  		
  	}
  	
  	MAIN.panel.ready ( function(){
  		
  	});
  </script>
</html>
