package org.joywins.zweb;
//...110p.

import org.joywins.zdomain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController3 {
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController3.class);
	
	//...113p. http://localhost:8080/zweb/doD
	@RequestMapping("/doD")
	public String doD(Model model){
		
		//make sample data
		ProductVO product = new ProductVO("Sample Product", 10000);
		
		logger.info("doD");
	
		//...addAttribute(객체)처럼 객체이름을 별도로 지정하지 않은 경우, 자동으로 저장되는
		//...객체 클래스명의 앞글자를 소문자로 처리한 이름이 객체이름이 됨.
		//...여기서는 productVO가 객체명이 됨.
		model.addAttribute(product);
		
		return "productDetail";
		
	}	
}
