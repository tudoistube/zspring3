package org.joywins.controller;
//...192p.

import javax.inject.Inject;

import org.joywins.domain.BoardVO;
import org.joywins.domain.Criteria;
import org.joywins.domain.PageMaker;
import org.joywins.service.IF_BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * @Controller로 설정하고, '/board/'로 오는 모든 요청을 처리한다. 
 * 
 * 190p.Get방식 or Post방식 결정.
 * Get/Post방식, URI, 작동기능의 조합으로 스토리보드에 미리 URI와 Get/Post방식을 정해야 함.
 * 1. Get방식 : 외부나 다른 사람에게 메신저등으로 보낼 수 있게 하려면 반드시 Get방식 처리함.
 *              조회가 가능하도록 만들어야 하는 모든 경우 Get방식 처리해야 함.
 * 2. Post방식 : 현재 사용자가 스스로 작업하는 내용이 있는 경우 사용함.
 *              외부에 노출하는 것이 아닌 사용자 본인이 결정해서 어떤 작업을 진행하는 경우.
 * 
 * 191p.리다이렉트 처리방식.
 * 등록/수정/삭제작업이 끝나면 어떻게 결과를 알려주고 페이지를 이동하는지에 대해서 
 * 우리가 사용하는 대부분의 페이지는 Ajax 또는 REST방식으로 처리해서 멋지게 작동하지만,
 * 경험이 없을수록 목표를 단순하게 잡고 최대한 단순한 페이지구성과 많은 화면 전환을 사용해야 함. 
 * 
 * 193p. 스프링 MVC 메서드의 파라미터와 리턴타입 결정시 고려사항.
 * 1. 파라미터의 수집은 스프링MVC에서 자동으로 이뤄지므로, 파라미터 수집이 필요하면
 *    원하는 객체를 파라미터로 선언한다.
 * 2. DTO클래스를 파라미터로 사용하는 것이 편리하다.
 * 3. 브라우저에서 들어오는 요청(request)이 자동으로 파라미터로 지정한 클래스 객체 속성값으로
 *    처리되는데 이를 바인딩이라고 한다.
 * 4. 스프링MVC Model객체는 해당 메서드에서 뷰(jsp...)에 필요한 데이터를 전달하는 용도로
 *    사용되므로, 만일 메서드 내에서 뷰로 전달할 데이터가 있다면, Model을 파라미터로 선언
 *    해주는 것이 편리하다.
 */
