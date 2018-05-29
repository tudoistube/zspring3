package org.joywins.zweb;
//...82p.
//import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 *...89p.@RunWith, @ContextConfiguration 어노테이션은 테스트코드 실행시 스프링을 로딩시킴.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
*...java.lang.IllegalStateException: Failed to load ApplicationContext
	https://stackoverflow.com/questions/40565064/junit-test-whats-wrong
*/
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	
	@Inject
	private DataSource ds;

	@Test
	public void testConnection()throws Exception{
		//fail("Not yet implemented");
		
		try(Connection con = ds.getConnection()){			
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
