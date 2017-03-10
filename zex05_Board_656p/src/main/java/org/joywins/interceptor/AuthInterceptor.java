package org.joywins.interceptor;
/*
 * ...641p.
 * ...LoginInterceptor는 로그인한 사용자에 대해 postHandle()을 이용해서 
 * ...HttpSession에 보관하는 처리를 담당함.
 * 
 * ...AuthInterceptor는 특정 경로에 접근하는 경우 현재 사용자가 로그인한
 * ...상태의 사용자인지 preHandle()을 이용하여 체크함.
 * 
 */

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joywins.domain.MsgUserVO;
import org.joywins.service.IF_LoginUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Inject
	private IF_LoginUserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler)
							throws Exception {

		logger.info("z5.AuthInterceptord preHandle called...");

		HttpSession session = request.getSession();		
		
		if(session.getAttribute("login") == null){
		
			logger.info("z5.current user is not logined");
			
			//...saveDest()를 이용해서 원래 사용자가 원하는 페이지의 정보는 HttpSession에 'dest'라는
			//...이름으로 저장함.
			//...GET방식인 경우 URI정보 뒤에 파라미터들을 함께 보관해야 함.
			saveDest(request);//...644p.추가.
			
			//...642p.sendRedirect 는 프로젝트 경로를 포함해서 지정함.
			response.sendRedirect("/z5/zuser/login");
			
			return false;			
			
		}
		
		//...인터셉터이전에 원래 의도한 페이지로 이동함.
		return true;
		
	}


	/*
	 * ...644p.로그인하지 않은 상태에서 LoginInterceptor::postHandle 의 처리결과로 인해
	 * ...http://localhost:8080/z5/zsboard/listPage에서 'New Board'버튼을 클릭하면
	 * ...http://localhost:8080/z5로 돌아가는 현상을 해결하고자, 사용자가 이동하고자 했던 URI를
	 * ...먼저 보관한 후, 로그인이 성공하면 해당경로로 연결시켜주도록 함.
	 * ...원래 사용자가 이동하고자 한 페이지 정보는 HttpSession에 'dest'라는 이름으로 지정함.
	 * ...GET방식인 경우 URI정보 뒤의 파라미터들을 함께 보관함.
	 */
	private void saveDest(HttpServletRequest req) {

		String uri = req.getRequestURI();
	
		String query = req.getQueryString();
		logger.info("z5.saveDest query = " + query);
	
		if (query == null || query.equals("null")) {
		  query = "";
		} else {
		  query = "?" + query;
		}
	
		if (req.getMethod().equals("GET")) {
		  logger.info("dest: " + (uri + query));
		  req.getSession().setAttribute("dest", uri + query);
		}
	}


	
	
	
}


