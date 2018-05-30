<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- ...652p. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<%--
	...293p.
 --%>
<%@include file="../zinclude/header.jsp" %>

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
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- 
	...601p.getFileInfo()는 별도의 JavaScript 파일로 작성함.
	...$.getJSON("/z5/zsboard/getAttach/"+bno,function(list){
	...getFileInfo()처리를 위한 링크추가.
 -->
<script type="text/javascript" src="../resources/zjs/upload.js"></script>


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
<!-- ...335p. -->
    <input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">
	
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">Title</label> 
		<input type="text"
			name='title' class="form-control" value="${boardVO.title}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Content</label>
		<textarea class="form-control" name="content" rows="3"
			readonly="readonly">${boardVO.content}</textarea>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">Writer</label> 
		<input type="text"
			name="writer" class="form-control" value="${boardVO.writer}"
			readonly="readonly">
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
	<!-- ...652p.추가.자신이 작성한 게시물만 수정가능하게 함.  -->
	<c:if test="${login.uid == boardVO.writer}"><!-- ...652p.추가.  -->
		<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
		<button type="submit" class="btn btn-danger" id="deleteBtn">Delete</button>
	</c:if><!-- ...652p.추가.  -->
	<button type="submit" class="btn btn-primary" id="listBtn">Go LIST</button>
</div>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
	
	

<!-- ...426p. -->
	<div class="row">
		<div class="col-md-12">
		
		<!-- 
			...426p. 댓글 등록에 필요한 div
			...652p. 사용자의 로그인에 따라 조회 페이지 내에서의 댓글 추가.
			...		 댓글의 추가 : 로그인한 사용자의 아이디로 추가되어야 함.
			...      댓글의 수정, 삭제 : 댓글 목록을 보는 것은 자유롭지만,
			                             자신이 작성한 댓글에 대해서만 수정, 삭제 가능해야함. 
		-->
			<div class="box box-success">
			
				<c:if test="${not empty login}"><!-- ...652p.추가 -->
				
				   <div class="box-header">
					   <h3 class="box-title">ADD NEW REPLY</h3>
				   </div>
			
					<div class="box-body">
						<label for="exampleInputEmail1">Writer</label> 
						<!-- 
							...652p.주석.로그인한 사용자의 아이디를 읽기전용으로 표시함. 
							<input class="form-control" type="text" placeholder="USER ID"
							   id="newReplyWriter"> 
						  -->
						<input class="form-control" type="text" placeholder="USER ID" 
							id="newReplyWriter" value="${login.uid }" readonly="readonly">     
			
						<label for="exampleInputEmail1">Reply Text</label> 
						<input class="form-control" type="text" placeholder="REPLY TEXT" 
							   id="newReplyText">
					</div>
					
					<!-- /.box-body -->
					<div class="box-footer">
						<!--
							...652p.주석.타입을 button에서 submit으로 변경함.
							<button type="button" class="btn btn-primary" id="replyAddBtn">
						 -->
						<button type="submit" class="btn btn-primary" id="replyAddBtn">	
							ADD REPLY
						</button>
					</div>
			
				</c:if><!-- ...652p.추가 -->
				  
			</div>  
			
			  <!-- ...652p.추가. -->
			  <c:if test="${empty login}">
			    <div class="box-body">
			      <div><h1><a href="javascript:goLogin();" >Login Please</a></h1></div>
			    </div>
			  </c:if>	
			</div>

		<!-- ...426p. 댓글 목록과 페이징 처리에 필요한 div -->
		<!-- The time line -->
			<ul class="timeline">
				<!-- timeline time label -->
				<li class="time-label" id="repliesDiv">
					<span class="bg-green">
						Replies List
						<!-- 
							...510p.댓글목록갯수.[댓글수]라는 식으로 []기호가 같이 나와서 임시로 주석처리.
							<small id='reply_countSmall' data-src="${boardVO.reply_count}"> [ ${boardVO.reply_count} ] </small> 
						-->  
						[<small id='reply_countSmall' data-src="${boardVO.reply_count}"> ${boardVO.reply_count} </small>]
					</span>
				</li>
			</ul>

			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->	
		
	<!-- ...442p. 수정과 삭제를 위한 Modal 창 -->
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body" data-rno>
	        <p><input type="text" id="replytext" class="form-control"></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
	        <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div> 	
	
</section>
<!-- /.content -->

<!-- 
	...602p.이미 업로드된 첨부파일을 보여주기 위한 handlebars의 템플릿.
 -->
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img">
	<img src="{{imgsrc}}" alt="Attachment">
  </span>

  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>  



<!-- ...335p. -->
<script>				
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		//$(".btn-warning").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#modifyBtn").on("click", function(){
			//formObj.attr("action", "/z/zboard/update"); //...read.jsp, 298p.주석.
			//...수정화면으로 이동(get).
			formObj.attr("action", "/z5/zsboard/updatePage");
			formObj.attr("method", "get");		
			formObj.submit();
		});
		
		//$(".btn-danger").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#deleteBtn").on("click", function(){
			alert("Delete Button clicked...");
			
			//...611p.S.게시물삭제와 첨부파일삭제의 화면처리.
			var replyCnt =  $("#reply_countSmall").html();			

			if(replyCnt > 0 ){
				alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
				return;
			}	

			var arr = [];
			$(".uploadedList li").each(function(index){
				 arr.push($(this).attr("data-src"));
			});

			if(arr.length > 0){
				$.post("/deleteAllFiles",{files:arr}, function(){
					
				});
			}
			//...611p.E.게시물삭제와 첨부파일삭제의 화면처리.
			
			//formObj.attr("action", "/x/board/delete"); //...read.jsp, 298p.주석.
			formObj.attr("action", "/z5/zsboard/deletePage");
			formObj.submit();
		});
		
		//$(".btn-primary").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#listBtn").on("click", function(){
			//self.location = "/z/zboard/listAll"; //...read.jsp, 294p 주석.
			
			//...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
			formObj.attr("method", "get");
			formObj.attr("action", "/z5/zsboard/listPage");
			formObj.submit();		
		});
		
	});
