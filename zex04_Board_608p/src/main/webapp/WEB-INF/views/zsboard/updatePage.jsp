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
	.fileDrop {
	  width: 80%;
	  height: 100px;
	  border: 1px dotted gray;
	  background-color: lightslategrey;
	  margin: auto;
	  
	}
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
			<label for="exampleInputEmail1">BNO</label> 
			<input type="text"
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
	
		<div class="form-group">
			<label for="exampleInputEmail1">File DROP Here</label>
			<div class="fileDrop"></div>
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
$(document).ready(function() {

	var formObj = $("form[role='form']");

	$(".btn-primary").on("click", function() {
		formObj.submit(function(event){
			event.preventDefault();
			
			var that = $(this);
			
			var str ="";
			$(".uploadedList .zremove").each(function(index){
				 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("data-src") +"'> ";
			});
			
			//alert('str = ' + str);
			
			that.append(str);
			
			that.get(0).submit();
		});
	});
	
	//...338p. 취소버튼을 클릭하면 원래 목록 페이지로 이동하게 함.
	$(".btn-warning").on("click", function() {
		self.location = "/z3/zsboard/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}"
						+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
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

<!-- 
	...602p.이미 업로드된 첨부파일을 보여주기 위한 handlebars의 템플릿.
 -->
<script id="templateAttach" type="text/x-handlebars-template">
<li class="{{fullName}}">
  <span class="mailbox-attachment-icon has-img">
	<img src="{{imgsrc}}" alt="Attachment">
  </span>

  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
  <i class="zremove" data-src="{{fullName}}" ><i class="fa fa-fw fa-remove"></i></i>
</li>                
</script>  
<script>
$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

	//console.log(file);
	
	var formData = new FormData();
	
	formData.append("file", file);	
	
	$.ajax({
		  url: '/y4b/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  
			  var html = template(fileInfo);
			  
			  $(".uploadedList").append(html);
		  }
		});	
});


/*
...579p.
...화면에서 small태그로 된 'x' 삭제버튼을 클릭하면 'data-src'속성값으로
...사용된 파일의 이름을 얻어와서 POST방식으로 호출함.
*/			
$(".uploadedList").on("click", ".zremove", function(event){
	var that = $(this);
	var tmp = that.attr("data-src");
	//console.log("tmp : " + tmp);
	
	$.ajax({
	   url:"/z4b/deleteFile",
	   type:"post",
	   data: {fileName:$(this).attr("data-src")},
	   dataType:"text",
	   success:function(result){
		   if(result == 'deleted'){
			   //console.log("fileName : " + $(this).attr("data-src"));	
			   that.parent('li').remove(); 
			   //$(".zremove").parentUntil("li").remove();
			   //that.parentsUntil($("li.tmp")).remove();
		   }
	   }
	});
});//...E.$(".uploadedList").on("click", "small", function(event){
</script>

</div>
<!-- /.content-wrapper -->

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

$.getJSON("/z4b/zsboard/getAttach/"+bno,function(list){
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
		
		//console.log(imgTag.attr("src"));
				
		$(".popup").show('slow');
		imgTag.addClass("show");		
	}	
});

$("#popup_img").on("click", function(){
	
	$(".popup").hide('slow');
	
});
</script>


<%@include file="../zinclude/footer.jsp"%>
