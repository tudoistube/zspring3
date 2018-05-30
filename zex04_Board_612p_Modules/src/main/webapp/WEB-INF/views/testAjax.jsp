<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>testAjax...396p</title>
	
	<style>
		#modDiv {
			width: 300px;
			height: 100px;
			background-color: gray;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-top: -50px;
			margin-left: -150px;
			padding: 10px;
			z-index: 1000;
		}
		
		.pagination {
		  width: 100%;
		}
		
		.pagination li{
		  list-style: none;
		  float: left; 
		  padding: 3px; 
		  border: 1px solid blue;
		  margin:3px;  
		}
		
		.pagination li a{
		  margin: 3px;
		  text-decoration: none;  
		}	
	</style>
</head>
<body>

	<h2>Ajax Test Page...396p</h2>

	<!-- 
		...402p. form태그를 이용할 수 있지만, 전송할 내용이 많지 않아
		...id 속성을 이용해서 처리함.
		...댓글은 조회 페이지에서 작성되므로 게시물 번호는 받지 않음.
	 -->
	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id="insertReplyBtn">ADD REPLY</button>
		<button id="postReplyBtn">Post REPLY</button>
	</div>


	<!-- 
		...396p.댓글목록이 출력됨. 
	-->
	<ul id="replies">
	</ul>
	
	<!-- 
		...419p.댓글 페이지를 출력함.
	 -->
	<ul class='pagination'>
	</ul>	

	<!-- 
		...412p.
		...수정과 삭제작업을 하는 div.
		...평상시에는 안보이도록 하고, 각 댓글 항목의 'MOD'버튼을 누르는 경우에만
		...보이도록 함.
	 -->
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id='closeBtn'>Close</button>
		</div>
	</div>

	

	<!--
		...397p.jQuery 2.1.4
		...현재 프로젝트의 경우 '/z2/'를 경로로 사용하므로 직접 '/z2/resources/'로
		...시작하는 경로를 이용함.
	<script src="http://code.jquery.com/jquery-2.1.4.js"></script>		 
	-->
	<script src="/z2/resources/plugins/jQuery/jquery-2.2.0.js"></script>


	<script>
		/*
		...398p.
		@RestController는 객체를 JSON으로 전달하므로 jQuery를 이용해서 호출할때
		getJSON()을 이용함.
		@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
		public ResponseEntity<List<ReplyVO>> selectReplies(@PathVariable("bno") Integer bno)
		...실행결과를 JSON으로 받아서 data를 each하면 ReplyVO.rno, ReplyVO.replytext를 받아서
		...처리할 수 있음.
		...http://localhost:8080/z2/testAjax를 실행하고 개발자도구 검사::Network::좌측에서 
		...게시글번호를 선택.
		...우측 Preview탭을 보면 해당 게시글에 등록된 댓글들이 JSON형태로 출력되고
		...아래쪽 Console에는 해당게시글의 댓글수가 출력됨.
		
		...400p.
		...li태그의 속성으로 사용한 'data-'로 시작하는 속성은 이름이나 개수에 관계없이 
		...태그 내에서 자유롭게 사용할 수 있는 속성으로, id나 name속성을 대신해서 
		...사용하기 편리함.	
		
		...댓글목록 갱신은 자주 사용되므로 selectReplies()로 만듦.	
		*/		
	
		//selectReplies();//...398p.
		
		function selectReplies()
		{
			//...398p.
			var bno = '1638456'
			$.getJSON(
					"/z2/replies/all/" + bno,
					function(data) {	
						console.log(data.length);
						
						//...400p.
						var str = "";
						$(data).each(
									function() {
											str += "<li data-rno='"+this.rno+"' class='replyLi'>"
													+ this.rno
													+ ":"
													+ this.replytext
													+ "<button>○MOD○</button></li>";//...409p.
										}
									);
	
						$("#replies").html(str);
						
					});			
		}

		/* 
		...403p.jQuery를 이용하여 $.ajax()를 통해 서버를 호출함.
		...전송하는 데이터는 JSON으로 구성된 문자열을 사용하고,
		...전송받은 결과는 단순문자열임.
		...특이한 점은 jQuery가 제공하는 $.post()등을 사용하지 않고,
		...$.ajax()를 이용하여 다양한 옵션으로 구성됨. 
		...407p.전송할때 HTTP헤더정보에 'application/json'이라고 명시함.
		...전송되는 데이터는 JSON.stringify()를 이용해서 JSON데이터로
		...구성해서 전송함.
		*/
	
		var bno = 1638456;
		
		$("#insertReplyBtn").on("click", function() {
	
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
	
			$.ajax({
					type : 'post',
					url : '/z2/replies',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'text',
					data : JSON.stringify({
						bno : bno,
						replyer : replyer,
						replytext : replytext
					}),
					success : function(result) {
		
						if (result == 'SUCCESS') {
		
							alert("등록 되었습니다.");
							//selectReplies();
							selectPageReplies(replyPage);
		
						}
					}
			});
		});	


		/*
		...405p.$.post()의 경우 일반적인 데이터 전송 방식에 적합함.
		...아래 코드를 실행하면 415(지원되지 않는 타입)상태코드가 전송됨.
		...이렇게 전송될 경우 RestController에서는 @RequestBody가 제대로 처리하지 못함.
		...@RequestBody의 경우 JSON으로 전송된 데이터를 ReplyVO타입의 객체로 변환해주는
		...역할을 하는데, 이때의 데이터는 일반적인 데이터가 아닌 JSON으로 구성된 문자열
		...데이터임. 
		*/

		$("#postReplyBtn").on("click", function() {
	
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			alert("replyer = " + replyer + "/ replytext = " + replytext);
			
			$.post("/z2/replies",
					{replyer : replyer, replytext : replytext},				
					function(result) {		
						alert(result);
					}
			);
		});	

		
		/*
		...410p.댓글의 각 항목을 의미하는 li태그의 경우 Ajax 통신 후에 생기는 요소들이므로
		...이벤트 처리를 할 때 기존에 존재하는 ul태그를 이용해서 이벤트를 등록함.
		...이후의 이벤트는 위임(Delegation)방식으로 전달하는데, class의 속성값이
		...'replyLI'로 된 요소 밑의 button을 찾아서 이벤트를 전달함.
		...jQuery의 이벤트는 아직 존재하지 않는 요소에 대해 이벤트를 위임해주는
		...편리한 기능이 있어서, 한번에 모든 목록에 대한 클릭 이벤트를 처리할 수 있음.
	 	*/

		$("#replies").on("click", ".replyLi button", function() {
	
			var reply = $(this).parent();
	
			var rno = reply.attr("data-rno");
			var replytext = reply.text();
			
			alert(rno + " : " + replytext);
	
			//...414p.
			$(".modal-title").html(rno);
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");
	
		});

	
	
		// ...415p.댓글삭제작업.
		$("#replyDelBtn").on("click", function() {
	
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
	
			alert(rno + " : " + replytext);
			
			$.ajax({
					type : 'delete',
					url : '/z2/replies/' + rno,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType : 'text',
					success : function(result) {
						console.log("result: " + result);
						if (result == 'SUCCESS') {
							alert("삭제 되었습니다.");
							$("#modDiv").hide("slow");
							//selectReplies();
							selectPageReplies(replyPage);
						}
					}
				});
		});	
	

		// ...417p. 댓글수정작업.
		$("#replyModBtn").on("click",function(){
			  
			  var rno = $(".modal-title").html();
			  var replytext = $("#replytext").val();
			  
			  $.ajax({
					type:'put',
					url:'/z2/replies/'+rno,
					headers: { 
					      "Content-Type": "application/json",
					      "X-HTTP-Method-Override": "PUT" },
					dataType:'text',					      
					data:JSON.stringify({replytext:replytext}),
					success:function(result){
						console.log("result: " + result);
						if(result == 'SUCCESS'){
							alert("수정 되었습니다.");
							 $("#modDiv").hide("slow");
							 //selectReplies();
							 selectPageReplies(replyPage);
						}
				}});
		});	

		$("#closeBtn").on("click", function() {
		
			alert("close reply box...");
			console.log("close reply box... ");
			$("#modDiv").hide("slow");
	
		});	



		/* 
		...419p.
		...http://localhost:8080/z2/replies/1638456/1 
		...selectPageReplies()는 페이지번호를 입력받고, jQuery의 getJSON()을
		...이용해서 가져온 데이터를 처리함.
		...서버에서 전송된 데이터 중 댓글 목록인 list데이터를 이용해서 댓글
		...내용을 표시하고, 페이징 처리를 위한 pageMaker데이터를 이용해서
		...printPaging()을 호출함.
	 	*/

		function selectPageReplies(page){
			
			  $.getJSON("/z2/replies/"+bno+"/"+page , function(data){
				  
				  console.log(data.list.length);
				  
				  var str ="";
				  
				  $(data.list).each(function(){
					  str+= "<li data-rno='"
					  	 +this.rno
					  	 +"' class='replyLi'>" 
					  	 +this.rno
					  	 +":"
					  	 + this.replytext
					  	 +"<button>MOD</button></li>";
				  });
				  
				  $("#replies").html(str);
				  
				  printPaging(data.pageMaker);
				  
			  });
			}		
	


		/* 
		...420p.화면에 페이지 번호를 출력함.
		*/

		function printPaging(pageMaker){
			
			var str = "";
			
			if(pageMaker.prev){
				str += "<li><a href='"
					+(pageMaker.startPage-1)
					+"'> << </a></li>";
			}
			
			for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++)
			{				
				var strClass= pageMaker.cri.page == i?'class=active':'';
			  	str += "<li "
			  		+strClass
			  		+"><a href='"
			  		+i
			  		+"'>"
			  		+i
			  		+"</a></li>";
			}
			
			if(pageMaker.next){
				str += "<li><a href='"
					+(pageMaker.endPage + 1)
					+"'> >> </a></li>";
			}
			
			$('.pagination').html(str);				
		}	

	
		/* 
		...422p.각 페이지 번호에 대한 클릭 이벤트처리.
		...a 태그 내용중 페이지 번호를 추출해서 Ajax처리.
		...event.preventDefault();는 기본적인 a href 태그의 기본 동작인 페이지 전환을 막음.
		...화면의 이동을 막은 후, 현재 클릭된 페이지의 번호를 추출함.
		...이 번호를 selectPageReplies() 호출함.
		.../z2/replies/"+bno+"/"+page, GET 방식으로 컨트롤러에 전달되어 selectPageReplies 처리.
		...클릭되는 페이지 번호를 의미하는 replyPage는 수정, 삭제작업에 다시 목록 페이지를
		...갱신할 때 필요한 정보이므로, 별도의 변수로 처리함.
		*/
		var replyPage = 1;
	
		$(".pagination").on("click", "li a", function(event){
			
			event.preventDefault();
			
			replyPage = $(this).attr("href");
			
			selectPageReplies(replyPage);
			
		});
		
		/*
		...420p.JSP가 처음 동작하면 1페이지의 댓글을 가져오도록 함.
		...같은 script태그안에 있어야 실행이 됨. 
	 	*/

		var bno = 1638456;
	
		selectPageReplies(1);			
	</script>	

</body>
</html>