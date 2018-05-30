package org.joywins.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("z5::Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	//...396p.
	@RequestMapping(value = "/testAjax", method = RequestMethod.GET)
	public void testAjax() {
	
	}

	//...619p.
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {

		System.out.println("doA called....................");
	
		return "home2";
	}  
	//...619p.
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	public String doB(Locale locale, Model model) {

		System.out.println("doB called....................");
	
		model.addAttribute("result", "result : doB called...");
	
		return "home2";
	}  
	


	
}
