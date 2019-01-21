<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Teacher's View: Assignment List  </title>
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
    	
    	<div class='exercise-list'>
    		<table class='table'>
    			<thead>
    				<tr>
    					<th> ID </th>
    					<th> Name </th>
    					<th> Status </th>
    					<th> Grade </th>
    					<th> View </th>
    					<th> Adjust </th>
    				</tr>
    			</thead>
    			<tbody>
    				
    			</tbody>
    		</table>    	
    	</div>
     
    </div>
    
<jsp:include page = "dlg_score.jsp" />

  </body>
  
  <script>
  	
  	
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
  	MAIN.assignment = <%= my.jsp.JspUtil.getAssignment(request) %>;
  	
  	MAIN.show_assignment = function( it )
  	{
  		var p = $(".assignment");
  		$(".title", p).html ( it.title );
  		$(".descr", p).html ( it.descr );
  	}
  	
  	MAIN.load = function()
  	{
  		var req = {};
  		req.assignment = this.assignment.id;
  		Af.rest ("ExerciseList.api", req, function(ans){
  			MAIN.show_item_list (ans.result);
  		});
  	}

  	MAIN.show_item_list = function( items )
  	{
  		var target = $(".exercise-list tbody");
  		
  		for (var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var download = "<a href='" + it.storePath + "'>View </a>";
  			
  			var str = "<tr class='item' id1='##1' >"
  				+ "<td>" + it.student + "</td>"
  				+ "<td>" + it.studentName + "</td>"
  				+ "<td>" + MAIN.status( it.status ) + "</td>"
  				+ "<td>" + it.score + "</td>"
  				+ "<td>" + ( it.status == 0 ? "-" : download ) + "</td>"
  				+ "<td>" + "<button id1='##1' onclick='MAIN.set_score(this)'> Grade </button>" + "</td>"  				
  				+ "</tr>";
  			str = str.replace(/##1/g, it.id);
  			target.append (str);
  		}
  	}
  	
  	MAIN.status = function ( s )
  	{
  		if(s==0) return "-";
  		if(s==1) return "Submitted";
  		if(s==100) return "OK"  		
  	}
  	
  	MAIN.set_score = function ( e )
  	{
  		var id = $(e).attr("id1");
  		DLG_SCORE.show( id );  		
  	}
  	
  	MAIN.panel.ready ( function(){
 		MAIN.show_assignment( MAIN.assignment );
 		
 		MAIN.load ();
  	});
  </script>
</html>
