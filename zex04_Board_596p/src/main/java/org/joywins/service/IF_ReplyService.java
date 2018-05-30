package org.joywins.service;
//...375p.
import java.util.List;

import org.joywins.domain.Criteria;
import org.joywins.domain.ReplyVO;

public interface IF_ReplyService {

	public List<ReplyVO> selectReplies(Integer bno) throws Exception;
	
	public void insertReply(ReplyVO vo) throws Exception;

	public void updateReply(ReplyVO vo) throws Exception;

	public void deleteReply(Integer rno) throws Exception;

	//...392p.
	public List<ReplyVO> selectPageReplies(Integer bno, Criteria cri) throws Exception;

	public int countReplies(Integer bno) throws Exception;	
	
}
