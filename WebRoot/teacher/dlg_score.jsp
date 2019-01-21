<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="modal fade" id="dlg-score">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Set Score</h4>
            </div>
            <div id="dlgOption-body" class="modal-body">

              	<input class="m-input score" type="text" style="width: 100%;margin: 10px 0px;" />	
               		                
            </div>
            <div class="modal-footer" style="text-align: right;">
                <button type="button" class="btn btn-default" onclick="DLG_SCORE.ok()"> Confirm </button>
            </div>
        </div>
    </div>
</div>

<script>
	var DLG_SCORE =
	{
		dlg: $("#dlg-score"),
		
		exercise : 0,
		
		show : function( exercise )
		{
			this.exercise = exercise;
			$(".score", this.dlg).html("");
		
			this.dlg.modal('show');
		},
	
		hide : function()
		{
			this.dlg.modal('hide');
		},
		
		ok : function()
		{
			var req = {};
			req.exercise = this.exercise;
			req.score =  $(".score", this.dlg).val();
			
			Af.rest ("ExerciseSetScore.api", req, function(ans){
			
				DLG_SCORE.hide();
			
				if(ans.errorCode != 0) { toastr.error(ans.reason); return; }
				
				
			});			
		},
		
		end_of_class: null
	};
	
	
</script>


