<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<script>
/* ...604p.첨부파일이 이미지인 경우, 원본파일의 경로를 특정한 div에 img 객체로
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
		
		console.log("uploadedList clicked..." + imgTag.attr("src"));
		
		imgTag.addClass("show");				
		$(".popup").show('slow');
		
	}	
});

$("#popup_img").on("click", function(){
	
	$(".popup").hide('slow');
	
});
</script>