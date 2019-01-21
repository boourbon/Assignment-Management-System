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
			color: #2292DD;
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
			background-color: #4F7CA2;
			color: #fff;
		}
		
	</style>
  </head>
  
  <body>
  
  	<jsp:include page='header.jsp' />
  	
    <div id='main-panel'>
    
      <div class='courses'></div>
      
      <div class='buttons'>
      	<button class='m-button' onclick='DLG_ASSN.show()'> 
      		<img src='images/add.png' style='width: 22px'> Create Assignments
      	</button>
      </div>
      
      <div class='assignments'>
      	
      </div>
    </div>
    
	<jsp:include page='dlg_assn.jsp' />

  </body>
  
  <script>
  	
  	
  	var COURSES = {};
  	COURSES.panel = $("#main-panel .courses");
  	COURSES.course = 0;
  	
  	COURSES.load = function()
  	{
  		var req = {};
  		req.teacher = USER.id;
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
  		ASSN.load( id );
  	}
  	
  	////////////////// assignment //////////////////
  	var ASSN = {};
  	ASSN.panel = $(".assignments");
  	
  	DLG_ASSN.callback = function(info)
  	{
  		DLG_ASSN.hide();
  		
  		var course = $(".courses .item-selected").attr("id1");
  		
  		var req = {};
  		req.course = course;
  		req.title = info.title;
  		req.descr = info.descr;
  		Af.rest ("AssignmentSave.api", req, function(ans){
  			Af.trace( ans );
  			
  			//var it = ans.result;
  			//ASSN.append ( it );
  			
  			ASSN.load( COURSES.course );
  		});
  	}
  	
  	ASSN.load = function( course )
  	{
  		var req = {};
  		req.course = course;
  		Af.rest ("AssignmentList.api", req, function(ans){
  			if( ans.errorCode != 0) {  toastr.error(ans.reason); return; }
  			
  			ASSN.show_item_list ( ans.result );
  		});
  	}
  	
  	ASSN.show_item_list = function(items)
  	{
   		var target = this.panel;
   		target.html("");
   		
  		for (var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<div class='item' id1='##1' onclick='ASSN.clicked(this)'>"
  				+ it.title 
  				+ "<label style='float:right'>" + it.timeCreated.substr(0,16) + "</label>"
  				+ "</div>";
  			str = str.replace(/##1/g, it.id);
  			target.append (str);
  		}  	
  	}
  	ASSN.append = function ( it )
  	{
  		
  	}
  	
  	ASSN.clicked = function ( dom )
  	{
  		var id = $(dom).attr("id1");
  		location.href = "teacher/ExerciseList.jsp?assignment=" + id;
  	}
  	///////////////////////////////////////////////
  	
  	var MAIN = {};
  	MAIN.panel = $("#main-panel");
  	
  	MAIN.panel.ready ( function(){
 		
 		COURSES.load();
  	});
  </script>
</html>
