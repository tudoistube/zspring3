package org.joywins;

import org.joywins.dao.IF_MsgUserMapper;
import org.joywins.domain.MsgUserVO;
import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

/*
 * ...715p.Zex07Appendix739pApplicationTests 코드가 점점 더 복잡해질 경우,
 *    기존 코드를 상속해서 생성하면, @RunWith 와 @SpringBootTest 와 같은 
 *    별도의 어노테이션 없이 Test 클래스를 생성함.
 *    
 * ...716p.기존의 프로젝트에서 MyBatis 를 사용하는 방식 :
 *    테이블 생성 및 도메인 클래스 작성.
 *    DAO 인터페이스 정의.
 *    XML 매퍼 작성.
 *    DAO 인터페이스 구현.
 *    
 * ...717p.스프링부트에서 MyBatis 를 사용하는 방식 :
 *    테이블 생성 및 도메인 클래스 작성.
 *    인터페이스 Mapper 작성 및 어노테이션 처리.
 *    
 */
public class MsgUserMapperTest extends Zex07Appendix739pApplicationTests {

	@Autowired
	private IF_MsgUserMapper mapper;
	
	@Test
	public void testInsert() throws Exception {
		
		MsgUserVO vo = new MsgUserVO();
		
		vo.setUid("USER04");
		vo.setUpw("USER04");
		vo.setUname("Park");
		
		mapper.insert(vo);
		
	}

	@Test
	public void testSelect() throws Exception {
		
		String uid = "USER04";
		
		MsgUserVO vo = mapper.select(uid);
		
		vo.toString();
		
	}	

	@Test
	public void testLogin() throws Exception {
		
		MsgUserVO vo = mapper.login("ZUSER00", "ZUSER00");
		
		System.out.println("testLogin vo : " + vo.toString());
		
	}	
	
}
