package org.joywins.service;
//...469p.
import org.joywins.domain.MsgVO;

public interface IF_MsgService {

	  public void addMessage(MsgVO vo) throws Exception;

	  public MsgVO readMessage(String uid, Integer mid) throws Exception;

}
