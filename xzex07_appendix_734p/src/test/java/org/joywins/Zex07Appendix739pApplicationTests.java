package org.joywins;
//...706p.
import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
/*
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Zex07Appendix739pApplication.class)
@WebAppConfiguration
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Zex07Appendix739pApplicationTests {
	
	@Autowired
	private DataSource ds;
	
	//...710p.
	@Autowired
	private SqlSessionFactory sqlSession;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testConnection() throws Exception {
		
		System.out.println("DataSource : " + ds);
		
		Connection con = ds.getConnection();
		
		System.out.println("con : " + con);
		
		con.close();
		
	}
	
	//...710p.
	@Test
	public void testSqlSession() {
		
		System.out.println("sqlSession : " + sqlSession);
		
	}
	

}
