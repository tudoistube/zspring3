package org.joywins.dao;

import java.util.List;

import org.joywins.domain.BoardVO;

/***
 * DAO = Persistence 패키지.
 * @author Administrator
 *
 */
public interface IF_BoardDAO {

	  public void create(BoardVO vo) throws Exception;

	  public BoardVO read(Integer bno) throws Exception;

	  public void update(BoardVO vo) throws Exception;

	  public void delete(Integer bno) throws Exception;

	  public List<BoardVO> listAll() throws Exception;	
	
}
