package org.joywins.dao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//...632p.
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserDAOImpl implements IF_LoginUserDAO {

	@Inject
	private SqlSession session;

	private static String namespace ="org.joywins.mapper.loginUserMapper";	
	
	
	@Override
	public MsgUserVO selectUser(LoginDTO dto) throws Exception {
		return session.selectOne(namespace +".selectUser", dto);
	}

	//...665p.
	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);

		session.update(namespace+".keepLogin", paramMap);

	}

	@Override
	public MsgUserVO checkLoginBefore(String value) {
		
		return session.selectOne(namespace +".checkLoginBefore", value);
		
	}

}
