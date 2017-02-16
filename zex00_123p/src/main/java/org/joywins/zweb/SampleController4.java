package org.joywins.zweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController4.class);

	/*
	 * ...115, 210p.
	 * ...http://localhost:8080/zweb/doE를 입력하면,
	 * ...http://localhost:8080/zweb/doF로 주소창이 바뀜.
	 * ...HTTP Status 404 - /zweb/WEB-INF/views/doF.jsp
	 * 
	 *...다른 경로를 호출하면서 데이터를 전달함.
	 *...redirect: 에서 ':'가 있음에 주의할 것.
	 *...RedirectAttributes클래스로 리다이렉트 시점에 데이터를 임시로 추가로 넘김.
	 *...addFlashAttribute(변수명, 값)을 이용해서 리다이렉트 시점에 임시 데이터를 전달함.
	 *... 이 경우, URI에 보이지 않게 데이터를 전달함.
	 */
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr){
		logger.info("zweb.doE called but redirect to /doF.............");
		
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected");
		return "redirect:/doF";
	}	
	
	@RequestMapping("/doF")
	public void doF(String msg){
		System.out.println("msg = " + msg);	
		logger.info("doF called..............."+ msg);
	}
	
}
