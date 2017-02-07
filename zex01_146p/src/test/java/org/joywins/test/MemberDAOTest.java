package org.joywins.test;
import javax.inject.Inject;

import org.junit.Test;
//...137p.
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.joywins.dao.IF_MemberDAO;
import org.joywins.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

	@Inject
	private IF_MemberDAO dao;
	
	@Test
	public void testTime()throws Exception{		
		System.out.println("joywins.zweb.testTime() = " + dao.getTime());
		
	}	
	
	//@Test
	public void testInsertMember()throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setUser_id("tuser02");
		vo.setUser_pw("tuser02");
		vo.setUser_name("TUSER02");
		vo.setEmail("tuser02@joywins.com");
		
		dao.insertMember(vo);		
	}	

	@Test
	public void selectMember()throws Exception{		
		dao.selectMember("tuser02");		
	}		
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}


