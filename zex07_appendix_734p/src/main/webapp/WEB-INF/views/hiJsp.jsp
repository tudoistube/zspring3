<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>hiJsp from Springboot...</title>
</head>
<body>

	<h1>hiJsp from Springboot...</h2>
	<h2>725p.message : ${msg}</h2>
	
	<h2>726p.한글처리.</h2>
	<form method="post">
		<p>UID : <input type="text" name="uid"></p>
		<p>UPW : <input type="password" name="upw"></p>
		<p>UNAME : <input type="text" name="uname"></p>
		
		<p><input type="submit"></p>
	</form>

</body>
</html>