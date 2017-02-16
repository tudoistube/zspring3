package org.joywins.controller;
//...192p.

import javax.inject.Inject;

import org.joywins.domain.BoardVO;
import org.joywins.service.IF_BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @Controller로 설정하고, '/board/'로 오는 모든 요청을 처리한다. 
 * 
 * ...190p.Get방식 or Post방식 결정.
 * Get/Post방식, URI, 작동기능의 조합으로 스토리보드에 미리 URI와 Get/Post방식을 정해야 함.
 * 1. Get방식 : 외부나 다른 사람에게 메신저등으로 보낼 수 있게 하려면 반드시 Get방식 처리함.
 *              조회가 가능하도록 만들어야 하는 모든 경우 Get방식 처리해야 함.
 * 2. Post방식 : 현재 사용자가 스스로 작업하는 내용이 있는 경우 사용함.
 *              외부에 노출하는 것이 아닌 사용자 본인이 결정해서 어떤 작업을 진행하는 경우.
 * 
 * ...191p.리다이렉트 처리방식.
 * 등록/수정/삭제작업이 끝나면 어떻게 결과를 알려주고 페이지를 이동하는지에 대해서 
 * 우리가 사용하는 대부분의 페이지는 Ajax 또는 REST방식으로 처리해서 멋지게 작동하지만,
 * 경험이 없을수록 목표를 단순하게 잡고 최대한 단순한 페이지구성과 많은 화면 전환을 사용해야 함. 
 * 
 * ...193p. 스프링 MVC 메서드의 파라미터와 리턴타입 결정시 고려사항.
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
@RequestMapping("/board/*")
public class BoardController {
	  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	  @Inject
	  private IF_BoardService service;

	  /*
	   * ...194p.등록작업은 등록페이지로 이동(Get방식)과 데이터를 처리(Post방식)으로 구분됨.
	   */	  
	  @RequestMapping(value = "/create", method = RequestMethod.GET)
	  public void createGET(BoardVO board, Model model) throws Exception {
	    logger.info("createGet called ...........");
	    
	  }
	  
	  /*
	   * ...195p.@RequestMapping의 value, method 속성은 배열로 필요시 아래와 같이 처리할 수 있다.
	   * @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
	   */
	   @RequestMapping(value = "/create", method = RequestMethod.POST)
	   public String createPOST(BoardVO board, Model model) throws Exception {
	  
	   logger.info("regist post ...........");
	   logger.info(board.toString());
	  
	   service.create(board);
	  
	   /*
	    * ...196p. Model클래스는 스프링MVC에서 제공하는 데이터 전달용 객체이고, Map처럼
	    *    (key, value)로 구성되어 데이터를 저장함.
	    *    Servlet에서는 RequestDispatcher에 데이터를 저장했음.
	    *    자동으로 BoardVO 로 모든 데이터를 수집하고, 향후에 뷰로 데이터를 전달할 가능성이
	    *    있으므로, Model 객체를 받도록 설계했음.
	    *    
	    * ...235p. ModelAndView : @ControllerAdvice 클래스의 메서드에서 사용함.
	    *    하나의 객체에 Model 데이터와 View 처리를 동시에 할 수 있는 객체임.
	    *    최근에는 지정된 파라미터를 사용하는 경우외에는 잘 쓰지 않음. 
	    */
	   model.addAttribute("result", "success");
	  
	   /*
	    * ...195p.WEB-INF/spring/appServlet/servlet-context.xml에서 
	    * ...org.springframework.web.servlet.view.InternalResourceViewResolver가
	    * ...뷰의 경로를 prefix, suffix로 설정했음.
	    * ...실제경로는 '/WEB-INF/views/zboard/success.jsp'가 됨.
	    */
	   return "/zboard/success";
	   
	   }	  

}
