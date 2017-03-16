package org.joywins.web;
//...685p.

import javax.inject.Inject;

import org.joywins.dao.IF_SampleMapper;
import org.joywins.dao.SampleSelectProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SampleMapperTest {
	
	@Inject
	private IF_SampleMapper mapper;
	
	@Test
	public void testTime(){
		System.out.println("mapper.getClass().getName() : " + mapper.getClass().getName());
		
		System.out.println("getTime() : " + mapper.getTime());
	}
	
	//...688p.
	@Test
	public void testUname(){
		
		System.out.println("mapper.getClass().getName() : " + mapper.getClass().getName());
		
		String uname = mapper.getUname("ZUSER00", "ZUSER00");
		
		System.out.println("getUname() : " + uname);
		
	}
	
	//...691p.
	@Test
	public void testUid(){
		
		System.out.println(mapper.getClass().getName());
		
		String uid = mapper.getUid("Iron Man");
		
		System.out.println("getUid() : " + uid);
		
	}	
	
	/*...693p.
	public void testSearchUname(){

		System.out.println(mapper.getClass().getName());
		
		String uname = mapper.searchUname(SampleSelectProvider.class, keyword) .getUid("Iron Man");
		
		System.out.println("getUid() : " + uid);
				
	}
	*/

}
