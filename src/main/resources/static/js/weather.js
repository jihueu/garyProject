/**
 * 
 */
 
 $(function(){
	var apiURI ="https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=2481c6cb0a4e9fa36603b0d21e74f9e3"
	
	$.ajax({
		url:apiURI,
		success:function(result){
			console.log("절대온도:"+result.main.temp);
			console.log("섭씨온도:"+parseInt(result.main.temp-273.15));
			console.log("날씨아이콘이름:"+result.weather[0].icon);
			var iconURL="https://openweathermap.org/img/w/"+result.weather[0].icon+".png";
			$("#wIcon").css("background-image","url("+iconURL+")");
			$("#temp").text(parseInt(result.main.temp-273.15));
		}
	});

});