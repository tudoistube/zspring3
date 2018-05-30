package org.joywins.controller;
//...379p.

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joywins.domain.Criteria;
import org.joywins.domain.PageMaker;
import org.joywins.domain.ReplyVO;
import org.joywins.service.IF_ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/*
 * ...346p.REST(Representational State Transfer) : 하나의 URI는 하나의 고유한 리소스를
 * ...대표하도록 설계된다는 개념.
 * ...REST방식은 특정한 URI는 반드시 그에 상응하는 데이터 자체라는 것을 의미함.
 * ...예) '/zboard/123' : 게시물중 123번이라는 고유한 의미를 가지도록 설계하고,
 * ...                    이에 대한 처리는 GET, POST방식과 같이 추가적인 정보를
 * ...                    통해서 결정함.
 * ...최근 Open API에서 많이 사용되면서 REST방식으로 제공되는 외부 연결 URI를 REST API라함.
 * ...REST방식의 서비스 제공이 가능한 것을 'Restful'하다고 표현함.
 * ...
 * ...스프링4~ : @RestController 사용함.
 * ...           JSP와 같은 뷰를 만드는것이 목적이 아닌 REST방식의 데이터 처리를 위해 사용함.
 * 
 * ...스프링3~ : @ResponseBody를 지원하면서 본격적인 REST방식의 처리를 지원함.
 * ...
 * ...~스프링3 : Content Negotiation...을 이용해서 처리함.
 *               스프링3까지는 주로 @Controller만을 사용하고, 화면처리를 담당하는
 *               JSP가 아닌 데이터 자체를 서비스하려면 해당 메서드나 리턴타입에
 *               '@ResponseBody'를 추가하는 형태로 작성했음.
 *               과거에는 개발자가 직접 MIME타입을 지정하고, 해당 데이터를 만드는 방식이었음.
 * 
 * ...369p.REST방식을 이용하려면 우선 컨트롤러를 먼저 작성하고, 그에 맞는 URI를
 * ...결정하는 것이 첫단계임.
 * ...URI						|	전송방식	|	설명
 *  /replies/ + JSON데이터		|	POST		|	새로운 댓글 추가.
 *  /replies/all/123			|	GET			|	'게시물123'번의 모든 댓글 리스트 조회.
 *  /replies/456 + JSON데이터	|	PUT/PATCH	|	'댓글456'번의 수정.
 *  /replies/456				|	DELETE		|	'댓글456번'의 삭제.
 *  
 *  ...395p. @RestController를 이용하는 경우에는 데이터만 만들어서 전송하므로,
 *  ...화면처리에 개발이 집중이 됨.
 *  ...앱에서는 HTTP방식으로 데이터를 주고받는 라이브러리등을 활용해서 처리함.
 *  ...웹에서는 Ajax를 이용해서 처리함.
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private IF_ReplyService service;

	/*
	 * ...377p.REST 방식 처리에 사용하는 특별한 어노테이션.
	 * ...@PathVariable : URI의 경로에서 원하는 데이터를 추출함.
	 * ...@RequestBody : 전송된 JSON데이터를 객체로 변환해주는 어노테이션으로
	 * ...@ModelAttribute와 유사한 역할을 하지만 JSON에서 사용된다는 차이가 있음.
	 * 
	 * ...379p.
	 * ...ResponseEntity<String>반환형을 사용하여, 댓글등록에 실패하면
	 * ...try~ catch~ 선언처리로 예외의 원인메시지를 전송하고
	 * ...사용자에게는 BAD_REQEUST(400)을 결과로 전송함.
	 * ...데이터 전송방식은 JSON을 이용하므로 @RequestBody를 사용함.
	 * 
	 * ...Advanced REST Cient 이용 테스트.
	 * ...http://localhost:8080/z2/replies
			Content-Type: application/json
			{
			  "replytext" : "z2댓글을 추가합니다",
			  "replyer"   : "zuser00",
			  "bno"       : "1638456"
			}
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertReply(@RequestBody ReplyVO vo) {
	
		logger.info("insert POST called ...........");
				
		ResponseEntity<String> entity = null;
		try {
			service.insertReply(vo);
			logger.info("OK... vo = " + vo.toString());	
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/*
	 * ...382p.
	 * ...@RequestMapping(value = "/all/{bno}"...)를 보면 URI내의 경로 {bno}를 활용함.
	 * ...{bno}는 메서드의 파라미터에서 @PathVariable("bno")로 활용됨.
	 * 
	 * ...아래 URI에서 1638456은 게시글 번호임.
	 * ...http://localhost:8080/z2/replies/all/1638456
			Content-Type: application/json

	 */
	@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> selectReplies(@PathVariable("bno") Integer bno) {

		logger.info("selectReplies GET called ...........");						
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(service.selectReplies(bno), 
										  HttpStatus.OK);
			logger.info("OK... bno = " + bno);
		
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		return entity;
		
	}

	/*
	 * ...384p.
	 * ...REST방식에서 update작업은 PUT, PATCH방식을 이용해서 처리함.
	 * ...PUT : 전체 데이터를 수정하는 경우 사용.
	 * ...PATCH : 일부 데이터를 수정하는 경우 사용. 
	 * ...@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	 * ...에서는 PUT, PATCH 방식 모두 사용할 수 있도록 선언되 있음.
	 * 
	 * ...아래 URI에서 11은 댓글번호임.
	 * ...http://localhost:8080/z2/replies/11
			Content-Type: application/json
			{
			 "replytext" : "z2댓글을 수정"
			 }
	 *
	 * ...387p. GET/POST방식만 지원하는 브라우저의 경우를 대비하기 위해 HiddenHttpMethodFilter를
	 * ...web.xml에 필터 설정을 추가함.
	 *
	 */
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> updateReply(@PathVariable("rno") Integer rno, 
											  @RequestBody ReplyVO vo) {
	
		logger.info("update PUT/PATCH called ...........");
		
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			service.updateReply(vo);
			logger.info("OK... vo = " + vo.toString());
			
		entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}

	/*
	 * ...388p.
	 * ...아래 URI에서 11은 댓글번호임. 
	 * ...http://localhost:8080/z2/replies/4
			Content-Type: application/json
	 */
	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReply(@PathVariable("rno") Integer rno) {
	
		logger.info("delete PUT/PATCH called ...........");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteReply(rno);
			logger.info("OK... rno = " + rno);
		entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error ...........");
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * ...393p.두개의 @PathVariable을 이용해서 처리함.
	 * ...'/replies/게시물번호/페이지번호' + GET방식.
	 * ...http://localhost:8080/z2/replies/1638456/10
			Content-Type: application/json
	 * 
	 * ...Ajax로 호출되므로 Model을 사용하지 못하므로,
	 * ...전달해야하는 데이터들을 담기 위해 Map타입의 객체를 별도로 생성해야함.
	 * ...화면으로 전달되는 Map데이터는 페이징 처리된 댓글의 목록(list)와
	 * ...PageMaker클래스 객체(pageMaker)를 담는다.
	 * ...게시물 페이징과 달리 댓글은 페이지당 보여주는 댓글수가 변경되는 일이
	 * ...드물고, 10, 20, 50, 100...으로 결정해 주는 것이 일반적임.
	 */
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> 
				selectPageReplies(@PathVariable("bno") Integer bno,
								  @PathVariable("page") Integer page) 
	{
		
		logger.info("selectPageReplies GET called... bno = "+ bno + " / page = "+ page);
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = service.selectPageReplies(bno, cri);
			
			map.put("list", list);
			
			int replyCount = service.countReplies(bno);
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			logger.info("OK... replyCount = " + replyCount + " / pageMaker = " + pageMaker.toString());
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}	
	
}
