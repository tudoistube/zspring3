package org.joywins.controller;
//...522p.

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
	}	

	//...524p.
	/*...528p.주석처리.
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());//...파일의 MIME타입등의 정보.

	}
	*/

	//...527p.
	//   /WEB-INF/spring/appServlet/servlet-context.xml 에 설정되있는 업로드된 파일 경로 정보.
	@Resource(name = "uploadPath")
	private String uploadPath;
		
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
	
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
	
		model.addAttribute("savedName", savedName);
	
		return "uploadResult";
	}
	
	//...528p.
	private String uploadFile(String originalFilename, byte[] fileData) throws Exception {
		
		//...UUID는 중복되지 않는 고유한 키값을 설정할때 사용함.
		//...파일업로드에서 주의할 점은, 동일한 경로에 동일한 이름의 파일을 업로드하는 것.
		//...UUID와 같이 (거의)고유한 값을 생성해서 처리하면 해결할 수 있음.
		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalFilename;

		File target = new File(uploadPath, savedName);

		//...529p.실제 파일처리는 스프링에서 제공하는 FileCopyUtils를 이용함.
		//...FileCopyUtils는 import org.springframework.util.FileCopyUtils패키지에 설정된 클래스임.
		//...파일 데이터를 파일로 처리하거나, 복사하는 등의 작업에 유용하게 사용될 수 있음.
		//...org.springframework.util.FileCopyUtils패키지는 개발자가 개발하면서 필요한 여러 종류의 
		//...클래스를 제공함. 
		FileCopyUtils.copy(fileData, target);

		return savedName;

	}
	
	//...536p.
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
	}
	
	/*
	 * ...544p.
	 * ...@RequestMapping::produces = "text/plain;charset=UTF-8"는 한국어를 정상적으로
	 * ...전송하기 위한 설정임.
	 */
	@ResponseBody
	@RequestMapping(value ="/uploadAjax", 
					method=RequestMethod.POST, 
		  			produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{

	logger.info("/uploadAjax.POST.originalName: " + file.getOriginalFilename());
	logger.info("/uploadAjax.POST.size: " + file.getSize());
	logger.info("/uploadAjax.POST.contentType: " + file.getContentType());	
	
	/*
	 * ...544p.
	 * ...HttpStatus.CREATED는 원하는 리소스가 정상적으로 생성되었다는 상태코드임.
	 * ...HttpStatus.OK를 이용해도 무방함.
	 */
	return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
	}
	
	

}
