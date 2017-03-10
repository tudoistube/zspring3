package org.joywins.dao;
//...631p.

import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;

public interface IF_LoginUserDAO {

	//...로그인할때 사용자 id, pw를 이용해서 사용자정보를 조회함.
	public MsgUserVO selectUser(LoginDTO dto)throws Exception;	
	
}
