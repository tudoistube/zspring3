package org.joywins.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * ...736p.
 */
@Controller
public class ZThymeleafController {

	@RequestMapping("/listTest")
	public void listTest(Model model) {
		
		List<String> list = new ArrayList<>();
		
		for(int i=0; i < 20; i++){
			
			list.add("Data : " + i);
			
		}
		
		model.addAttribute("name", "Sample Data");
		model.addAttribute("list", list);		
		
	}
	
}
