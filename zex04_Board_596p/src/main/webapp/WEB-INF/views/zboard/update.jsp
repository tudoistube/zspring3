<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!-- 228p. -->
<%@include file="../zinclude/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				
<!-- 202p참조. form::action 속성이 지정되지 않으면 현재 경로를 그대로 action의 대상 경로로 잡음. -->
<!-- 228p. 수정작업에 필요한 모든 전송 데이터를 form태그로 감싸도록 선언함. -->
<form role="form" method="post">

	<div class="box-body">

		<div class="form-group">
			<label for="exampleInputEmail1">BNO</label> 
			<input type="text" 
					name='bno' class="form-control" value="${boardVO.bno}"
					readonly="readonly">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> 
			<input type="text"
				name='title' class="form-control" value="${boardVO.title}">
		</div>
		
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> 
			<input	type="text" 
					name="writer" class="form-control" value="${boardVO.writer}">
		</div>
	</div>
	<!-- /.box-body -->

</form>

<div class="box-footer">
	<button type="submit" class="btn btn-primary">SAVE</button>
	<button type="submit" class="btn btn-warning">CANCEL</button>
</div>	
	

<!-- 
	...228p.$("form[role='form']")로 선언된 formObj는 <form>태그를 의미함.
	...$(".btn-warning")이벤트 처리는 수정할 수 있는 페이지로 이동하도록 <form>태그 속성을
	...수정하고 전송하게 함.
	...Tomcat에서 프로젝트실행경로를 199p.처럼 /z/로 한 경우, 페이지에서는 http://localhost:8080
	...다음에 오는 완전한 경로를 명시해서 보내야 처리가 됨.
	...ex)http://localhost:8080/z/zboard/read?bno=3
	...컨트롤러입장에서는 http://localhost:8080/z 다음에 오는 완전한 요청경로를 대상으로 처리함. 
 -->
<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/z/zboard/listAll";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});

	});
</script>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../zinclude/footer.jsp"%>