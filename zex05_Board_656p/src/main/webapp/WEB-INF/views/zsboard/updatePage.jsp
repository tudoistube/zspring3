<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 299p. 228p참조. -->
<%@include file="../zinclude/header.jsp"%>

<!-- 
	...602p.
	...일반파일의 경우 컨트롤러에서 자동으로 MIME타입을 다운로드함.
	...원본 이미지의 경우, 현재 화면이 전환되면서 이미지가 보여지므로 별도 이벤트 처리가 필요함.
	...원본이미지의 경우 CSS를 이용해서 화면 맨 앞쪽으로 보여지게 처리함.
	...이미지파일명을 클릭하면 큰 크기로 보여줌.
-->
<style type="text/css">
.popup {position: absolute;}
.back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
.front {z-index:1110; opacity:1; boarder:1px; margin: auto;}
.show{
		position:relative;
		max-width: 1200px; 
		max-height: 800px; 
		overflow: auto;       
	}   	
</style>

<div class='popup back' style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;">
	<img id="popup_img">
</div>



<!-- ...433p.Mustach를 기반으로 작성된 handlebars.js 이용. -->
<!-- 
	...591p.첨부파일을 보여주는 HTML코드가 복잡하므로 handlebars를 이용하여
	...첨부된 각각의 파일을 보여주는 화면을 템플릿으로 작성함.  
	...li태그를 구성할 때 'imgsrc'속성을 보여주는데, 'imgsrc'는 이미지 파일인 경우
	...썸네일 파일의 경로이고, 일반 파일인 경우는 단순히 파일모양의 이미지(file.png)를
	...보여주는 경로임.
-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- 
	...601p.getFileInfo()는 별도의 JavaScript 파일로 작성함.
	...$.getJSON("/z4b/zsboard/getAttach/"+bno,function(list){
	...getFileInfo()처리를 위한 링크추가.
 -->
<script type="text/javascript" src="../resources/zjs/upload.js"></script>



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
	
	<!-- 
		...601p.
		...조회페이지에서 기존에 업로드된 파일들이 보여지는 영역을 작성하고
		...upload.js와 handlebars를 설정함. 
	-->
	<ul class="mailbox-attachments clearfix uploadedList"></ul>

	<div class="box-footer">	
	
		<button type="submit" class="btn btn-primary">SAVE</button>
		<button type="submit" class="btn btn-warning">CANCEL</button>
	</div>	
	
</form>

<script>
	$(document).ready(
			function() {

				var formObj = $("form[role='form']");

				console.log(formObj);
				//...338p. 취소버튼을 클릭하면 원래 목록 페이지로 이동하게 함.
				$(".btn-warning")
						.on("click",
							function() {
								self.location = "/z4b/zsboard/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}"
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

<%@include file="../zinclude/delAttachedImg.jsp"%>

<%@include file="../zinclude/footer.jsp"%>
