<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 299p. 228p참조. -->
<%@include file="../zinclude/header.jsp"%>


<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<!-- /.box-header -->

<form role="form" action="updatePage" method="post">

	<!-- 299p. 페이징 처리에 대한 정보를 유지하도록 hidden 사용. -->
	<input type='hidden' name='page' value="${cri.page}"> 
	<input type='hidden' name='perPageNum' value="${cri.perPageNum}">

	<!--  338p. 검색 처리에 대한 정보를 유지하도록 hidden 사용. -->
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

	<div class="box-body">

		<div class="form-group">
			<label for="exampleInputEmail1">BNO</label> <input type="text"
				name='bno' class="form-control" value="${boardVO.bno}"
				readonly="readonly">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				name='title' class="form-control" value="${boardVO.title}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> <input
				type="text" name="writer" class="form-control"
				value="${boardVO.writer}">
		</div>
	</div>
	<!-- /.box-body -->
</form>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary">SAVE</button>
					<button type="submit" class="btn btn-warning">CANCEL</button>
				</div>

<script>
	$(document).ready(
			function() {

				var formObj = $("form[role='form']");

				console.log(formObj);
				//...338p. 취소버튼을 클릭하면 원래 목록 페이지로 이동하게 함.
				$(".btn-warning")
						.on("click",
							function() {
								self.location = "/z3/zsboard/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}"
												+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
							});

				$(".btn-primary").on("click",
									function() {
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
