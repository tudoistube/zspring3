<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	...293p.
 --%>
<%@include file="../zinclude/header.jsp" %>

<%--
	...290p.사용자가 조회페이지에서 다시 '목록 보기'를 이용해서 기존에 자신이 보던 목록 페이지로 
	...전환되야 함.
	...예) 사용자가 7페이지를 보던 중 자신이 원하는 게시물을 선택해서 조회 이후에 목록 페이지로
	...    링크를 클릭하면 7페이지의 데이터를 다시 볼 수 있어야만 함.
	...가끔 Javascript의 history.go(-1)과 같은 방식으로 처리하는 경우도 있지만, 게시물 리스트
	...자체가 프레임이나 iframe태그 내부에 들어있는 경우에는 사용할 수 없기 때문에, 직접 링크에
	...관련된 모든 정보를 처리하도록 함.
	...293p.read.jsp를 복사해서 사용함.
 --%>
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

<form role="form" method="post">

	<input type='hidden' name='bno' value="${boardVO.bno}">
	bno = ${boardVO.bno}	
<%--
	...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
	  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
	  public void read(@RequestParam("bno") int bno, 
			  		   @ModelAttribute("cri") Criteria cri, 
			  		   Model model)	
 --%>	
    <input type='hidden' name='page' value ="${cri.page}">
    page = ${cri.page}
    <input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
    perPageNum = ${cri.perPageNum}
    
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">Title</label> <input type="text"
			name='title' class="form-control" value="${boardVO.title}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Content</label>
		<textarea class="form-control" name="content" rows="3"
			readonly="readonly">${boardVO.content}</textarea>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">Writer</label> <input type="text"
			name="writer" class="form-control" value="${boardVO.writer}"
			readonly="readonly">
	</div>
</div>
<!-- /.box-body -->

<div class="box-footer">
	<button type="submit" class="btn btn-warning">Modify</button>
	<button type="submit" class="btn btn-danger">Delete</button>
	<button type="submit" class="btn btn-primary">Go LIST</button>
</div>


<script>
				
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".btn-warning").on("click", function(){
		//formObj.attr("action", "/z/zboard/update"); //...read.jsp, 298p.주석.
		//...수정화면으로 이동(get).
		formObj.attr("action", "/z/zboard/updatePage");
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$(".btn-danger").on("click", function(){
		alert("Delete Button clicked...");
		//formObj.attr("action", "/x/board/delete"); //...read.jsp, 298p.주석.
		formObj.attr("action", "/z/zboard/deletePage");
		formObj.submit();
	});
	
	$(".btn-primary").on("click", function(){
		//self.location = "/z/zboard/listAll"; //...read.jsp, 294p 주석.
		
		//...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
		formObj.attr("method", "get");
		formObj.attr("action", "/z/zboard/listPage");
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

    
<%@include file="../zinclude/footer.jsp" %>
      