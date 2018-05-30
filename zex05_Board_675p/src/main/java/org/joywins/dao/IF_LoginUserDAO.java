package org.joywins.dao;
//...631p.

import java.util.Date;

import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;

public interface IF_LoginUserDAO {

	//...로그인할때 사용자 id, pw를 이용해서 사용자정보를 조회함.
	public MsgUserVO selectUser(LoginDTO dto)throws Exception;	
	
	//...664p.
	public void keepLogin(String uid, String sessionId, Date next);
	
	public MsgUserVO checkLoginBefore(String value);

	
}
