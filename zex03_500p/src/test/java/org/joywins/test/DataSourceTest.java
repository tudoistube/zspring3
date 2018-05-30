package org.joywins.test;
//...160p.
//...163p. ZTBL_BOARD 테이블 생성후 테스트 데이터 생성할 것.
import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {

	@Inject
	private DataSource ds;	
	
	@Test
	public void testConection()throws Exception{
		
		try(Connection con = ds.getConnection()){
			
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
