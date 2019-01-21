<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Student View: View and Submit Homework </title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<jsp:include page="../include.jsp" />
	
	<style>
	
		#main-panel
		{
			width: 80%;
			margin: 10px auto auto auto;
			border: 0px solid #CCC;
			border-radius: 4px;
			padding: 8px;
			min-height: 360px;
		}
		
	
	</style>
  </head>
  
  <body>
  
  	<jsp:include page='header.jsp' />
  	
    <div id='main-panel'>
    
    	<div class='assignment'>
    		<label class='title'> </label>
    		<div class='descr'> </div>
    	</div>
    	
    	<hr>
    	
    	<button class='m-button upload-button' onclick='MAIN.upload()'> Upload Homework File </button>
    	<label class='filename'> </label>
    	<div class='progressbar'> <div class='percent'></div></div>
     
    </div>
    
  <jsp:include page="simple_upload.jsp" />
  
  </body>
  
  <script>
  	
  	
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
  	MAIN.assignment = <%= my.jsp.JspUtil.getAssignment(request) %>;
  	MAIN.exercise = <%= my.jsp.Jsp.getInt(request, "exercise", 0) %>;
  	MAIN.exerciseObj = <%= my.jsp.JspUtil.getExercise(request) %>;
  	
  	MAIN.show_assignment = function( it )
  	{
  		var p = $(".assignment");
  		$(".title", p).html ( it.title );
  		$(".descr", p).html ( it.descr );
  		
  		if(this.exerciseObj.status != 0 )
  		{
  			var storePath = this.exerciseObj.storePath;
  			var str = "<a href='" + storePath + "'> View </a>";
			$(".progressbar").html ( str );
  		}
  	}
  	
  	MAIN.upload = function()
  	{
  		this.progress = $(".percent");
  		
  		SIMPLE_UPLOAD.listener = {
  		
			uploadUrl : "CommonFileUpload?type=exercise&id=" + MAIN.exercise,
		
			check : function ( file ) { return true; } ,
			
			ready: function ( file ) {
				$(".filename").html ( file.name );
			},
			
			progress: function( context, percent) {
				MAIN.progress.html ( percent );
			},
			
			error : function(context, error){ },
			
			complete: function ( message ) {  
				MAIN.progress.html ( "Complete!" );
				var ans = JSON.parse(message);
				if(ans.error == 0)
				{
					var storePath = ans.result.storePath;
					var str = "<a href='" + storePath + "'> View </a>";
					$(".progressbar").html ( str );
				}
			},
			
			end_of_class: null
  		}
  		
  		SIMPLE_UPLOAD.user_select_file();
  	}
  	
  	MAIN.panel.ready ( function(){
 		MAIN.show_assignment( MAIN.assignment );
 		
 		
  	});
  </script>
</html>
