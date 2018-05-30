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
					<!-- 
						...310p.
						value / 의미
						n / 검색조건 없음.
						t / 제목으로 검색.
						c / 내용으로 검색.
						w / 작성자로 검색.
						tc / 제목 or 내용으로 검색.
						cw / 내용 or 작성자로 검색.
						tcw / 제목 or 내용 or 작성자로 검색. 
					-->
					<select name="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---
						</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							Title
						</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							Content
						</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							Writer
						</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							Title OR Content
						</option>
						<option value="cw"
							<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							Content OR Writer
						</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							Title OR Content OR Writer
						</option>
					</select>
					
					<input type="text" name='keyword' id="keywordInput"
										value='${cri.keyword }'>
					<button id='searchBtn'>Search</button>
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
								<td>
									<a href='/z/zboard/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}'>
										${boardVO.title}
									</a>
								</td>
								...317p.								
								 -->
								<td>
									<a href='/z3/zsboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}'>
										${boardVO.title}
										<!-- ...507p.목록에서 댓글의 수를 미리 보여줌.
												select * from zbook_ex.ztbl_board where bno > 0 order by bno desc;
												
												ztbl_reply 와 ztbl_board 의 댓글 숫자를 일치시킴.												
												update zbook_ex.ztbl_board 
												set REPLY_COUNT = ( select count(rno) 
																	from zbook_ex.ztbl_reply
												                    where bno = zbook_ex.ztbl_board.BNO)
												where bno > 0;   										 
										-->
										<strong>[ ${boardVO.reply_count} ]</strong>	
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
									<a href="/z/zboard/listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
									${pageMaker.makeQuery(pageMaker.startPage - 1)}
									...313p.↓.
									http://localhost:8080/z/zsboard/listPage 실행시
									전달된 데이터가 없으므로 'searchType&keyword'와 같이 값이 없는 형태로 링크가 작성됨.									
								 -->
									<a href="/z3/zsboard/listPage${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a>
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
									...313p.↓.
								 -->
									<a href="/z3/zsboard/listPage${pageMaker.makeSearch(idx)}">${idx}</a>
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
									...313p.↓.
								 -->								
									<a href="/z3/zsboard/listPage${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a>
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

<!-- 
	...319p.PageMaker.makeQuery()를 이용해서 처리함 : makeQuery()는 검색 조건이 없는 상황에서 사용하는 메서드임.
	...즉, 검색조건이 없는 링크를 생성하고, 필요한 링크를 뒤에 연결시키는 방식임.
 -->
<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "listPage"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {

					self.location = "insert";

				});

			});
</script>


<%@include file="../zinclude/footer.jsp"%>
    