package com.linus.db.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.derby.jdbc.ClientDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource40;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	private static DataSource ds;
	
	public static DataSource getDataSource() throws SQLException {
		if (ds != null) return ds;
		
		Properties props = new Properties();
		FileInputStream fis = null;

		try {
			// JavaResearch/db.properties
			fis = new FileInputStream("db.properties");
			props.load(fis);
			
			if (props.containsKey("DERBY_DB_URL")) {
				ds = getDerbyDataSource(props);
				return ds;
			}

			if (props.containsKey("MYSQL_DB_URL")) {
				ds = getMySQLDataSource(props);
				return ds;
			}

			if (props.containsKey("ORACLE_DB_URL")) {
				ds = getOracleDataSource(props);
				return ds;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ds;
	}
	
	/**
	 * Get a derby data source.
	 * @param props
	 * @return
	 */
	public static DataSource getDerbyDataSource(Properties props) {
		DataSource ds = null;
		
		if ("EMBEDDED".equalsIgnoreCase(props.getProperty("DERBY_DB_ENVIRONMENT"))) {
			EmbeddedDataSource40 eds = new EmbeddedDataSource40();
			
			eds.setCreateDatabase("create");
			eds.setDatabaseName(props.getProperty("DERBY_DB_DATABASE"));
			
			if (props.getProperty("DERBY_DB_PASSWORD") != null) {
				eds.setUser(props.getProperty("DERBY_DB_USERNAME"));
				eds.setPassword(props.getProperty("DERBY_DB_PASSWORD"));
			}
			
			eds.setDescription(props.getProperty("DERBY_DB_DESCRIPTION"));
			ds = eds;
		} else {
			ClientDataSource cds = new ClientDataSource();
			cds.setServerName(props.getProperty("DERBY_DB_SERVER"));
			cds.setPortNumber(Integer.parseInt(props.getProperty("DERBY_DB_PORT")));
			cds.setDatabaseName(props.getProperty("DERBY_DB_DATABASE"));
			
			if (props.getProperty("DERBY_DB_PASSWORD") != null) {
				cds.setUser(props.getProperty("DERBY_DB_USERNAME"));
				cds.setPassword(props.getProperty("DERBY_DB_PASSWORD"));
			}
			
			cds.setDescription(props.getProperty("DERBY_DB_DESCRIPTION"));
			
			ds = cds;
		}

		return ds;
	}

	/**
	 * Get a mysql data source
	 * @param props
	 * @return
	 */
	public static DataSource getMySQLDataSource(Properties props) {
		MysqlDataSource ds = new MysqlDataSource();

		ds.setURL(props.getProperty("MYSQL_DB_URL"));
		ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
		ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));

		return ds;
	}

	/**
	 * Get a oracle data source
	 * @param props
	 * @return
	 * @throws SQLException
	 */
	public static DataSource getOracleDataSource(Properties props)
			throws SQLException {
		OracleDataSource ds = new OracleDataSource();

		ds.setURL(props.getProperty("ORACLE_DB_URL"));
		ds.setUser(props.getProperty("ORACLE_DB_USERNAME"));
		ds.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));

		return ds;
	}

	public void DataSourceUtil() throws NamingException {
		ClientDataSource ds = new ClientDataSource();
		ds.setServerName("derby");
		ds.setDatabaseName("derby");
		ds.setDescription("Database for billing");

		Context ctx = new InitialContext();
		ctx.bind("jdbc/billingDB", ds);
	}

	public Connection getConnection() throws NamingException, SQLException {
		Connection con = null;

		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("jdbc/billingDB");
		con = ds.getConnection();

		return con;
	}

}
