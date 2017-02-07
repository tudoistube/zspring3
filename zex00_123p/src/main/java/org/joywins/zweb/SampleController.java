package org.joywins.zweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.joywins.zweb.SampleController;

@Controller
public class SampleController {

	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController.class);
	
	//...106p.http://localhost:8080/zweb/doA
	//...HTTP Status 404 - /zweb/WEB-INF/views/doA.jsp
	@RequestMapping("doA")
	public void doA(){
		
		logger.info("zweb.doA called....................");
		
	}

	//...106p.http://localhost:8080/zweb/doB
	//...HTTP Status 404 - /zweb/WEB-INF/views/doB.jsp
	@RequestMapping("doB")
	public void doB(){
		
		logger.info("zweb.doB called....................");
		
	}	
	
	
}
