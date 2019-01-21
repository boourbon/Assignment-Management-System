<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Teacher's Main Page </title>
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
		
		.courses 
		{
			display: inline-block;		
		}
		
		.courses  .item
		{
			display: inline-block;
			background-color: #ddd;
			color: #2292DD;
			padding: 4px 10px;
			border: 1px solid #ccc;
			cursor: default;
		}

		.courses  .item-selected
		{
			background-color: #fff;
		}
		
		.buttons 
		{
			display: inline-block;	
			margin-left: 100px;	
		}
		
		.buttons .m-button
		{
			background-color: #FFF;
			border: 0px solid #CCC;
			border-radius: 2px;
			padding: 4px;
		}
		
		.buttons .m-button:hover
		{
			color: blue;
		}
		
		.assignments
		{
			border: 1px solid #ccc;
			border-radius: 2px;
			min-height: 300px;
			padding: 10px;
		}
		.assignments .item
		{
			margin: 2px;
			border : 1px solid #ccc;
			border-radius: 4px;
			padding: 4px;
			cursor: pointer;
		}
		.assignments .item:hover
		{
			background-color: #add;
			color: #fff;
		}
		
	</style>
  </head>
  
  <body>
  
  	<jsp:include page='header.jsp' />
  	
    <div id='main-panel'>
    
      <div class='courses'></div>
      
      <div class='buttons'>
      </div>
      
    	<div class='exercise-list'>
    		<table class='table'>
    			<thead>
    				<tr>
    					<th> Question </th>
    					<th> Date </th>
    					<th> Status </th>
    					<th> Score </th>
    				</tr>
    			</thead>
    			<tbody>
    				
    			</tbody>
    		</table>    	
    	</div>    	
    	
    </div>    

  </body>
  
  <script>
  	
  	
  	var COURSES = {};
  	COURSES.panel = $("#main-panel .courses");
  	COURSES.course = 0;
  	
  	COURSES.load = function()
  	{
  		var req = {};
  		Af.rest ("CourseList.api", req, function(ans){
  			COURSES.show_item_list ( ans.result );
  		});
  	}

  	COURSES.show_item_list = function(items)
  	{
  		var target = this.panel;
  		for (var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<div class='item' id1='##1' onclick='COURSES.clicked(this)'>"
  				+ it.title 
  				+ "</div>";
  			str = str.replace(/##1/g, it.id);
  			target.append (str);
  		}
  		
  		$(".item", this.panel)[0].click();
  		
  	}

  	COURSES.clicked = function ( dom )
  	{
  		var id = $(dom).attr("id1");
  		
  		$(dom).siblings().removeClass("item-selected");
  		$(dom).addClass("item-selected");
  		
  		this.course = id;
  		EXER.load( id );
  	}
  	
  	////////////////// assignment //////////////////
  	var EXER = {};
  	EXER.panel = $(".assignments");
  	
  	EXER.load = function( course )
  	{
  		var req = {};
  		req.course = course;
  		req.student = USER.id;
  		Af.rest ("ExerciseList.api", req, function(ans){
  			if( ans.errorCode != 0) {  toastr.error(ans.reason); return; }
  			
  			EXER.show_item_list ( ans.result );
  		});
  	}
  	
  	EXER.show_item_list = function(items)
  	{
  		var target = $(".exercise-list tbody");
  		target.html("");
  		
  		for (var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' id2='##2' onclick='EXER.clicked(this)'>"
  				+ "<td>" + it.title + "</td>"
  				+ "<td>" + it.timeCreated.substr(0,11) + "</td>"
  				+ "<td>" + MAIN.status( it.status ) + "</td>"
  				+ "<td>" + it.score + "</td>"  								
  				+ "</tr>";
  			str = str.replace(/##1/g, it.id).replace(/##2/g, it.assignment);
  			target.append (str);
  		}  	
  	}
  	
  	EXER.clicked = function ( dom )
  	{
  		var exercise = $(dom).attr("id1");
  		var assignment = $(dom).attr("id2");
  		location.href = "student/ExerciseView.jsp?exercise=" + exercise + "&assignment=" + assignment;
  	}
  	
  	///////////////////////////////////////////////
  	
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
  	
  	MAIN.status = function ( s )
  	{
  		if(s==0) return "-";
  		if(s==1) return "Submitted";
  		if(s==100) return "OK"  		
  	}
  	
  	MAIN.panel.ready ( function(){
 		
 		COURSES.load();
  	});
  </script>
</html>
