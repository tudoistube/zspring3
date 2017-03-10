<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
	...625p.
	...기존의 home.jsp는 page session="true"로 처리되어 HttpSession을
	...이용할 수 없으므로 home2.jsp에서 제거한 또는 true로 변경 후 사용함.
	...브라우저에 http://localhost:8080/z5/doB를 호출하면
	...http://localhost:8080/z5/doB로 이동하는데, HttpSession에 'result'이름으로
	...보관된 객체가 있으므로, 화면에는 'result'에 해당하는 문자열이 보여짐.
	
	...home2.jsp에서는 Model객체에서 전달된 객체는 없지만, HttpSession객체에
	...필요한 정보가 보관되었기 때문에 EL문법을 이용해서 화면에 문자열이 보여짐.
	...이를 이용하여, 컨트롤러에서는 로그인 처리 후의 결과를 반환하고,
	...인터셉터를 이용해서 HttpSession에 로그인이 필요한 객체를 보관하는 형태로
	...작성하면 컨트롤러에서 직접 HttpSession등의 API를 사용하지 않는 코드를
	...만들 수 있음.	
-->
<%--
<%@ page session="false"%>
--%>


<html>
<head>
	<title>Home</title>
</head>

<body>
<h1>
	SampleInterceptor를 이용한 home2 : home2.jsp  
</h1>


<h2>${result}</h2>

</body>
</html>
