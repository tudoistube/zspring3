package org.joywins.controller;
//...348p.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joywins.domain.SampleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ...346p.REST(Representational State Transfer) : 하나의 URI는 하나의 고유한 리소스(Resource)를 
 * ...대표하도록 설계된다는 개념.
 * ...다양한 기기에서 공통으로 데이터를 처리할 수 있는 규칙을 만들려고 하는 시도가 REST방식임.
 * ...OpenAPI에서 많이 사용되면서 REST방식으로 제공되는 외부 연결 URI를 REST API라고 하고,
 * ...REST방식의 서비스 제공이 가능한 것을 'Restful'하다고 표현함.
 * 
 * ...스프링3버전~ : 메서드나 리턴 타입에 @ResponseBody를 사용하여 본격적으로 REST 방식의 처리를 지원함.
 * ...~스프링3버전 : Content Negotiation등을 이용해서 처리.
 *                   과거에는 개발자가 직접 MIME타입을 지정하고, 해당 데이터를 직접 만들어내는 방식이었음.
 * ...스프링4버전~ : @RestController 사용.
 * ...               JSP와 같은 뷰를 만드는 것이 목적이 아닌 REST방식의 데이터 처리를 위해 사용함.
 * ...               스프링3버전까지 @Controller을 사용해서 처리하고, 화면처리(JSP)가 아닌  
 * ...               메서드나 리턴타입에 @ResponseBody를 추가하여 데이터 자체를 서비스함.
 * ...               @RestController 사용으로 컨트롤러 자체의 용도를 REST로 지정함.
 * ...349p.          주로 단순문자열, JSON, XML을 사용함.
 * ...               @RestController는 모든 뷰 처리가 JSP가 아님을 의미함.
 * ...               @RestController가 사용된 클래스의 모든 메서드는 @ResponseBody가 없어도 동일하게 동작함.
 * ...               생략된거라고 생각하면 됨.
 * ...353p.          별도 처리없이 객체는 자동으로 JSON으로 처리할 수 있음.
 */
@RestController
@RequestMapping("/rsample")
public class SampleController {

	/*
	 * ...349p.단순문자열.
	 * ...@RestController에서 문자열 데이터는 기본적으로 브라우저에는 'text/html' 타입으로 처리됨.
	 * ...반환되는 문자열은 JSP 경로가 아닌 일반 문자열임. 
	 * ...http://localhost:8080/z2/rsample/sayHi
	 */
	@RequestMapping("/sayHi")
	public String sayHi() {
		return "Hello World ";
	}
	
	/*
	 * ...354p. http://localhost:8080/z2/rsample/sendVO 실행시 '406 Not Acceptable' 에러 발생함.
	 * ...스프링에서 객체의 변환에 실패했기 때문임.
	 * ...스프링에 jackson-databind 라이브러리를 pom.xml에 추가하여 해결함.
	 * ...서버재시작후 일반브라우저로 확인한 결과 :  {"mno":123,"firstName":"길동","lastName":"홍"}
	 * ...개발자도구 : sendVO::Headers::ResponseHeaders::Content-Type : application/json 확인.
	 */
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);
	
		return vo;
	}

	/*
	 * ...356p. List타입 → JSON 문법상 배열로 표현됨.
	 * ...      Map타입  → JSON 문법상 <키, 값> 조합으로 표현됨.
	 */
	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {

		//...356p. JDK1.7~ : List<SampleVO> list = new ArrayList<>();와 같이
		//...클래스에 제너릭 타입을 명시하지 않아도 됨(타입추론).
		//...원래 표기는 List<SampleVO> list = new ArrayList<SampleVO>();
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {
	
		Map<Integer, SampleVO> map = new HashMap<>();
	
		for (int i = 0; i < 10; i++) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(i);
		map.put(i, vo);
		}
		return map;
	}
	
	/*
	 * ...358p. 웹의 HTTP 상태(status)코드.
	 * ...100번대 : 현재 데이터의 처리 중인 상태.
	 *        100 : 데이터의 일부를 서버가 받은 상태.
	 * ...200번대 : 정상적인 응답.
	 *        200 : 에러없이 정상처리.
	 *        204 : 정상 처리되었으나 서버에서 보내줄 데이터가 없음.
	 * ...300번대 : 다른 URL처리.
	 *       301  : 요청된 페이지가 새 URL로 변경되었음.
	 *       304  : 이미 기존의 데이터와 변경된 것이 없음.
	 * ...400번대 : 서버에서 인식할 수 없음.
	 *       400  : 전송된 Request에 문제가 있어서 서버가 인식할 수 없음.
	 *       403  : 서버에서 허락되지 않음.
	 *       404  : URL에 해당하는 자원을 찾을 수 없음.
	 *       406  : 전송 방식이 허락되지 않음(REST에서 자주 발생)
	 * ...500번대 : 서버내부의 문제.
	 *       500  : 서버에서 처리시 문제가 발생.
	 *       502  : 게이트웨이나 프록시 상태의 문제(과부하...)
	 *       503  : 일시적인 과부하나 서비스 중단 상태.
	 *       504  : 지정된 처리시간이 지나서 처리되지 못함.
	 *       
	 * ...스프링에서 제공되는 ResponseEntity타입은 개발자가 직접 결과 데이터 + HTTP의 상태코드를
	 * ...직접 제어할 수 있는 클래스임.
	 * ...ResponseEntity를 이용하면 개발자는 404 or 500같은 HTTP상태 코드를 전송하고 싶은 데이터와
	 * ...함께 전송할 수 있기 대문에 좀 더 세밀한 제어가 필요한 경우 사용할 수 있음.
	 *  
	 */
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendListAuth() {

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


	/*
	 * ...361p.결과 데이터와 HTTP 상태 코드를 같이 사용하는 경우.
	 * ...주로 호출한 쪽으로 에러의 원인 메시지를 전송할 때 사용함.
	 * ...@RestController를 이용해서 결과 데이터만을 서버에서 제공하는 방식은
	 * ...데이터를 이용하는 클라이언트측에서 기능이 많아지는 경우 사용함.
	 * ...예) Android, iPhone 등의 모바일 환경 : 서버의 데이터를 이용함.
	 * ...    HTML5, Ajax 등을 이용한 경우 많이 사용함.
	 */
	@RequestMapping("/sendErrorInfo")
	public ResponseEntity<List<SampleVO>> sendListNot() {
		
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(i);
		list.add(vo);
		}
		
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
	
	
}








