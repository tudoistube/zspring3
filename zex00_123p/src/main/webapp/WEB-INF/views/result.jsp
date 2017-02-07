<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>from SampleController2</title>
</head>
<body>

 <span>...109p.zweb.Hello ${msg}</span>
 <span>${productVO.name }</span>
 <span>${productVO.price }</span> 

<script>    
    var result = '${msg}';
    
    if(result != ''){
    	alert("처리가 완료되었습니다.");
    }    
</script>

</body>
</html>