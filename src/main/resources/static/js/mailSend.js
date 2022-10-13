/**
 * 
 */
 //var mailkey="";

var myTimeout;
$(function() {
	$("#key").hide();
	$("#btn-mail").click(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
			url: "/request-key/mail",
			type: "post",
			data: { email: $("#domain-txt").val() },
			success: function(result) {
				$("#key").show();
				start(result + (1000*60*3));
			}
		});
	});



	$("#key").blur(function() {
		//var scode=[[${session.mailKey}]]; 페이지 로딩시 결정되는 세션이라 이렇게 받으면 안됨
		//name이 생략된 get요청 ajax
		var inputKey = $(this).val();
		//console.log("입력한code : "+inputKey);

		$.get("/request-key/getkey", function(code) {
				console.log("code : "+ code)
			if (code == inputKey) {
				$(".mail-msg").text("인증성공").css("color", "green");
				$("#key").hide();
				clearTimeout(myTimeout);
				$.get("/request-key/remove", function(){});
			
			} else {
				$(".mail-msg").text("인증실패").css("color", "red");

			}

		});
	});
});
	function start(targetTime){
		var seconds=(targetTime-new Date().getTime())/1000;
		var minute=Math.floor(seconds/60);
		var second=Math.floor(seconds%60);
		
		console.log(minute +":"+second);
		//console.log("차이:"+(targetTime-currTime));//300,000
			if(seconds>1){
				$(".time").text(minute +":"+second);
				myTimeout = setTimeout(start, 1000,targetTime);
				
			}else{
				clearTimeout(myTimeout);
				$(".time").text("00:00");
				$.get("/request-key/remove",function(){});
			}
		
	}

