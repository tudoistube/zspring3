<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 
	...602p.
	...일반파일의 경우 컨트롤러에서 자동으로 MIME타입을 다운로드함.
	...원본 이미지의 경우, 현재 화면이 전환되면서 이미지가 보여지므로 별도 이벤트 처리가 필요함.
	...원본이미지의 경우 CSS를 이용해서 화면 맨 앞쪽으로 보여지게 처리함.
	...이미지파일명을 클릭하면 큰 크기로 보여줌.
-->	
<script>
$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

	//console.log(file);
	
	var formData = new FormData();
	
	formData.append("file", file);	
	
	$.ajax({
		  url: '/z4b/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  
			  var html = template(fileInfo);
			  
			  $(".uploadedList").append(html);
		  }
		});	
});

</script>

