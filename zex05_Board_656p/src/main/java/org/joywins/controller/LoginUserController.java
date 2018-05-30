package org.joywins.controller;
/*
 * ...634p.
 * ...컨트롤러에서 HttpSession객체를 처리할 것인지? 
 * ...인터셉터에서 HttpSession객체를 처리할 것인지? 중요한 결정을 해야 함.
 *
 */

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;
import org.joywins.service.IF_LoginUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/zuser")
public class LoginUserController {

	private static final Logger logger = LoggerFactory.getLogger(LoginUserController.class);	
	
	@Inject
	private IF_LoginUserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		logger.info("z5. loginGET called...");
	}

	//...기존의 컨트롤러들과 차이없이 POST방식으로 파라미터를 이용해서 Model에
	//...MsgUserVO객체를 추가함.
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model)
	throws Exception {
		
	 logger.info("z5. loginPost called...");

	 MsgUserVO vo = service.selectUser(dto);

	 if (vo == null) {
		 return;
	 }

	 //...635p.Model : 서버내 객체와 객체 사이의 데이터를 전달함.
	 //   ModelAndView : Model 값을 받음.
	 model.addAttribute("userVO", vo);

	}


	
}
