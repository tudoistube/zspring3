package org.joywins.service;
//...376p.
import java.util.List;

import javax.inject.Inject;

import org.joywins.dao.IF_BoardDAO;
import org.joywins.dao.IF_ReplyDAO;
import org.joywins.domain.Criteria;
import org.joywins.domain.ReplyVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements IF_ReplyService {

	@Inject
	private IF_ReplyDAO replyDAO;//...505p.dao→replyDAO로 변경.
	
	//...505p.
	@Inject
	private IF_BoardDAO boardDAO;	
	
	@Override
	public List<ReplyVO> selectReplies(Integer bno) throws Exception {
		return replyDAO.selectReplies(bno);
	}

	@Transactional //...added since 506p.
	@Override
	public void insertReply(ReplyVO vo) throws Exception {
	    replyDAO.insertReply(vo);
	    boardDAO.updateReplyCnt(vo.getBno(), 1); //...added since 506p.
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		replyDAO.updateReply(vo);
	}

	@Transactional //...added since 506p.
	@Override
	public void deleteReply(Integer rno) throws Exception {
		int bno = replyDAO.getBno(rno);
		replyDAO.deleteReply(rno);
		boardDAO.updateReplyCnt(bno, -1); //...added since 506p.
	}

	//...392p.
	@Override
	public List<ReplyVO> selectPageReplies(Integer bno, Criteria cri) throws Exception {
		return replyDAO.selectPageReplies(bno, cri);
	}

	@Override
	public int countReplies(Integer bno) throws Exception {
		return replyDAO.countReplies(bno);
	}


	

}
