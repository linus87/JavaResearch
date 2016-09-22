package com.linus.test.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.linus.db.jdbc.DriverManagerUtil;
import com.linus.db.pojo.Country;

public class PreparedStatementTest {
	Connection conn = null;
	
	@Before
	public void getConnection() throws ClassNotFoundException, SQLException {		
		conn = DriverManagerUtil.getConnection();
		
		Assert.assertNotNull(conn);
	}
	
	@Test
	public void statement() throws ClassNotFoundException, SQLException {
		PreparedStatement s = conn.prepareStatement("select id,name from country where id=?");
		s.setLong(1, 1);
		
		if (s.execute()) {
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Country c = new Country();
				c.setId(rs.getLong(1));
				c.setName(rs.getString(2));
				
				System.out.println(c.getName());
			}			
		}
	}
}
