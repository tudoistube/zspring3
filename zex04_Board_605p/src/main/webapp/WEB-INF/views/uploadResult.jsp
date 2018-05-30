<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	...533p
	...uploadResult.jsp는 iframe내에서 동작하기 위해 작성된 페이지임.
	...특이하게도 화면을 구성하는 부분 없이 script를 사용해서 자신이 속한
	...화면의 바깥쪽 parent의 addFilePath()를 호출함. 
-->    
<%@page session="false"%>    

<script>

var result = '${savedName}';

parent.addFilePath(result);

</script>