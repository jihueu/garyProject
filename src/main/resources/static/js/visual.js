/**
 * 
 */
 
$(function(){
	$.ajax({
		url:"/common/visuals",
		success:function(result){
			$("#visual").html(result);
		}
	});
});