</script>

<!-- 
	...435p. template코드는 화면상에서 하나의 댓글을 구성하는 부분임.
	...prettifyDate regdate 
-->
<script id="template_Old" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rno={{rno}}>
		<i class="fa fa-comments bg-blue"></i>
		 <div class="timeline-item" >
		  	<span class="time">
		    	<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		  	</span>

		  	<h3 class="timeline-header">
				<strong>{{rno}}</strong> -{{replyer}}
		  	</h3>

			  <div class="timeline-body">
				{{replytext}} 
			  </div>
		      <div class="timeline-footer">
		     	<a class="btn btn-primary btn-xs" 
			       data-toggle="modal" data-target="#modifyModal">
					Modify
				</a>
		      </div>
		  </div>			
		</li>
	{{/each}}
</script>

<!-- 
	...654p.템플릿변경한것.
	...handlerbars의 기능을 확장하기 위해 'eqReplyer'를 작성했고, ${login.uid}를 활용해서 
	...로그인한 사용자의 경우 값을 비교하도록 하고, 이를 위해서 템플릿도 변경함.	
	...{{#eqReplyer replyer }} 와 {{/eqReplyer}} 추가.
	...작성자본인에게만 Modify링크를 보여줌.
 -->
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rno={{rno}}>
		<i class="fa fa-comments bg-blue"></i>
		 <div class="timeline-item" >
		  	<span class="time">
		    	<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		  	</span>

		  	<h3 class="timeline-header">
				<strong>{{rno}}</strong> -{{replyer}}
		  	</h3>

			  <div class="timeline-body">
				{{replytext}} 
			  </div>
			  <div class="timeline-footer">
				{{#eqReplyer replyer }}
                  <a class="btn btn-primary btn-xs" 
					 data-toggle="modal" data-target="#modifyModal">
					  Modify
				  </a>
				{{/eqReplyer}}
		      </div>
		  </div>			
		</li>
	{{/each}}
</script> 


<!-- 
	...601p.조회페이지에서 기존에 업로드된 파일을 보여주는 템플릿과 자바스크립트.
	...컨트롤러에서 문자열의 리스트를 반환하므로 JSON형태의 데이터를 전송하게 되고,
	...이를 getJSON()을 이용해서 처리함.
	...등록과 마찬가지로 템플릿을 활용하는데, 등록화면에서는 첨부파일 삭제가 가능
	...하지만, 조회의 경우에는 삭제되면 안된다.
 -->
<script>
var bno = ${boardVO.bno};
var template = Handlebars.compile($("#templateAttach").html());

$.getJSON("/z5/zsboard/getAttach/"+bno,function(list){
	$(list).each(function(){
		
		var fileInfo = getFileInfo(this);
		
		var html = template(fileInfo);
		
		 $(".uploadedList").append(html);
		
	});
});

/*
 * ...604p.첨부파일이 이미지인 경우, 원본파일의 경로를 특정한 div에 img 객체로
   ...만들어서 넣은 후 해당 div를 맨 앞쪽으로 보여주게 처리함.
   ...사용자가 첨부파일의 제목을 클릭한 경우 해당파일이 이미지인지 체크하면,
   ...화면이동을 못하도록 막음.
   ...현재 클릭한 이미지의 경로를 id속성값이 'popup_img'인 요소에 추가함.
   ...추가된 뒤에 화면에 보이도록 jQuery.show()를 호출하고, 필요한 CSS를 추가함.
   ...첨부파일을 클릭하면 원본파일을 천천히 화면이 열리면서 보여줌.
   ...화면에 원본 이미지가 보여진 후 다시 한번 사용자가 클릭하면 이미지가 사라짐.
 */
$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
	
	var fileLink = $(this).attr("href");
	
	if(checkImageType(fileLink)){
		
		event.preventDefault();
				
		var imgTag = $("#popup_img");
		imgTag.attr("src", fileLink);
		
		console.log(imgTag.attr("src"));
				
		$(".popup").show('slow');
		imgTag.addClass("show");		
	}	
});

$("#popup_img").on("click", function(){
	
	$(".popup").hide('slow');
	
});
</script>

<!-- 
	...654p.댓글의 수정, 삭제는 로그인한 사용자 본인의 댓글만 가능하게 함.
	...댓글목록이 처리되는 방식은 Ajax와 handlerbars를 이용했기 때문에, handlerbars의 기능을 확장해서
	...댓글의 수정화면으로 진입할 수 있는 버튼이 보여지는 것을 제어함.
	...handlerbars의 기능을 확장하기 위해 'eqReplyer'를 작성했고, ${login.uid}를 활용해서 
	...로그인한 사용자의 경우 값을 비교하도록 하고, 이를 위해서 템플릿도 변경함.
-->
<script>
	Handlebars.registerHelper("eqReplyer", function(replyer, block) {
		var accum = '';
		if (replyer == '${login.uid}') {
			accum += block.fn();
		}
		console.log("eqReplyer :: replyer = " + replyer + ", accum = " + accum);
		return accum;
	});
</script>

<!-- 
	...435p.prettifyDate에 대한 자바스크립트 처리. 
	...handlebars는 helper라는 기능을 이용해서 데이터의 상세한 처리에 필요한 기능들을 처리함.
	...만일 원하는 기능이 없는 경우에는 registerHelper()를 이용해서 사용자가 새로운 기능을 추가할
	...수 있음.
 -->
<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	var printData = function(replyArr, target, templateObject) {
	
		var template = Handlebars.compile(templateObject.html());
	
		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	
	}
	

	//...436p. 해당 게시물에 대한 번호.
	var bno = ${boardVO.bno};
	
	//...436p. 수정이나 삭제작업 이후 사용자가 보던 댓글의 페이지 번호를 가지고 다시 목록을
	//...출력하기 위해 유지되는 데이터임.
	var replyPage = 1;

	//...436p. getPage() : 특정한 게시물에 대한 페이징 처리를 위해 호출되는 함수.
	//...페이지번호를 전달받고, 댓글의 목록 데이터를 처리함.
	//...댓글의 목록 데이터는 'pageMaker'와 'list'로 구성되므로 이를 printPaging()과
	//...printData()에서 처리함.
	function getPage(pageInfo) {

		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));
			$("#reply_countSmall").html("[ " + data.pageMaker.totalCount +" ]");//...510p.
			console.log("댓글 총갯수: " + data.pageMaker.totalCount)
			//$("#modifyModal").modal('hide');			

		});
	}

	var printPaging = function(pageMaker, target) {

		var str = "";

		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1)
					+ "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1)
					+ "'> >> </a></li>";
		}

		target.html(str);
	};	
	
	
	//...437p.화면상에서 'Replies List'라는 버튼을 클릭했을때 댓글 목록을 가져와서 보임.
	//...★ /z5/replies/로 요청경로를 한 이유를 알아야 함.
	//...ajax를 사용해서 JSON으로 값을 받아와서 jQuery로 화면에 표시하므로 JSON으로 값을
	//...처리해주는 별도의 @RestController ReplyController를 사용하고 있다.
	$("#repliesDiv").on("click", function() {

		if ($(".timeline li").size() > 1) {
			return;
		}
		getPage("/z5/replies/" + bno + "/1");

	});
		
	
	//...438p.페이징 처리의 코드는 ul class = 'pagination' 에서 이뤄짐.
	$(".pagination").on("click", "li a", function(event){
		
		event.preventDefault();
		
		replyPage = $(this).attr("href");
		
		getPage("/z5/replies/"+bno+"/"+replyPage);
		
	});

	//...440p. 댓글추가 이벤트 처리.
	$("#replyAddBtn").on("click",function(){
		 
		 var replyerObj = $("#newReplyWriter");
		 var replytextObj = $("#newReplyText");
		 var replyer = replyerObj.val();
		 var replytext = replytextObj.val();
		
		  
		  $.ajax({
				type:'post',
				url:'/z5/replies/',
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "POST" },
				dataType:'text',
				data: JSON.stringify({bno:bno, 
									  replyer:replyer, 
									  replytext:replytext}),
				success:function(result){
					console.log("replyAddBtn clicked result: " + result);
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						replyPage = 1;
						getPage("/z5/replies/"+bno+"/"+replyPage );
						replyerObj.val("");
						replytextObj.val("");
					}
			}});
	});

	
	//...443p. 각 댓글의 버튼 이벤트 처리.
	//...실제 댓글목록에 관한 소스에는 다음과 같은 부분이 대상이 된다.
	/*
		div class = "timeline-footer"
		a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal"
		Modify
		a
		처럼 'data-'로 시작하는 커스텀 속성을 활용해서 'modifyModal'아이디에 속하는 
		div를 화면에 보이게 함.
	*/
	$(".timeline").on("click", ".replyLi", function(event){
		
		var reply = $(this);
		
		$("#replytext").val(reply.find('.timeline-body').text());
		$(".modal-title").html(reply.attr("data-rno"));
		
	});
	
	
	//...댓글 수정 버튼 이벤트.
	$("#replyModBtn").on("click",function(){
		  
		  var rno = $(".modal-title").html();
		  var replytext = $("#replytext").val();
		  
		  $.ajax({
				type:'put',
				url:'/z5/replies/'+rno,
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "PUT" },
				data:JSON.stringify({replytext:replytext}), 
				dataType:'text', 
				success:function(result){
					console.log("댓글 수정 result: " + result);
					if(result == 'SUCCESS'){
						alert("수정 되었습니다.");
						getPage("/z5/replies/"+bno+"/"+replyPage );
					}
			}});
	});

	//...댓글 삭제 버튼 이벤트.
	$("#replyDelBtn").on("click",function(){
		  
		  var rno = $(".modal-title").html();
		  var replytext = $("#replytext").val();
		  
		  $.ajax({
				type:'delete',
				url:'/z5/replies/'+rno,
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "DELETE" },
				dataType:'text', 
				success:function(result){
					console.log("댓글 삭제 result: " + result);
					if(result == 'SUCCESS'){
						alert("삭제 되었습니다.");
						getPage("/z5/replies/"+bno+"/"+replyPage );
					}
			}});
	});	
		
		
</script>
    
<%@include file="../zinclude/footer.jsp" %>
      