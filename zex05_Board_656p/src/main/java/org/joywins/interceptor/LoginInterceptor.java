package org.joywins.interceptor;
/*
 * ...636p. 
 * ...LoginInterceptor는 '/loginPost'로 접근하도록 설정하는 것을 목적으로 작성됨.
 * ...preHandle()에서는 기존의 HttpSession에 남아있는 정보를 삭제함.
 * ...postHadnle()에서는 LoginUserControllerdptj 'userVO'라는 이름으로 객체를 담아둔
 * ...상태이므로, 이 상태를 체크해서 HttpSession에 저장함.
 *
 */

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
			
			logger.info("z5. new login success!!!!!");
			session.setAttribute(LOGIN, userVO);
			//response.sendRedirect("/z5/doB"); //...TestOnly.SampleController 참조.
			//...since 646p.주석.
			//response.sendRedirect("/z5"); //...SampleController 참조.
			
			//...646p.AuthInterceptor::saveDest()참조.
			Object dest = session.getAttribute("dest");

			response.sendRedirect(dest != null ? (String)dest:"/z5");			
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

