package com.linus.test.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.linus.db.jdbc.DriverManagerUtil;
import com.linus.db.pojo.Country;

public class DriverManagerTest {
	Connection conn = null;

	@Before
	public void getConnection() throws ClassNotFoundException, SQLException {		
		conn = DriverManagerUtil.getConnection();
		
		Assert.assertNotNull(conn);
		
		System.out.println(System.getProperty("jdbc:drivers"));
	}
	
	@Test
	public void statement() throws ClassNotFoundException, SQLException {
		Statement s = conn.createStatement();
		ArrayList<Country> al = new ArrayList<Country>();
		
		if (s.execute("select id,name from country")) {
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Country c = new Country();
				c.setId(rs.getLong(1));
				c.setName(rs.getString(2));
				
				System.out.println(c.getName());
				
				al.add(c);
			}			
			
			Assert.assertEquals(al.size(), 3);
		}		
	
		Assert.assertNotNull(conn);
	}

}
