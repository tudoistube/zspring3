package org.joywins.zweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
/***
 * http://localhost:8080/zweb/doC?msg="2Be"
 * @author Administrator
 *
 */
@Controller
public class SampleController2 {

	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController2.class);
	
	//...108p.http://localhost:8080/zweb/doC?msg="2Be"
	//...@ModelAttribute 는 자동으로 해당 객체를 뷰까지 전달함.
	//...result.jsp
	//...zweb.Hello "2Be"
	@RequestMapping("doC")
	public String doC( @ModelAttribute("msg") String msg){
		
		logger.info("zweb.doC called..............................");
		
		return "result";
		
	}	
	
}
