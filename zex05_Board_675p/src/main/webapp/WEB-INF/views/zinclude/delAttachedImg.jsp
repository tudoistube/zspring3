<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
  <i class="zremove" data-src="{{fullName}}" >Delete</i>
</li>                
</script>  
<script>
/*
...579p.
...화면에서 small태그로 된 'x' 삭제버튼을 클릭하면 'data-src'속성값으로
...사용된 파일의 이름을 얻어와서 POST방식으로 호출함.
*/			
$(".uploadedList").on("click", ".zremove", function(event){
	var that = $(this);
	var tmp = that.attr("data-src");
	console.log("tmp : " + tmp);
	
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
		
		console.log(imgTag.attr("src"));
				
		$(".popup").show('slow');
		imgTag.addClass("show");		
	}	
});

$("#popup_img").on("click", function(){
	
	$(".popup").hide('slow');
	
});
</script>	

