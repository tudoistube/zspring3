package org.joywins.dao;

import java.util.List;

import org.joywins.domain.BoardVO;
import org.joywins.domain.Criteria;
import org.joywins.domain.SearchCriteria;

/***
 * DAO = Persistence 패키지.
 * ...177p.
 * @author Administrator
 *
 */
public interface IF_BoardDAO {

	public void insert(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void delete(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listPage(int page) throws Exception;

	//...256p. Criteria객체를 파라미터로 전달받고, 
	//...필요한 getPageStart()와 getPerPageNum()를 호출한 결과를 사용함.
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	//...279p.
	public int countBno(Criteria cri) throws Exception;
	
	//...323p.
	//...S.use for dynamic sql	  
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	  
	public int listSearchCount(SearchCriteria cri)throws Exception;
	//...E.use for dynamic sql	

	//...504p.
	public void updateReplyCnt(Integer bno, int amount)throws Exception;
	  
	//...511p.
	public void updateViewCnt(Integer bno)throws Exception;
	
}
