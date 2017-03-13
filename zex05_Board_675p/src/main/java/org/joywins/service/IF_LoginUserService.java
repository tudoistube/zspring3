package org.joywins.service;
//...633p.
import java.util.Date;

//...633p.
import org.joywins.domain.MsgUserVO;
import org.joywins.dto.LoginDTO;

public interface IF_LoginUserService {

	public MsgUserVO selectUser(LoginDTO dto)throws Exception; 
	
	//...665p.
	public void keepLogin(String uid, String sessionId, Date next)throws Exception;

	public MsgUserVO checkLoginBefore(String value);

	
}
