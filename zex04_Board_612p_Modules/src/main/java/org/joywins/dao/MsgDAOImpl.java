package org.joywins.dao;
//...466p.

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joywins.domain.MsgVO;
import org.springframework.stereotype.Repository;

@Repository
public class MsgDAOImpl implements IF_MsgDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace ="org.joywins.mapper.MsgMapper";	
		
	@Override
	public void insert(MsgVO vo) throws Exception {
		
		session.insert(namespace+".insert", vo);
	}

	@Override
	public MsgVO read(Integer mid) throws Exception {

		return session.selectOne(namespace+".read", mid);
	}

	@Override
	public void updateOpenDate(Integer mid) throws Exception {

		session.update(namespace+".updateOpenDate", mid);

	}


}
