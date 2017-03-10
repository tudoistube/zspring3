package org.joywins.interceptor;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
//...618p.
/*
 * ...618p.
 * ...Servlet Filter vs Spring MVC Interceptor.
 * ...Filter : 동일한 웹어플리케이션 영역 내에서 필요한 자원들을 활용함.
 * ...			웹어플리케이션 내에서 동작하므로 스프링의 Context를 접근하기 어렵다.
 * ...Interceptor : 스프링의 Context내에서 존재하므로 Context내의 모든 객체를 활용할 수 있다.
 * 
 * ...일반적으로 AOP(BeforeAdvice...)을 활용하기 보다는 HandlerInterceptor인터페이스 또는
 * ...HandlerInterceptorAdapter클래스를 활용하는 경우가 더 많음.
 * 
 * ...AOP의 Advice는 JoinPoint 또는 ProceedingJoinPoint...을 활용해서 호출대상이 되는 메서드의
 * ...파라미터등을 처리함.
 * 
 * ...HandlerInterceptor 또는 HandlerInterceptorAdapter는 Filter와 유사하게 HttpServletRequest,
 * ...HttpServletResponse를 파라미터로 받는 구조임.
 * ...인터셉터는 기존의 컨트롤러에서는 순수하게 필요한 데이터를 만들어내고, 인터셉터를 이용해서
 * ...웹과 관련된 처리를 하도록 도와줌.
 * 
 * ...HandlerInterceptor의 메서드.
 * ...preHandle(request, response, handler) : 지정된 컨트롤러의 동작 이전에 가로채는 역할을 함.
 * ...                                        대부분은 preHandle()을 이용해서 로그인 처리를 함.
 * ...                    handler : 현재 실행하려는 메서드 자체, 이를 활용하면 현재 실행되는
 * ...                    컨트롤러를 파악하거나, 추가적인 메서드를 실행하는 등의 작업이 가능함.
 * ...postHandle(request, response, handler, modelAndView) : 지정된 컨트롤러의 동작 이후에 처리함.
 * ...              SpringMVC의 FrontController인 DispatcherServlet이 화면을 처리하기 전에 동작함.
 * ...afterCompletion(request, response, handler, exception) : DispatcherServlet의 화면 처리가
 * ...                                                         완료된 상태에서 처리함.
 * 
 */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {

	
	//...621p.
	//...preHandle의 반환형은 boolean으로 하여 다음 인터셉터나 대상 컨트롤러를 호출할 것인지를 결정함.
	@Override
	public boolean preHandle(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler) throws Exception {
	
		System.out.println("S.pre handle..........................");	

		//...623p.
		/*...handler는 HandlerMethod타입으로 캐스팅하여 원래의 메서드와 객체(빈)을 확인할 수 있음.*/
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		System.out.println("실행하려는 컨트롤러 Bean: " + method.getBean());
		System.out.println("실행하려는 메서드 Method: " + methodObj);
		
		System.out.println("E.pre handle..........................");

		return true;		

	}
	
	
	//...620p.
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
	
		System.out.println("post handle called........................");

		/*
		 * ...624p.
		 * ...postHandler() : 컨트롤러의 동작후 화면 처리전에 실행.
		 * ...홈컨트롤러에서 'http://localhost:8080/z5/doB'를 호출하면 'result'라는 문자열이 보관되고,
		 * ...이후에 postHandler()에서 'result'라는 변수가 ModelAndView에 존재하면 이를 추출해서
		 * ...HttpSession에 추가함.
		 */
		Object result = modelAndView.getModel().get("result");
		
		if(result != null){
			System.out.println("result = " + result);
			request.getSession().setAttribute("result", result);
			//...624p.jsp처럼 http://localhost:8080/z5/doA 전체경로중
			//...http://localhost:8080이후의 경로를 모두 지정해야 페이지 이동함.
			response.sendRedirect("/z5/doA");
		}
		
		
	}		


}
