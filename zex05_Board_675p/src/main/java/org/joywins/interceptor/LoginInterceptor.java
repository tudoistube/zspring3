package org.joywins.interceptor;
/*
 * ...636p. 
 * ...LoginInterceptor는 '/loginPost'로 접근하도록 설정하는 것을 목적으로 작성됨.
 * ...preHandle()에서는 기존의 HttpSession에 남아있는 정보를 삭제함.
 * ...postHadnle()에서는 LoginUserControllerdptj 'userVO'라는 이름으로 객체를 담아둔
 * ...상태이므로, 이 상태를 체크해서 HttpSession에 저장함.
 *
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
		
	/*
	 * ...658p.주석.
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
		
		logger.info("z5. LoginInterceptor postHandle called...");
		
		HttpSession session = request.getSession();

		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");

		if(userVO != null){
			
			logger.info("z5. new login success");
			session.setAttribute(LOGIN, userVO);
			//response.sendRedirect("/z5/doB"); //...TestOnly.SampleController 참조.
			//...646p.주석.
			//response.sendRedirect("/z5"); //...SampleController 참조.
			
			//...646p.
			//...AuthInterceptor::saveDest()참조.
			Object dest = session.getAttribute("dest");

			response.sendRedirect(dest != null ? (String)dest:"/z5");			
		}
		
	}
	* ...658p.
	* ...앞서 작성한 postHandle()은 HttpSession에 UserVO타입의 객체를 보관했음.
	* ...이를 중간에 쿠키를 생성하고 HttpServletResponse에 같이 담아서 전송하도록 수정함.
	* ...사용자가 '자동 로그인'을 선택한 경우 쿠키를 생성하고 생성된 쿠키의 이름을
	* ...loginCookie로 지정하고, 현재 세션의 아이디값을 보관함.
	* ...세션 아이디는 세션 쿠키의 값을 의미함.
	* ...세션쿠키의 경우 브라우저를 종료하면 사라지지만, 작성하는 loginCookie의 경우
	* ...오랜 시간 보관되기 위해 setMaxAge()를 이용함.
	*/	
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
		
		logger.info("z5. LoginInterceptor postHandle called...");
		
	    HttpSession session = request.getSession();

	    ModelMap modelMap = modelAndView.getModelMap();
	    Object userVO = modelMap.get("userVO");

	    if (userVO != null) {

	      logger.info("z5. new login success");
	      session.setAttribute(LOGIN, userVO);
	      
	      //...중간에 쿠키를 생성하고 HttpServletResponse에 같이 담아서 전송하도록 수정함.
	      if (request.getParameter("useCookie") != null) {

	        logger.info("z5.remember me................");
	        
	        Cookie loginCookie = new Cookie("loginCookie", session.getId());
	        
	        logger.info("z5. loginCookie = " + loginCookie);
	        
	        //...http://docs.oracle.com/javaee/6/api/javax/servlet/http/Cookie.html#setPath(java.lang.String)
	        //...setPath('/folder') : '/foler'이하의 모든 디렉토리에서 보여짐.
	        //...http://egloos.zum.com/utils/v/3225087
	        loginCookie.setPath("/"); //...모든 경로에서 접근가능하게 함.
	        //loginCookie.setPath("/z5");
	        
	        //...setMaxAge()는 초(second)단위의 시간동안 유효하며, 60*60*24*7을 이용해서 일주일간 보관시킴.	        
	        loginCookie.setMaxAge(60 * 60 * 24 * 7);
	        
	        
	        //...만들어진 쿠키는 반드시 HttpServletResponse에 담아서 전송함.
	        response.addCookie(loginCookie);
	      }

	      Object dest = session.getAttribute("dest"); //...AuthInterceptor::saveDest()참조.

	      response.sendRedirect(dest != null ? (String) dest : "/z5");
	    }
	  }



	@Override
	public boolean preHandle(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler) throws Exception {
		
		logger.info("z5.preHandle called...");
		
	    HttpSession session = request.getSession();

	    if (session.getAttribute(LOGIN) != null) {
	      logger.info("z5.clear login data before");
	      session.removeAttribute(LOGIN);
	    }

	    return true;
	}

	
}

