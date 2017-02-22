package org.joywins.service;

import java.util.List;

import org.joywins.domain.BoardVO;
import org.joywins.domain.Criteria;
import org.joywins.domain.SearchCriteria;

//...185p.비즈니스영역(서비스)은 컨트롤러와 DAO사이의 접착제 역할.
//...1. 고객마다 다른 부분을 처리할 수 있는 완충역할을 함.
//...2. 각 회사마다 다른 로직이나 규칙을 DB와 무관하게 처리할 수 있는 완충 영역.
//...3. 컨트롤러와 같은 외부 호출이 DAO에 종속적인 상황을 막아줌.
//...   만약 컨트롤러가 DAO의 DB를 이용하게 되면 트랜잭션 처리나 예외 처리등 모든 로직을
//...	컨트롤러가 담당해야 함.
//...	비즈니스계층(서비스)는 컨르롤러의 일을 덜고 일을 분업하게 함.
public interface IF_BoardService {

	  public void insert(BoardVO board) throws Exception;

	  public BoardVO read(Integer bno) throws Exception;

	  public void update(BoardVO board) throws Exception;

	  public void delete(Integer bno) throws Exception;

	  public List<BoardVO> listAll() throws Exception;
	
	  public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	  
	  //...280p. int listCountCriteria(Criteria cri)	  
	  public int countBno(Criteria cri) throws Exception;

	  //...331p.
	  public List<BoardVO> listSearchCriteria(SearchCriteria cri) 
	      throws Exception;

	  public int listSearchCount(SearchCriteria cri) throws Exception;
	  
	  
}
