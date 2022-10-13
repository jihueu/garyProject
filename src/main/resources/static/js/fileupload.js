/**
 * 
 */
 
 $(function(){
	$(".fileInput").change(fileUpload);
	
});

function fileUpload(){
	
	target = $(this); //var 생략가능
	file=$(this)[0].files[0];
	console.log(file);
	fileName=file.name;
	if(!file.type.includes("image/")){
		alert("이미지파일이 아닙니다.");
		return;
	}
	if(file.size> (1024*1024*5) ){
		alert("이미지 용량은 1MB 까지만 적용하세요");
		return;}
	data=new FormData();
	data.append("file", file);
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			beforeSend:function(xhr){
			xhr.setRequestHeader(header, token);
		},////csrf 적용한경우..아니면 제거
		data: data,
		type: "POST",
		url: "/admin/goods/fileupload",
		contentType: false,
		//enctype: 'multipart/form-data',
		processData: false,
		success: function(fileUrl) {
			
			target.next().css("background-image", "url("+fileUrl+")" );
			//style="background-image: url('');"
			target.siblings("[type=hidden]").val(fileName);
		},
		error: function(err){
			alert("파일업로드 오류 서버의 용량이나 경로를 확인하세요");
			//console.log(err);
		}
		});
}


