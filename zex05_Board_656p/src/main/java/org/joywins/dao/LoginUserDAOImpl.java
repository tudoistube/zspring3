package org.joywins.dao;
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

}
