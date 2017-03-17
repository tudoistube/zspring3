package org.joywins.dao;
//...713p.

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.joywins.domain.MsgUserVO;

public interface IF_MsgUserMapper {
	
	@Insert(" insert into ZTBL_MSG_USER (UID,  UPW,  UNAME) "
		  + " values (#{uid}, #{upw}, #{uname})")
	public void insert(MsgUserVO vo) throws Exception;
	
	@Select(" select * from ZTBL_MSG_USER "
		  + " where UID = #{uid}")
	public MsgUserVO select(String uid) throws Exception;
	
	@Update(" update ZTBL_MSG_USER "
		  + " set UPW = #{upw}, UNAME = #{uname} "
		  + " where UID = #{uid}")
	public void update(MsgUserVO vo) throws Exception;
	
	@Delete(" delete from ZTBL_MSG_USER "
		  + " where UID = #{uid} ")
	public void delete(String uid) throws Exception;
	
	//...717p.XML 매퍼를 이용하므로 어노테이션 없는 메서드를 사용함.
	public MsgUserVO login(
			@Param("uid") String uid,
			@Param("upw") String upw
	) throws Exception;

}
