package org.joywins.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ...731p.스프링 부트를 이용하면서 인터셉터를 이용할 경우 가장 많이 사용하는 방법은
 *    WebMvcConfigurerAdapter 를 상속받아서 사용하고, @Configuration 어노테이션을
 *    적용함.
 *    WebMvcConfigurerAdapter 는 과거 servlet-context.xml 의 내용을 설정하는 용도로
 *    만들어진 클래스임.
 *    인터셉터를 활용해야 한다면 addInterceptors() 를 이용해서 처리함.
 *    
 */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("/////////////////////// SampleInterceptor ///////////////////////");
		
		//return super.preHandle(request, response, handler);
		
		return true;
	}

}
