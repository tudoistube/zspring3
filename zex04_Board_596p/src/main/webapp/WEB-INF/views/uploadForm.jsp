<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>...523p</title>
<!-- 
	...533p
	...업로드된 결과 역시 현재 창에서 바로 확인할 수 있도록 함.
	...form태그의 전송은 기본적으로 화면전환을 피할 수 없는데, 
	...form태그에 target속성을 주고 iframe을 이용하면 화면 전환효과를 없앨 수 있음.
	...form태그의 전송은 iframe을 이용하고, iframe내의 결과페이지는 다시 바깥쪽
	...parent의 Javascript를 호출함. 
 -->
<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px
}
</style>
</head>

<body>

	<!-- 
		...523p.
		...enctype="multipart/form-data"는 일반적인 form방식과 다른 multipart방식으로 데이터를 전달함을
		...의미함. 
		http://localhost:8080/z4b/uploadForm
	-->
	<!-- 
		...532p.주석처리.
	<form id='form1' action="uploadForm" method="post" enctype="multipart/form-data">
		<input type='file' name='file'> <input type='submit'>
	</form>
		...532p.form태그에 target속성을 줌.
	 -->	 
	<form id='form1' action="uploadForm" method="post"
			enctype="multipart/form-data" 
			target="zeroFrame">
		<input type='file' name='file'> <input type='submit'>
	</form>
	
	<iframe name="zeroFrame"></iframe>

	<!-- 
		...534p.
		...addFilePath()는 uploadResult.jsp에서 호출하는 부모창(parent)의 함수임.
		...iframe을 사용하는 방식은 숨겨진 페이지를 이용해서 form의 submit()을
		...실행하고, 숨겨진 페이지가 바깥쪽의 페이지 함수를 호출하는 형태임.
		...iframe의 width, height을 모두 '0'으로 지정하면 화면에 보이지 않으므로,
		...Ajax를 사용하지 않고도 화면이 전환되지 않는 효과를 볼 수 있는 장점이 있음.
	 -->	
	<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>
	 
	 
</body>
</html>

