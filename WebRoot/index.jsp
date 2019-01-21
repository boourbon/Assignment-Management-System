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
	
	<jsp:include page="include.jsp" />
	
	<style>
	    body
	    {
	        background-color: rgba(255,255,255);
	    } 
	    
		#head
		{
			text-align: center;
			padding: 15px;
			font-size: 20px;
		}
		#head a
		{
			border: 2px solid #4F7CA2;
			padding: 4px;
			margin:3px;
			background-color:#4F7CA2;
			color: #D3E9F8;
		}
		
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
		
		#main-panel  .item
		{
			display: inline-block;
			width: 20%;
			margin: 25px;
			height: 100px;
			background-color: #DDD;
			border-radius: 4px;
			padding: 4px;
			text-align: center;
		}
		
		
	</style>
  </head>
  
  <body>
  	<div id='head'>
  		<a href='teacher'> I am a Teacher </a>
  		<a href='student'> I am a Student </a>
  		
  	</div>
    <div id='main-panel'>
      No courses in database.
    </div>
    
  </body>
  
  <script>
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
  	
  	MAIN.load = function()
  	{
  		var req = {};
  		Af.rest("CourseList.api", req, function(ans){
			  			
  			// Af.trace ( ans );
  			MAIN.show_item_list( ans.result);
  		});
  	}
  
  	MAIN.show_item_list = function ( items )
  	{
  		var target = this.panel;
  		target.html ("");
  		
  		for (var i =0; i<items.length;i++)
  		{
  			var it = items[i];
  			
  			var str = "<div class='item'>"
  				+ it.title 
  				+ "</div>"
  				;
  			target.append ( str );
  		}
  	}
  	
  	MAIN.panel.ready ( function(){
  		
  		MAIN.load();
  		
  	});
  </script>
</html>
