package org.joywins.zweb;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MyBatisTest {

	//...94p.스프링이 정상 작동하면 root-context.xml의 sqlSessionFactory가 주입됨.
	@Inject 
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFactory(){
		System.out.println("testFactory");
		System.out.println(sqlFactory);
		
	}	
	
	@Test
	public void testSession()throws Exception{
		
		try(SqlSession session = sqlFactory.openSession()){
			System.out.println("Okay!");
			System.out.println(session);
			
		}catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		}
		
	}

}
