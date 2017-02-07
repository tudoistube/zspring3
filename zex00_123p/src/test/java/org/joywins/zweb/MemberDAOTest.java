package org.joywins.zweb;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.joywins.zdao.IF_MemberDAO;
import org.joywins.zdomain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	@Test
	public void testInsertMember()throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setUserid("zuser04");
		vo.setUserpw("zuser04");
		vo.setUsername("ZUSER04");
		vo.setEmail("zuser04@joywins.com");
		
		dao.insertMember(vo);
		
	}	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
