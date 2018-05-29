package org.joywins.zweb;
//...74p.
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER = 
			"com.mysql.cj.jdbc.Driver";
/*
*..MySQL JDBC 드라이버 Time Zone 이슈	
	https://desty.github.io/2016/08/14/mysql-jdbc-timezone-issue
*...MySQL – Establishing SSL connection without server’s identity verification is not recommended	
	https://www.mkyong.com/mysql/mysql-establishing-ssl-connection-without-servers-identity-verification-is-not-recommended/
*/			
	private static final String URL = 
			"jdbc:mysql://127.0.0.1:3306/zbook_ex?serverTimezone=UTC&useSSL=false";
	private static final String USER = 
			"*****";
	private static final String PW = 
			"*****";	
	
	@Test
	public void testConnection() throws Exception {
		//fail("Not yet implemented");

		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){			
			System.out.println("Okay");			
			System.out.println(con);
			
		}catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
			
		}
		
	}

}
