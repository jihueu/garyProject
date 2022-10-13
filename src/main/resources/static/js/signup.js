/**
 * 정규표현식 
 * 아이디 중복체크
 * 비밀번호 확인
 */
 
 	var check=[false,false,false,false];
	
	$(function() {
		$("#domain-txt").blur(idBlured);
		$("#pass").blur(passBlured);
		$("#pass_check").blur(checkBlured);
		$("#name").blur(nameBlured);
	});
	
	function submitCheck() {
		for (i = 0; i < check.length; i++) {
			if (check[i] == false) {
				$("#btn-join").attr("disabled", true);
				return;
			}
		}
		//모두 true인경우
		$("#btn-join").attr("disabled", false);
	}
	
	
	function idBlured() {
		var idRexp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var in_id = $(this).val();
		var text;
		var target = $(this).next();//클래스 이름이 msg인 p태그요
		if (idRexp.test(in_id.trim())) {
				alert("메일확인테스트"+in_id);
				var token = $("meta[name='_csrf']").attr("content");
	   			var header = $("meta[name='_csrf_header']").attr("content");				
			$.ajax({
				beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
				url:"/common/mailauth",
				data: {email:in_id},
				type:"post",
				success:function(result) {
					if (result) {
						text = "이미 사용중인 아이디입니다.";
						target.css("color", "red");
						target.text(text);
						check[3]=false;
						
						
					} else {
						text = "아이디사용가능";
						target.css("color", "green");
						target.text(text);
						check[3]=true;
					}
				}
			});
		} else {
			text = "아이디형식오류";
			target.css("color", "red");
			target.text(text);
			check[3]=false;
		}
		
		
		submitCheck();
		
	}
	
	
	
	function passBlured() {
		var passRexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$/;
		var in_pass = $.trim($(this).val());
		var text;
		var target = $(this).next();
		if (in_pass.length < 8 || in_pass.length > 15 ) {
			text = "8자리이상 15자리이하로 입력하세요";
			target.css("color", "red");
			target.html(text);
			check[2]=false;
			return;//함수 종료....
		}
		//8자리 이상인 경우 패턴 체크	
		if (passRexp.test(in_pass)) {
			text = "비밀번호입력완료";
			target.css("color", "green");
			check[2]=true;
		} else {
			text = "비밀번호형식오류";
			target.css("color", "red");
			check[2]=false;
		}
		target.html(text);
		submitCheck();
	
	}
	function checkBlured() {
		var in_pass = $("#pass").val();
		var target = $(this).next();
		var text;
		if ($(this).val() == in_pass) {
			text = "비밀번호확인완료";
			target.css("color", "green");
			check[1]=true;
		} else {
			text = "비밀번호를확인하세요";
			target.css("color", "red");
			check[1]=false;
		}
		target.html(text);
		submitCheck();
	}
	function nameBlured() {
		var nameRexp = /^[가-힣]{2,10}|[A-Za-z]{2,10}$/;
		var in_name = $.trim($(this).val());
		var text;
		var target = $(this).next();
		if (nameRexp.test(in_name)) {
			text = "이름입력완료";
			target.css("color", "green");
			check[0]=true;
		} else {
			text = "이름을다시입력하세요";
			target.css("color", "red");
			check[0]=false;
		}
		target.html(text);
		submitCheck();
	
	}