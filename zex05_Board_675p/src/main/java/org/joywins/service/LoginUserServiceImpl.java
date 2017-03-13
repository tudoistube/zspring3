package org.joywins.service;
//...633p.
import java.util.Date;

//...633p.
import javax.inject.Inject;

import org.joywins.dao.IF_LoginUserDAO;
import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements IF_LoginUserService {
	
	@Inject
	private IF_LoginUserDAO dao;	

	@Override
	public MsgUserVO selectUser(LoginDTO dto) throws Exception {
		
		return dao.selectUser(dto);
		
	}

	//...666p.
	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		
		dao.keepLogin(uid, sessionId, next);
		
	}

	@Override
	public MsgUserVO checkLoginBefore(String value) {
		
		return dao.checkLoginBefore(value);
		
	}
	
}

