package org.joywins.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//...724p.

@Controller
public class HiJspController {
	
	@RequestMapping(value="/hiJsp", method=RequestMethod.GET)
	public void hiJsp(Model model) {
		
		model.addAttribute("msg", "Hi, Jsp!");
		
	}
	
	//...726p.한글처리.
	//   http://devday.tistory.com/3666
	@RequestMapping(value="/hiJsp", method=RequestMethod.POST)
	public void hiJspPost(String uid, String upw
			            , String uname, Model model) {
		
		System.out.println("UID : "+uid);
		System.out.println("UPW : "+upw);
		System.out.println("UNAME : "+uname);
		
		model.addAttribute("msg", "Hi, " + uname);
		
	}	
}
