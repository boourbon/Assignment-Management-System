<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="modal fade" id="dlg-assn">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Create Assignments</h4>
            </div>
            <div id="dlgOption-body" class="modal-body">

              		<input class="m-input title" type="text" style="width: 100%;margin: 10px 0px;" placeholder='Title'/>
               		
               		<textarea class="m-input descr" style="width: 100%;height: 100px" placeholder='Problem Description'></textarea>
               		                
            </div>
            <div class="modal-footer" style="text-align: right;">
                <button type="button" class="btn btn-default" onclick="DLG_ASSN.ok()"> Create </button>
            </div>
        </div>
    </div>
</div>

<script>
	var DLG_ASSN = {};
	DLG_ASSN.dlg = $("#dlg-assn");

	DLG_ASSN.show = function( )
	{
		var p = this.dlg;
		$(".title", p).html("");
		$(".descr", p).html("");
		
		this.dlg.modal('show');
	};
	
	DLG_ASSN.hide = function()
	{
		this.dlg.modal('hide');
	};
	
	DLG_ASSN.ok = function()
	{
		var info = {};
		info.title =  $(".title", this.dlg).val();
		info.descr =  $(".descr", this.dlg).val();
		if(this.callback != null)
		{
			this.callback (info);
		}
	};
	
</script>