@Controller
@RequestMapping("/zboard/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private IF_BoardService service;

	/*
	 * ...194p.등록작업은 등록페이지로 이동/입력/조회(Get방식)과 데이터를 처리(Post방식)으로 구분됨.
	 * ...195p. @RequestMapping::value속성은 특정한 URI를 의미함. ...Prefix + value속성값 +
	 * Suffix 형태로 이동할 페이지가 자동으로 반환됨. URL 패턴에서 기억해야 할 주요한 사실은 디폴트 접미어 패턴(default
	 * suffix pattern)이 적용 된다는 점이다. 예를 들어 다음과 같이 @RequestMapping을 적용 했다고 하자.
	 * 
	 * @RequestMapping("/hello")
	 * 
	 * 이렇게 확장자가 붙지 않고 /로 끝나지도 않는 URL 패턴에는 디폴트 접미어 패턴이 적용돼서 실제로는 다음 세개의 URL 패턴을
	 * 적용했을 때와 동일한 결과가 나온다.
	 * 
	 * @RequestMapping("/hello" , "/hello/" , "/hello.*"})
	 * 
	 * 따라서 /hello라고 정의한 것만으로 /hello.do , /hello.html과 같이 확장자가 붙은 URL이나 /hello/처럼
	 * /로 끝나는 URL도 자동으로 매핑 된다.
	 * 
	 * [출처] @RequestMapping 핸들러 매핑|작성자 듀시스트
	 * http://blog.naver.com/PostView.nhn?blogId=deux0083&logNo=220088984575
	 * ...193p. 스프링MVC Model객체는 해당 메서드에서 뷰(jsp...)에 데이터를 전달용 객체. ...196p. Map과
	 * 유사하게 (key, value) 조합으로 데이터를 저장함.
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertGET(BoardVO board, Model model) throws Exception {
		logger.info("insertGet called ...........");

	}

	// /*
	// * ...195p.@RequestMapping의 value, method 속성은 배열로 필요시 아래와 같이 처리할 수 있다.
	// * @RequestMapping(value = "/register", method = {RequestMethod.POST,
	// RequestMethod.GET})
	// */
	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// public String createPOST(BoardVO board, Model model) throws Exception {
	// logger.info("insert post ...........");
	// logger.info(board.toString());
	//
	// service.insert(board);
	//
	// /*
	// * ...196p. Model클래스는 스프링MVC에서 제공하는 데이터 전달용 객체이고, Map처럼
	// * ...(key, value)로 구성되어 데이터를 저장함.
	// * ...Servlet에서는 RequestDispatcher에 데이터를 저장했음.
	// */
	// model.addAttribute("result", "success");
	//
	// /*
	// * ...195p.WEB-INF/spring/appServlet/servlet-context.xml에서
	// * ...org.springframework.web.servlet.view.InternalResourceViewResolver가
	// * ...뷰의 경로를 prefix, suffix로 설정했음.
	// * ...실제경로는 '/WEB-INF/views/zboard/success.jsp'가 됨.
	// */
	// //return "/zboard/success";//...207p.주석처리.
	// /*
	// * ...207p./zboard/success.jsp에서 새로고침을 하면 같은 글이 계속 등록되는 도배현상 발생함.
	// * ...바로 페이지를 자동으로 다른 곳으로 이동시킴(리다이렉트).
	// * ...src/main/resources/mappers/boardMapper.xml에 listAll이 작성되어 있어야 함.
	// * ...호출경로는 /zboard/listAll?result=success 가 되고,
	// * ...http://localhost:8080/z/zboard/listAll?result=success 로 페이지 주소 바뀜.
	// */
	// return "redirect:/zboard/listAll";
	//
	// }

	/*
	 * ...210p.위와 같이 Model을 이용한 경우 동일한 내용의 도배현상은 막을 수 있으나, ...새로고침할 때
	 * /zboard/listAll?result=success은 계속 남아있는 문제점이 남음.
	 * ...Spring.RedirectAttributes객체는 리다이렉트 시점에 한번만 사용되는 데이터를 ...전송할 수 있는
	 * addFlashAttribute()를 지원하여 페이지이동 및 데이터 전달후
	 * ...http://localhost:8080/z/zboard/listAll로 깔끔하게 정리됨.
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPOST(BoardVO board, RedirectAttributes rattr) throws Exception {
		logger.info("insert post  addFlashAttribute...........");
		logger.info(board.toString());

		service.insert(board);

		rattr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/zboard/listAll";

	}

	/*
	 * ...207p.
	 * ...214p.listAll(Model m)은 MVC의 Model타입의 객체를 파라미터로 받아서, ...모든 게시물을
	 * model.addAttribute()로 JSP에 전달함.
	 */
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list......................");
		model.addAttribute("list", service.listAll());
	}
	
	/*
	 * ...262p. 스프링MVC 컨트롤러는 특정 URL에 해당하는 메서드를 실행할 때,
	 * ...파라미터의 타입을 보고, 해당 객체를 자동으로 생성해냄.
	 * ...파라미터가 자종으로 수집되기 때문에, Criteria클래스를 그대로 사용할 수 있음.
	 * ...http://localhost:8080/z/zboard/listCriteria 의 경우, Criteria클래스에서
	 * ...선언한 대로 page = 1, perPageNum = 10 이 됨.
	 * ...http://localhost:8080/z/zboard/listCriteria?page=3 의 경우, Criteria객체에
	 * ...page = 3, perPageNum = 10 이 됨.
	 */	
	@RequestMapping(value = "/listCriteria", method = RequestMethod.GET)	
	public void listAll(Criteria cri, Model model) throws Exception {

		logger.info("show list Page with Criteria......................");

		model.addAttribute("list", service.listCriteria(cri));
	}	

	/*
	 * ...219p.@ReqeustParam("bno")는 Servlet::request.getParam("bno")처럼 동작함.
	 * ...@RequestParam을 사용하여 외부에서 전달될 bno값을 파라미터로 받는 것을 더욱 
	 * ...명확하게 표현함.
	 * ...Servlet.HttpServletRequest와 다른 점은 문자열, 숫자, 날짜 등의 형변환이 가능함.
	 * ...Model.addAttribute()작업할때 아무런 이름 없이 데이터를 넣으면 자동으로 
	 * ...클래스의 이름을 소문자로 시작해서 사용한다. 
	 * ...BoardVO org.joywins.service.IF_BoardService.read(Integer bno)
	 * throws Exception처럼 ...여기서 들어가는 데이터는 BoardVO객체이므로 boardVO라는 이름으로 저장됨.
	 */	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, 
					 Model model) throws Exception {
		logger.info("read called......................");
		model.addAttribute(service.read(bno));
	}

	/*
	 * ...292p.페이징 처리가 된 후, 조회 페이지는 다시 목록 페이지로 돌아가기 위해 다음 3가지 정보가 필요함.
	 * ...1. 현재 목록 페이지의 페이지 번호(page).
	 * ...2. 현재 목록 페이지의 페이지당 데이터 수(perPageNum).
	 * ...3. 현재 조회하는 게시물의 번호(bno).
	 * ...★같은 메서드를 요청별로 오버로딩할 수 있다.
	 * ...'/z/zboard/readPage?bno=xx&page=x&perPageNum=xxx'와 같은 식으로 요청을 받고,
	 * ...★page와 perPageNum 파라미터는 Criteria 타입의 객체로 처리함.
	 * ...★Criteria를 확장해서 bno값을 처리하지 않고, 별도의 bno 파라미터를 사용하는 이유는
	 * ...숫자가 아닌 문자열을 처리하는 경우 다시 상속해야 하는 문제가 발생하고, Criteria는
	 * ...페이징 처리를 위해 존재하는 객체이므로, 매번 의미없는 bno등을 유지할 필요가 없음.
	 */
	  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
	  public void read(@RequestParam("bno") int bno, 
			  		   @ModelAttribute("cri") Criteria cri, 
			  		   Model model) throws Exception {
		logger.info("readPage called......................");
	    model.addAttribute(service.read(bno));
	  }
	
	
	/*
	 * ...225p.@RequestParam("bno")를 제외해도 bno에 값이 들어옴.
	 * ...addFlashAttribute()를 사용해서 한번만 실행되어서 새로고침시 계속 데이터가 전송되지 않게 함.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, 
						 RedirectAttributes rttr) throws Exception {

		logger.info("delete called... bno = " + bno);
		
		service.delete(bno);

		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info("삭제처리, bno = " + bno);

		return "redirect:/zboard/listAll";
	}

	/*
	 * ...296p.파라미터로 Criteria를 사용해서 JSP로 page, perPageNum을 전송함.
	 */
	@RequestMapping(value = "/deletePage", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, 
						 Criteria cri,
						 RedirectAttributes rttr) throws Exception {

		logger.info("deletePage POST called... bno = " + bno + cri.toString());
		
		service.delete(bno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addFlashAttribute("msg", "SUCCESS");		
		
		logger.info("삭제처리, bno = " + bno);

		return "redirect:/zboard/listPage";
	}
	
	
	// ...227p.수정페이지로 이동.
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("수정페이지로 이동, update get called.../ bno = " + bno);
		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO board, RedirectAttributes rttr) throws Exception {

		logger.info("수정처리, update post BoardVO = " + board.toString());

		service.update(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/zboard/listAll";
	}

	//...298p.수정페이지로 이동.
	@RequestMapping(value = "/updatePage", method = RequestMethod.GET)
	public void updatePageGET(@RequestParam("bno") int bno, 
							    @ModelAttribute("cri") Criteria cri, 
							    Model model) throws Exception {
		logger.info("수정페이지로 이동, updatePage get called.../ bno = " + bno + " / cri = " + cri.toString());
		model.addAttribute(service.read(bno));
	}
	
	//...300p.수정처리.
	@RequestMapping(value = "/updatePage", method = RequestMethod.POST)
	public String updatePagePOST(BoardVO board, 
					  			Criteria cri, 
					  			RedirectAttributes rttr) throws Exception {
	  logger.info("수정처리, updatePage post called.../ board = " + board.toString() + " / cri = " + cri.toString());
	  service.update(board);
	  rttr.addAttribute("page", cri.getPage());
	  rttr.addAttribute("perPageNum", cri.getPerPageNum());
	  rttr.addFlashAttribute("msg", "SUCCESS");
  
	  return "redirect:/zboard/listPage";
	  
	}  
  	
	
	/*
	 * ...275p. listPage()에서는 크게 목록 데이터를 Model에 저장하는 작업과 
	 * ...PageMaker를 구성해서 Model에 담는 작업을 함.
	 * ...282p. http://localhost:8080/z/zboard/listPage?page=256
	 */
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	// public void listPage(Criteria cri, Model model) throws Exception {//...275p.
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {//...281p.

		logger.info("listPage called..." + cri.toString());

		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		// pageMaker.setTotalCount(131);//...274p.
		pageMaker.setTotalCount(service.countBno(cri));// ...281p.

		model.addAttribute("pageMaker", pageMaker);
	}

}
