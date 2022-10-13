/**
 * 
 */
 
 $(function(){
	
	$.get("/common/goods", function(listHtmlData){
			$("#goods-list>.wrap").html(listHtmlData);
			
		});
});