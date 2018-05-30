package org.joywins.dao;
/*
 * ...129p.
 * ...DAO = Persistence 패키지.
 */
import org.joywins.domain.MemberVO;

public interface IF_MemberDAO {

	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public MemberVO selectMember(String userid)throws Exception;
	  
	public MemberVO readWithPW(String userid, String userpw)throws Exception;
	
}
