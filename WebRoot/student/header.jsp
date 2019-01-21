
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 

<style>
	#header
	{
		width: 100%;		
		background-color: #4F7CA2;
		border-bottom: 1px solid #475D6F;
	}
	#header .center
	{
		width: 80%;
		margin: 0px auto auto auto;
	}
	#header .logo
	{
		font-size: 18px;
		color: #ddd;
	}
	#header  .userinfo
	{
		float: right;
		color: #ddd;
	}
</style>

<div id='header'>
	<div class='center'>
	
		<label class='logo'> Assignment Management System </label>
		
	   <div class='userinfo'>
	        Not logged in
	   </div>
	</div>
   
</div>

<script>
	var ROLE = <%= my.jsp.JspSession.getString(request, "role", "") %>;
	var USER = <%= my.jsp.JspUtil.currentUser(request) %>;
	
	if("student" != ROLE)
	{
		location.href = "student/login.jsp";
	}
	else
	{
		$(".userinfo").html( USER.displayName );
	}
	
	
</script>
