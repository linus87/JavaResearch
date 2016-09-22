package com.linus.db.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverManagerUtil {

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection conn = null;

		try {
			// JavaResearch/db.properties
			fis = new FileInputStream("db.properties");
			props.load(fis);

			if (props.containsKey("DERBY_DB_URL")) {
				conn = getDerbyConnection(props);
			} else if (props.containsKey("MYSQL_DB_URL")) {
				conn = getMysqlConnection(props);
			} else if (props.containsKey("ORACLE_DB_URL")) {
				conn = getOracleConnection(props);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		return conn;
	}

	/**
	 * Get a oracle connection
	 * 
	 * @param props
	 * @return
	 * @throws SQLException
	 */
	public static Connection getOracleConnection(Properties props)
			throws SQLException {

		// Class.forName(props.getProperty("ORACLE_DB_DRIVER_CLASS"));

		// for example jdbc:oracle:thin:@localhost:1521:orcl
		return DriverManager.getConnection(props.getProperty("ORACLE_DB_URL"),
				props.getProperty("ORACLE_DB_USERNAME"),
				props.getProperty("ORACLE_DB_PASSWORD"));
	}

	/**
	 * Get a mysql connection.
	 * 
	 * @param props
	 * @return
	 * @throws SQLException
	 */
	public static Connection getMysqlConnection(Properties props)
			throws SQLException {

		// Class.forName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));

		// for example jdbc:mysql://localhost:3306/

		return DriverManager.getConnection(props.getProperty("MYSQL_DB_URL"),
				props.getProperty("MYSQL_DB_USERNAME"),
				props.getProperty("MYSQL_DB_PASSWORD"));
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////
	// Derby Database
	// ////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Get a derby connection.
	 * 
	 * @param props
	 * @return
	 * @throws SQLException
	 */
	public static Connection getDerbyConnection(Properties props)
			throws SQLException {
		// Class.forName(props.getProperty("DERBY_DB_DRIVER_CLASS"));

		return DriverManager.getConnection(props.getProperty("DERBY_DB_URL"));
	}

	/**
	 * Shut down one derby database.
	 * 
	 * @param dbName
	 * @throws SQLException
	 */
	public static void shutdownDerbyDatabase(String dbName) throws SQLException {
		DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
	}

	/**
	 * Shut down all derby database
	 * 
	 * @throws SQLException
	 */
	public static void shutdownAllDerbyDatabase() throws SQLException {
		DriverManager.getConnection("jdbc:derby:;shutdown=true");
	}
}
