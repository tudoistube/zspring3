package org.joywins.service;
//...470p.

import javax.inject.Inject;

import org.joywins.dao.IF_MsgDAO;
import org.joywins.dao.IF_MsgPointsDAO;
import org.joywins.dao.IF_MsgUserDAO;
import org.joywins.domain.MsgPointsVO;
import org.joywins.domain.MsgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgServiceImpl implements IF_MsgService {

	@Inject
	private IF_MsgDAO msgDAO;

	@Inject
	private IF_MsgUserDAO msgUserDAO;
	
	@Inject
	private IF_MsgPointsDAO msgPointsDAO;
	
	private MsgPointsVO msgPointsVO;

	/*
	 * ...495p.SampleAdvice의 @Before, @Around를 주석처리하고
	 * ...addMessage()에 @Transactional을 선언하면 STS상에 Around형태의 아이콘이 나타남.
	 * ...테스트를 위해 updatePoint의 sql문에 고의로 오류를 발생시킴. 
	 * ...where xxuid = #{uid}
	 * 
	 * ...http://localhost:8080/z3/msg 테스트 결과.
		Content-Type: application/json
		{
		  "receiver_id" : "zuser02",
		  "sender_id"   : "zuser03",
		  "message"     : "xxx.You can do it!"
		}		
	 * 
	 * 404:Bad Request로 정상적인 msgDAO.insert(vo)까지 롤백시켜서 전체트랜잭션이 처리되지 않음.
	 * 
	 * ...497p.SQL문이 모두 정상인 경우, 하나의 Connection이 열리고,
	 * ...두개의 SQL문이 처리된 후 commit()이 됨.
	 * 
	 */
	@Transactional
	@Override
	public void addMessage(MsgVO vo) throws Exception {
		msgDAO.insert(vo);
		msgUserDAO.updatePoint(vo.getSender_id(), 10);
		
	/*
	 * ...478p.
	 * ...msgDAO.insert(vo); 에서 ZTBL_MSG_POINTS에 대한 처리를 함께 하고자
	 *    했으나 안됨.
		msgPointsVO = new MsgPointsVO();
		msgPointsVO.setUid(vo.getSender_id());
		msgPointsVO.setMid(1);
		msgPointsVO.setUpoint(10);
		msgPointsVO.setActivity("send");
		msgPointsDAO.insert(msgPointsVO);
	*/

	}

	@Override
	public MsgVO readMessage(String uid, Integer mid) throws Exception {
		msgDAO.updateOpenDate(mid);

		msgUserDAO.updatePoint(uid, 5);

		return msgDAO.read(mid);
	}

}
