package org.joywins.controller;
//...477p.

import javax.inject.Inject;

import org.joywins.domain.MsgVO;
import org.joywins.service.IF_MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {
	
  private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

  @Inject
  private IF_MsgService service;
	
  /*
   * ...478p.
   * ...http://localhost:8080/z3/msg
   		Content-Type: application/json
		{
		  "receiver_id" : "zuser01",
		  "sender_id"   : "zuser02",
		  "message"     : "You can do it!"
		}   		
   		
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> addMessage(@RequestBody MsgVO vo) {
	  
	logger.info("addMessage POST called ...........");
	
	ResponseEntity<String> entity = null;
	try {
	  service.addMessage(vo);
	  entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	return entity;
  }  
	
}
