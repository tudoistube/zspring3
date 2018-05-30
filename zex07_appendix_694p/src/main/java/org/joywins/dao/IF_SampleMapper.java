package org.joywins.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/*
 * ...683p.Mapper 인터페이스는 기존의 DAO 인터페이스와 동일함.
 *    가장 큰 차이는 인터페이스의 구현을 mybatis-spring 에서 자동으로
 *    생성함.
 */
public interface IF_SampleMapper {
	
	@Select("select now()")
	public String getTime();
	
	/*
	 * ...687p.MyBatis 어노테이션을 사용해서 두 개 이상의 파라미터에 각각
	 *    @Param 어노테이션을 붙여서 처리할 수 있음.
	 */
	@Select("select uname from ztbl_msg_user "
			+ " where uid = #{uid} and upw = #{upw}")
	public String getUname(
			@Param("uid") String uid,
			@Param("upw") String upw
	);
	
	/*
	 * ...689p.Mapper 인터페이스를 이용하는 또 다른 장점은 기존의 XML 을
	 *    그대로 사용할 수 있음.
	 *    이때 두가지 규칙이 있음.
	 *    1. Mapper 인터페이스 이름과 XML Mapper 의 네임스페이스를 반드시
	 *       일치시킬 것.
	 *    2. Mapper 인터페이스의 메서드 이름과 XML 의 id 를 반드시 일치시킴.
	 *    
	 * ...691p.root-context.xml :: sqlSessionFactory 에 매퍼 xml 파일의 경로를 인식하게 함.
	 * 
	 */
	public String getUid(
			@Param("uname") String uname
	);
	
	//...693p.
	@SelectProvider(type=SampleSelectProvider.class, method="searchUname")
	public String searchUname(
			@Param("type") String type,
			@Param("keyword") String keyword
	);
	
}
