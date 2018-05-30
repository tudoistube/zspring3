package org.joywins.service;
//...633p.
import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;

public interface IF_LoginUserService {

	public MsgUserVO selectUser(LoginDTO dto)throws Exception; 

	
}
