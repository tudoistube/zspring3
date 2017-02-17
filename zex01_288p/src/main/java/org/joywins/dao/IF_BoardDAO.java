package org.joywins.dao;

import java.util.List;

import org.joywins.domain.BoardVO;
import org.joywins.domain.Criteria;

/***
 * DAO = Persistence 패키지.
 * 
 * @author Administrator
 *
 */
public interface IF_BoardDAO {

	public void insert(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void delete(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listPage(int page) throws Exception; //...249p.added.

	// ...256p. 필요한 getPageStart()와 getPerPageNum()를 호출한 결과를 사용함.
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int countBno(Criteria cri) throws Exception;

}
