package org.joywins.zdao;

import org.joywins.zdomain.MemberVO;
/***
 * DAO = Persistence 패키지.
 * @author Administrator
 *
 */

public interface IF_MemberDAO {
	
	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public MemberVO readMember(String userid)throws Exception;
	  
	public MemberVO readWithPW(String userid, 
				String userpw)throws Exception;	

}
