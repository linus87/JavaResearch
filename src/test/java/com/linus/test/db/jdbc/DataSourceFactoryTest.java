package com.linus.test.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.linus.db.jdbc.DataSourceFactory;
import com.linus.db.jdbc.SQLExceptionUtil;
import com.linus.db.pojo.Country;

public class DataSourceFactoryTest {
	DataSource ds = null;

	@Before
	public void connectyDataSource() throws SQLException {
		ds = DataSourceFactory.getDataSource();

		Assert.assertNotNull(ds);
	}

	@Test
	public void getConnection() {
		try {
			Connection con = ds.getConnection();
			Statement s = con.createStatement();
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

			Assert.assertNotNull(con);
		} catch (SQLException e) {
			SQLExceptionUtil.printSQLException(e);
		}
	}
}
