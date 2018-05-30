package org.joywins.dao;
//...464p.
//...DAO : 도메인객체와 SQL을 처리.
import org.joywins.domain.MsgVO;

public interface IF_MsgDAO {

  public void insert(MsgVO vo) throws Exception;

  public MsgVO read(Integer mid) throws Exception;

  public void updateOpenDate(Integer mid) throws Exception;

	
}
