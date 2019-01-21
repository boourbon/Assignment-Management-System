<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form style='display:none;'>
   <input type='file' id='simple-file-upload' onchange='SIMPLE_UPLOAD.on_file_selected(this)'/>
</form>

<script>

var SIMPLE_UPLOAD = 
{	
	listener : {
	
		uploadUrl : "CommonFileUpload",
	
		check : function ( file ) { return true; } ,

		ready: function ( file ) { },
		
		progress: function( context, percent) { },
		
		error : function(context, error){ },
		
		complete: function ( message ) {  },
		
		end_of_class: null	
	},
	
	user_select_file : function()
	{
		$("#simple-file-upload").click();
	},
	
	on_file_selected : function ( filebutton )
	{
		var files = filebutton.files;	
		if(files == null) return;
		this.start_upload ( files[0]);
		
		$(filebutton).val("");
	},
	
	start_upload : function( file )
	{		
		if( ! this.listener.check (file)) return;
		this.listener.ready ( file );
		
		Af.trace("Start uploading: " + file.name);
		var context = {};
	    context.file = file;  
	    context.listener = this.listener;
	    
	   	var vFD = new FormData();
		vFD.append('fileupload', file); 
		
	    var oXHR = new XMLHttpRequest();
	    oXHR.context = context;
	    oXHR.upload.context = context;
	    oXHR.upload.addEventListener("progress", this.evt_upload_progress, false);
	    oXHR.addEventListener("load", this.evt_upload_complete, false);
	    oXHR.addEventListener("error", this.evt_upload_failed, false);
	    oXHR.addEventListener("abort", this.evt_upload_cancel, false);
	
		context.vFD = vFD;
	    context.oXHR = oXHR;
	    
	    oXHR.open("POST", context.listener.uploadUrl );
	    oXHR.send(vFD);
	},
	
	evt_upload_progress : function (evt) 
	{
	    if (evt.lengthComputable)
	    {
	    	var percent = Math.round(evt.loaded * 100 / evt.total);
	    	Af.trace ("Upload Progress: " + percent);
	    	this.context.listener.progress ( this.context, percent);
	    }	        
	    else 
	    {
	    	Af.trace("Display of upload progress is not supported！");
	    }
	},
	
	evt_upload_complete : function (evt)
	{
		Af.trace (evt.target.responseText); 
		/*
	    var ans = JSON.parse(evt.target.responseText);	    
	    if(ans.error == 0)
	    	this.context.listener.complete ( ans.result);
	    else
	    	this.context.listener.error (this.context, ans.reason);
	    */
	   this.context.listener.complete ( evt.target.responseText );
	},
 
	evt_upload_failed : function (evt) 
	{
		this.context.listener.error (this.context, "Upload Failed!");	
	},
	
	evt_upload_cancel : function (evt) 
	{
		this.context.listener.error (this.context, "Upload Terminated!");	
	},
	
	end_of_class: null
};

	
</script>


