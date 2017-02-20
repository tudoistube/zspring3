package org.joywins.controller;
//...306p.

import javax.inject.Inject;

import org.joywins.domain.BoardVO;
import org.joywins.domain.PageMaker;
import org.joywins.domain.SearchCriteria;
import org.joywins.service.IF_BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/zsboard/*")//...306p.
public class SearchBoardController {

	  private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

	  @Inject
	  private IF_BoardService service;
	 
	  //...306p.
	  //...SearchCriteria를 @ModelAttribute를 사용하기 때문에 listPage.jsp에 자동으로 전달됨.
	  @RequestMapping(value = "/listPage", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info("listPage is called... cri = " + cri.toString());

	    //model.addAttribute("list", service.listCriteria(cri));//...332p.주석.
	    model.addAttribute("list", service.listSearchCriteria(cri));

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	    //pageMaker.setTotalCount(service.countBno(cri));//...332p.주석.
	    pageMaker.setTotalCount(service.listSearchCount(cri));

	    model.addAttribute("pageMaker", pageMaker);
	  }

	  //...335p.
	  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
	  public void read(@RequestParam("bno") int bno, 
			  			@ModelAttribute("cri") SearchCriteria cri, 
			  			Model model)
	      throws Exception {
		  logger.info("read GET called... bno = " + bno + " // cri = " + cri.toString() + " // model = " + model.toString());
		  model.addAttribute(service.read(bno));
	  }
	  
	  //...336p.
	  @RequestMapping(value = "/deletePage", method = RequestMethod.POST)
	  public String delete(@RequestParam("bno") int bno, 
			  				SearchCriteria cri, 
			  				RedirectAttributes rttr) throws Exception {
		logger.info("delete called... bno = " + bno + " // cri = " + cri.toString());
	    service.delete(bno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/zsboard/listPage";
	  }
	  
	  //...337p.
	  @RequestMapping(value = "/updatePage", method = RequestMethod.GET)
	  public void updatePageGET(int bno, 
			  					@ModelAttribute("cri") SearchCriteria cri, 
			  					Model model) throws Exception {
		logger.info("updatePageGET called... bno = " + bno + " // cri = " + cri.toString() + " // model = " + model.toString());
	    model.addAttribute(service.read(bno));
	  }  

	  @RequestMapping(value = "/updatePage", method = RequestMethod.POST)
	  public String updatePagePOST(BoardVO board, 
			  						SearchCriteria cri, 
			  						RedirectAttributes rttr) throws Exception {

	    logger.info("updatePagePOST called... board = " + board.toString() + " // cri = " + cri.toString());
	    service.update(board);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    logger.info(rttr.toString());

	    return "redirect:/zsboard/listPage";
	  }

	  //...339p.
	  @RequestMapping(value = "/insert", method = RequestMethod.GET)
	  public void insertGET() throws Exception {

	    logger.info("regist get ...........");
	  }  
	  
	  @RequestMapping(value = "/insert", method = RequestMethod.POST)
	  public String insertPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	    logger.info("regist post ... board = " + board.toString());

	    service.insert(board);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/zsboard/listPage";
	  }
	  
	  
	  
}
