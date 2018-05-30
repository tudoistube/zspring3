package org.joywins.dao;
//...468p.추가.

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.joywins.domain.MsgPointsVO;
import org.springframework.stereotype.Repository;

@Repository
public class MsgPointsDAOImpl implements IF_MsgPointsDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace ="org.joywins.mapper.MsgPointMapper";	
	
	
	@Override
	public void insert(MsgPointsVO vo) throws Exception {
		session.insert(namespace+".insert", vo);
	}

	@Override
	public MsgPointsVO read(Integer uid, Integer mid) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("point", mid);
		
		return session.selectOne(namespace+".read", paramMap);
	}

	@Override
	public void update(MsgPointsVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}


}
