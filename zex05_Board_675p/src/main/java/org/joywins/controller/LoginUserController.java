package org.joywins.controller;
/*
 * ...634p.
 * ...컨트롤러에서 HttpSession객체를 처리할 것인지? 
 * ...인터셉터에서 HttpSession객체를 처리할 것인지? 중요한 결정을 해야 함.
 *
 */

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/zuser")
public class LoginUserController {

	private static final Logger logger = LoggerFactory.getLogger(LoginUserController.class);	
	
	@Inject
	private IF_LoginUserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		logger.info("z5. login called...");
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
	
		 model.addAttribute("userVO", vo);
		 
		 //...666p.
		 //...사용자가 '자동 로그인'을 선택한 경우, loginCookie값이 유지되는 시간 정보를 DB에 저장함.
		 if (dto.isUseCookie()) {

			 int amount = 60 * 60 * 24 * 7; //...7일.

			 Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));

			 service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		 }		 

	}
	
	/*
	 * ...673p.
	 * ...로그아웃의 처리는 HttpSession인 경우 login과 같이 저장된 정보를 삭제하고,
	 * ...invalidate()를 주는 작업과 쿠키의 유효시간을 변경하는 작업을 함께 함.
	 * ...직접 파라미터로  HttpServletRequest등을 받는 방식이 가장 단순하고,
	 * ...별도의 인터셉터를 이용하는 방식도 사용해볼만 함.
	 *
	 * ...header.jsp에 링크 적용함.
          <!-- Menu Footer-->
          <li class="user-footer">
            <div class="pull-left">
              <a href="#" class="btn btn-default btn-flat">Profile</a>
            </div>
            <div class="pull-right">
              <a href="/z5/zuser/logout" class="btn btn-default btn-flat">Sign out</a>
            </div>
          </li>
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, 
						HttpServletResponse response, 
						HttpSession session) throws Exception {

		logger.info("z5. logout called...");
		
		Object obj = session.getAttribute("login");
	
		if (obj != null) {
			MsgUserVO vo = (MsgUserVO) obj;
		
			session.removeAttribute("login");
			session.invalidate();
		
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());				
			}
			
		}
		
		return "redirect:/zuser/login";
	}


	
}
