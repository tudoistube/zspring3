package org.joywins.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//...702p.

@RestController
public class HiController {
	
	@RequestMapping("/hi")
	public String hi() {
		return "Hi, Wonderful World!";
	}

}
