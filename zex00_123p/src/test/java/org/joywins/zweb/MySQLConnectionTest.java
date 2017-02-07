package org.joywins.zweb;
//...74p.
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER = 
			"com.mysql.jdbc.Driver";
	private static final String URL = 
			"jdbc:mysql://127.0.0.1:3306/zbook_ex";
	private static final String USER = 
			"JoyWins";
	private static final String PW = 
			"ZSql@";	
	
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
