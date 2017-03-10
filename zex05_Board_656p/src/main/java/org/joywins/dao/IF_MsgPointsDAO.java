package org.joywins.dao;
//...468p.추가.
import org.joywins.domain.MsgPointsVO;

public interface IF_MsgPointsDAO {

	  public void insert(MsgPointsVO vo) throws Exception;

	  public MsgPointsVO read(Integer uid, Integer mid) throws Exception;
	  
	  public void update(MsgPointsVO vo) throws Exception;		  
	
}
