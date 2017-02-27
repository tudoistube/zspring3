<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../zinclude/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				<div class='box-body'>
					<button id='newBtn'>New Board</button>
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>

						<c:forEach items="${list}" var="boardVO">

							<tr>
								<td>${boardVO.bno}</td>
								<!-- 
								...216p.276p.↓.
								...http://localhost:8080/x/board/listPage?page=256
								<td><a href='/z/zboard/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
								...286p.↓.페이지정보를 유지하게 함.
								...http://localhost:8080/z/zboard/listPage?page=19&perPageNum=10로 호출가능.
								 -->
								<td><a href='/z/zboard/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}'>
										${boardVO.title}
									</a>
								</td>
								<td>${boardVO.writer}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${boardVO.regdate}" /></td>
								<td><span class="badge bg-red">${boardVO.view_count }</span></td>
							</tr>

						</c:forEach>

					</table>
				</div>				
				<!-- /.box-body -->


				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li>
								<!-- 
									...276p.↓.
									<a href="/z/zboard/listPage?page=250">&laquo;</a>
									<a href="/z/zboard/listPage?page=${pageMaker.startPage - 1}">&laquo;</a>
									
									...288p.↓.페이지정보를 유지하게 함.
									...★'?page='가 필요없음에 주의할 것.
									<a href="/z/zboard/listPage?page=?page=250&perPageNum=10">&laquo;</a>									
									<a href="/z/zboard/listPage?page=${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
								 -->
									<a href="/z/zboard/listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
									${pageMaker.makeQuery(pageMaker.startPage - 1)}
								</li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
									   end="${pageMaker.endPage }" 
									   var="idx">
								<!-- 
									...277p.↓.li태그내의 서식을 동적으로 표현함.
									...현재 페이지번호는 PageMaker객체 내의 Criteria에 존재함.
									${pageMaker.cri.page}를 이용해서 getCriteria(), getPage()를 호출하여
									현재 페이지 번호만을 강조시킴.
								 -->									   
								<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
								<!-- 
									...277p.↓.
									<a href="/z/zboard/listPage?page=${idx}">${idx}</a>
									...287p.↓.
									<a href="/z/zboard/listPage?page=251&perPageNum=10">251</a>
									<a href="/z/zboard/listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								 -->
									<a href="/z/zboard/listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li>
								<!-- 
									...276p.↓.
									<a href="/z/zboard/listPage?page=250">&laquo;</a>
									<a href="/z/zboard/listPage?page=${pageMaker.endPage + 1}">&laquo;</a>
									
									...288p.↓.페이지정보를 유지하게 함.
									...★'?page='가 필요없음에 주의할 것.
									<a href="/z/zboard/listPage?page=31&perPageNum=10">&raquo;</a>
									<a href="/z/zboard/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
								 -->								
									<a href="/z/zboard/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
									endPage : ${pageMaker.makeQuery(pageMaker.endPage +1) }
								</li>
							</c:if>

						</ul>
					</div>

				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<form id="jobForm">
  <input type='hidden' name="page" value="${pageMaker.cri.page}">
  <input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>

<!-- 
	...290p.페이지번호가 클릭되면 event.preventDefault()를 이용해서 실제 화면의 이동을 막고,
	...<a>태그에 있는 페이지 번호를 찾아서 <form>태그를 전송하는 방식을 이용함.
 -->
<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}	
</script>

<script>
	$(document).ready(
			function() {
				
				$('#newBtn').on("click", function(evt) {

					self.location = "insert";

				});

			});
</script>


<%@include file="../zinclude/footer.jsp"%>
    