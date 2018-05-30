package org.joywins;
//...732p.

import org.joywins.interceptor.SampleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * ...731p.스프링 부트를 이용하면서 인터셉터를 이용할 경우 가장 많이 사용하는 방법은
 *    WebMvcConfigurerAdapter 를 상속받아서 사용하고, @Configuration 어노테이션을
 *    적용함.
 *    WebMvcConfigurerAdapter 는 과거 servlet-context.xml 의 내용을 설정하는 용도로
 *    만들어진 클래스임.
 *    인터셉터를 활용해야 한다면 addInterceptors() 를 이용해서 처리함.
 *    
 */
@Configuration
public class Zex07AppWebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//super.addInterceptors(registry);
		
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/hiJsp");
		
	}
	

}
